import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HistorialPrecio {
    private final Integer id;
    private final Producto producto;
    private final BigDecimal precioAnterior;
    private final BigDecimal precioNuevo;
    private final LocalDateTime fechaHora;

    public HistorialPrecio(Integer id, Producto producto, BigDecimal precioAnterior,
                           BigDecimal precioNuevo, LocalDateTime fechaHora) {
        this.id = Validacion.idOpcional(id);
        if (producto == null || fechaHora == null) {
            throw new IllegalArgumentException("Producto y fecha son obligatorios.");
        }
        this.producto = producto;
        this.precioAnterior = Validacion.positivo(precioAnterior, "El precio anterior");
        this.precioNuevo = Validacion.positivo(precioNuevo, "El precio nuevo");
        if (precioAnterior.compareTo(precioNuevo) == 0) {
            throw new IllegalArgumentException("El historial requiere un cambio real de precio.");
        }
        this.fechaHora = fechaHora;
    }

    public Integer getId() { return id; }
    public Producto getProducto() { return producto; }
    public BigDecimal getPrecioAnterior() { return precioAnterior; }
    public BigDecimal getPrecioNuevo() { return precioNuevo; }
    public LocalDateTime getFechaHora() { return fechaHora; }
}
