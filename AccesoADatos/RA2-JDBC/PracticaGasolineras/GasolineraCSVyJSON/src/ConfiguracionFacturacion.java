public class ConfiguracionFacturacion {
    private final Integer id;
    private final String codigo;
    private final String serieTickets;
    private long siguienteNumero;
    private final String textoPie;
    private Estacion estacion;

    public ConfiguracionFacturacion(Integer id, String codigo, String serieTickets,
                                    long siguienteNumero, String textoPie) {
        this.id = Validacion.idOpcional(id);
        this.codigo = Validacion.texto(codigo, "El código de configuración");
        this.serieTickets = Validacion.texto(serieTickets, "La serie de tickets");
        if (siguienteNumero <= 0) {
            throw new IllegalArgumentException("El siguiente número debe ser positivo.");
        }
        this.siguienteNumero = siguienteNumero;
        this.textoPie = Validacion.texto(textoPie, "El texto de pie");
    }

    public long obtenerSiguienteNumero() {
        return siguienteNumero++;
    }

    void asignarEstacion(Estacion estacion) {
        if (this.estacion != null || estacion == null) {
            throw new IllegalStateException("La configuración ya pertenece a una estación.");
        }
        this.estacion = estacion;
    }

    public Integer getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getSerieTickets() { return serieTickets; }
    public long getSiguienteNumero() { return siguienteNumero; }
    public String getTextoPie() { return textoPie; }
    public Estacion getEstacion() { return estacion; }
}
