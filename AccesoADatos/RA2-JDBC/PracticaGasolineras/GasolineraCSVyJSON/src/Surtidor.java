public class Surtidor {
    private final Integer id;
    private final String codigoEquipo;
    private final int numeroVisible;
    private final String combustible;
    private EstadoSurtidor estado;
    private Estacion estacion;

    public Surtidor(Integer id, String codigoEquipo, int numeroVisible, String combustible,
                    EstadoSurtidor estado) {
        this.id = Validacion.idOpcional(id);
        this.codigoEquipo = Validacion.texto(codigoEquipo, "El código de equipo");
        if (numeroVisible <= 0) {
            throw new IllegalArgumentException("El número visible debe ser positivo.");
        }
        this.numeroVisible = numeroVisible;
        this.combustible = Validacion.texto(combustible, "El combustible");
        cambiarEstado(estado);
    }

    public void cambiarEstado(EstadoSurtidor estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado es obligatorio.");
        }
        this.estado = estado;
    }

    void asignarEstacion(Estacion estacion) {
        if (this.estacion != null || estacion == null) {
            throw new IllegalStateException("El surtidor ya pertenece a una estación.");
        }
        this.estacion = estacion;
    }

    void retirarDeEstacion(Estacion estacion) {
        if (this.estacion != estacion) {
            throw new IllegalArgumentException("La estación no es propietaria del surtidor.");
        }
        this.estacion = null;
    }

    public Integer getId() { return id; }
    public String getCodigoEquipo() { return codigoEquipo; }
    public int getNumeroVisible() { return numeroVisible; }
    public String getCombustible() { return combustible; }
    public EstadoSurtidor getEstado() { return estado; }
    public Estacion getEstacion() { return estacion; }
}
