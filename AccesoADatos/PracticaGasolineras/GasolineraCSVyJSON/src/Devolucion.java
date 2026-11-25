import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class Devolucion {
    private final Integer id;
    private final LineaVenta linea;
    private final BigDecimal cantidad;
    private final String motivo;
    private final LocalDateTime fechaHora;
    private final Empleado empleado;
    private final BigDecimal importeReintegrado;
    private final boolean aptaReventa;

    public Devolucion(Integer id, LineaVenta linea, BigDecimal cantidad, String motivo,
                      LocalDateTime fechaHora, Empleado empleado, boolean aptaReventa) {
        this.id = Validacion.idOpcional(id);
        if (linea == null || fechaHora == null || empleado == null) {
            throw new IllegalArgumentException("Línea, fecha y empleado son obligatorios.");
        }
        if (linea.getVenta() == null || linea.getVenta().getEstado() != EstadoVenta.PAGADA) {
            throw new IllegalArgumentException("Solo se puede devolver una línea de una venta pagada.");
        }
        linea.getProducto().validarCantidad(cantidad);
        this.importeReintegrado = linea.calcularReintegro(cantidad);
        this.linea = linea;
        this.cantidad = cantidad;
        this.motivo = Validacion.texto(motivo, "El motivo");
        this.fechaHora = fechaHora;
        this.empleado = empleado;
        this.aptaReventa = aptaReventa;
        linea.registrarDevolucion(this);
    }

    public Integer getId() { return id; }
    public LineaVenta getLinea() { return linea; }
    public BigDecimal getCantidad() { return cantidad; }
    public String getMotivo() { return motivo; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public Empleado getEmpleado() { return empleado; }
    public BigDecimal getImporteReintegrado() { return importeReintegrado; }
    public boolean isAptaReventa() { return aptaReventa; }
}
