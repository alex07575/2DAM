import java.time.LocalDate;

public class TarjetaFidelizacion {
    private final Integer id;
    private final String numero;
    private final LocalDate fechaEmision;
    private Cliente cliente;
    private LocalDate fechaAsignacion;

    public TarjetaFidelizacion(Integer id, String numero, LocalDate fechaEmision) {
        this.id = Validacion.idOpcional(id);
        this.numero = Validacion.texto(numero, "El número de tarjeta");
        if (fechaEmision == null) {
            throw new IllegalArgumentException("La fecha de emisión es obligatoria.");
        }
        this.fechaEmision = fechaEmision;
    }

    public void asignar(Cliente cliente, LocalDate fecha) {
        if (this.cliente != null || cliente == null || fecha == null || fecha.isBefore(fechaEmision)) {
            throw new IllegalArgumentException("La asignación de tarjeta no es válida.");
        }
        cliente.registrarTarjeta(this);
        this.cliente = cliente;
        this.fechaAsignacion = fecha;
    }

    public Integer getId() { return id; }
    public String getNumero() { return numero; }
    public LocalDate getFechaEmision() { return fechaEmision; }
    public Cliente getCliente() { return cliente; }
    public LocalDate getFechaAsignacion() { return fechaAsignacion; }
}
