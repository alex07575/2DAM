import java.math.BigDecimal;

public final class Validacion {
    private Validacion() {
    }

    public static String texto(String valor, String nombre) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(nombre + " es obligatorio.");
        }
        return valor.trim();
    }

    public static BigDecimal positivo(BigDecimal valor, String nombre) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(nombre + " debe ser mayor que cero.");
        }
        return valor;
    }

    public static Integer idOpcional(Integer id) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException("El identificador debe ser positivo.");
        }
        return id;
    }
}
