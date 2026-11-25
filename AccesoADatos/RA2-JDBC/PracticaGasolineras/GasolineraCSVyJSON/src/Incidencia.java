import java.time.LocalDateTime;

public class Incidencia {
    private final Integer id;
    private final String referencia;
    private final Estacion estacion;
    private final String descripcion;
    private final LocalDateTime fechaApertura;
    private EstadoIncidencia estado;
    private Empleado responsable;

    public Incidencia(Integer id, String referencia, Estacion estacion, String descripcion,
                      LocalDateTime fechaApertura, EstadoIncidencia estado) {
        this.id = Validacion.idOpcional(id);
        this.referencia = Validacion.texto(referencia, "La referencia de incidencia");
        if (estacion == null) {
            throw new IllegalArgumentException("La estación es obligatoria.");
        }
        this.descripcion = Validacion.texto(descripcion, "La descripción");
        if (fechaApertura == null || estado == null) {
            throw new IllegalArgumentException("Fecha y estado son obligatorios.");
        }
        this.estacion = estacion;
        this.fechaApertura = fechaApertura;
        this.estado = estado;
    }

    public void asignarResponsable(Empleado responsable) {
        this.responsable = responsable;
    }
    public void cambiarEstado(EstadoIncidencia estado) {
        if (estado == null) throw new IllegalArgumentException("El estado es obligatorio.");
        this.estado = estado;
    }

    public Integer getId() { return id; }
    public String getReferencia() { return referencia; }
    public Estacion getEstacion() { return estacion; }
    public String getDescripcion() { return descripcion; }
    public LocalDateTime getFechaApertura() { return fechaApertura; }
    public EstadoIncidencia getEstado() { return estado; }
    public Empleado getResponsable() { return responsable; }
}
