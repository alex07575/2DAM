import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class JsonSimple {
    private JsonSimple() {
    }

    static Object parse(String texto) throws IOException {
        String limpio = texto.trim();
        // Compatibilidad con el contenedor inválido que generaba la versión anterior.
        if (limpio.startsWith("{") && limpio.substring(1).trim().startsWith("[")) {
            limpio = limpio.substring(1, limpio.length() - 1).trim();
        }
        Parser parser = new Parser(limpio);
        Object resultado = parser.valor();
        parser.espacios();
        if (!parser.fin()) throw new IOException("Hay contenido adicional después del JSON.");
        return resultado;
    }

    static String cadena(String valor) {
        StringBuilder salida = new StringBuilder("\"");
        for (int i = 0; i < valor.length(); i++) {
            char c = valor.charAt(i);
            switch (c) {
                case '"' -> salida.append("\\\"");
                case '\\' -> salida.append("\\\\");
                case '\n' -> salida.append("\\n");
                case '\r' -> salida.append("\\r");
                case '\t' -> salida.append("\\t");
                default -> salida.append(c);
            }
        }
        return salida.append('"').toString();
    }

    private static final class Parser {
        private final String texto;
        private int posicion;

        private Parser(String texto) { this.texto = texto; }
        private boolean fin() { return posicion >= texto.length(); }
        private void espacios() {
            while (!fin() && Character.isWhitespace(texto.charAt(posicion))) posicion++;
        }

        private Object valor() throws IOException {
            espacios();
            if (fin()) throw new IOException("JSON incompleto.");
            char c = texto.charAt(posicion);
            if (c == '"') return cadena();
            if (c == '{') return objeto();
            if (c == '[') return arreglo();
            if (texto.startsWith("null", posicion)) { posicion += 4; return null; }
            int inicio = posicion;
            while (!fin() && ",]} \r\n\t".indexOf(texto.charAt(posicion)) < 0) posicion++;
            if (inicio == posicion) throw new IOException("Valor JSON inválido en posición " + posicion);
            String numero = texto.substring(inicio, posicion);
            try {
                return new java.math.BigDecimal(numero);
            } catch (NumberFormatException e) {
                throw new IOException("Valor JSON no soportado: " + numero, e);
            }
        }

        private String cadena() throws IOException {
            posicion++;
            StringBuilder resultado = new StringBuilder();
            while (!fin()) {
                char c = texto.charAt(posicion++);
                if (c == '"') return resultado.toString();
                if (c == '\\') {
                    if (fin()) throw new IOException("Escape JSON incompleto.");
                    char escape = texto.charAt(posicion++);
                    switch (escape) {
                        case '"', '\\', '/' -> resultado.append(escape);
                        case 'n' -> resultado.append('\n');
                        case 'r' -> resultado.append('\r');
                        case 't' -> resultado.append('\t');
                        case 'b' -> resultado.append('\b');
                        case 'f' -> resultado.append('\f');
                        case 'u' -> {
                            if (posicion + 4 > texto.length()) throw new IOException("Escape Unicode inválido.");
                            try {
                                resultado.append((char) Integer.parseInt(texto.substring(posicion, posicion + 4), 16));
                            } catch (NumberFormatException e) {
                                throw new IOException("Escape Unicode inválido.", e);
                            }
                            posicion += 4;
                        }
                        default -> throw new IOException("Escape JSON desconocido: \\" + escape);
                    }
                } else {
                    resultado.append(c);
                }
            }
            throw new IOException("Cadena JSON sin cerrar.");
        }

        private Map<String, Object> objeto() throws IOException {
            posicion++;
            Map<String, Object> mapa = new LinkedHashMap<>();
            espacios();
            if (aceptar('}')) return mapa;
            do {
                espacios();
                if (fin() || texto.charAt(posicion) != '"') throw new IOException("Clave JSON inválida.");
                String clave = cadena();
                espacios();
                exigir(':');
                mapa.put(clave, valor());
                espacios();
                if (aceptar('}')) return mapa;
                exigir(',');
            } while (true);
        }

        private List<Object> arreglo() throws IOException {
            posicion++;
            List<Object> lista = new ArrayList<>();
            espacios();
            if (aceptar(']')) return lista;
            do {
                lista.add(valor());
                espacios();
                if (aceptar(']')) return lista;
                exigir(',');
            } while (true);
        }

        private boolean aceptar(char esperado) {
            if (!fin() && texto.charAt(posicion) == esperado) { posicion++; return true; }
            return false;
        }

        private void exigir(char esperado) throws IOException {
            espacios();
            if (!aceptar(esperado)) throw new IOException("Se esperaba '" + esperado + "' en " + posicion);
        }
    }
}
