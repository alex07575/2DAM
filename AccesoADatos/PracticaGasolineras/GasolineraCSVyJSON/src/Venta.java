import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Venta {
    private final Integer id;
    private final String referenciaTicket;
    private final Estacion estacion;
    private final Empleado empleado;
    private final Cliente cliente;
    private final LocalDateTime fechaHora;
    private final List<LineaVenta> lineas = new ArrayList<>();
    private final List<PagoTienda> pagos = new ArrayList<>();
    private EstadoVenta estado = EstadoVenta.ABIERTA;

    public Venta(Integer id, String referenciaTicket, Estacion estacion, Empleado empleado,
                 Cliente cliente, LocalDateTime fechaHora) {
        this.id = Validacion.idOpcional(id);
        this.referenciaTicket = Validacion.texto(referenciaTicket, "La referencia de ticket");
        if (estacion == null || empleado == null || fechaHora == null) {
            throw new IllegalArgumentException("Estación, empleado y fecha son obligatorios.");
        }
        if (empleado.getEstacion() != estacion) {
            throw new IllegalArgumentException("El empleado debe estar adscrito a la estación de la venta.");
        }
        this.estacion = estacion;
        this.empleado = empleado;
        this.cliente = cliente;
        this.fechaHora = fechaHora;
    }

    public void agregarLinea(LineaVenta linea) {
        if (estado != EstadoVenta.ABIERTA) {
            throw new IllegalStateException("No se pueden modificar las líneas de una venta cerrada.");
        }
        if (linea == null || linea.getVenta() != null) {
            throw new IllegalArgumentException("La línea debe ser nueva y es obligatoria.");
        }
        for (LineaVenta existente : lineas) {
            if (existente.getNumeroOrden() == linea.getNumeroOrden()) {
                throw new IllegalArgumentException("El orden de línea ya existe en el ticket.");
            }
        }
        linea.asignarVenta(this);
        lineas.add(linea);
    }

    public BigDecimal calcularTotal() {
        return lineas.stream().map(LineaVenta::getImporte)
                .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, java.math.RoundingMode.HALF_UP);
    }

    public void agregarPago(PagoTienda pago) {
        if (estado != EstadoVenta.ABIERTA || pago == null || pago.getVenta() != this) {
            throw new IllegalStateException("El pago debe corresponder a una venta abierta.");
        }
        BigDecimal nuevoTotal = pagos.stream().map(PagoTienda::getImporte)
                .reduce(BigDecimal.ZERO, BigDecimal::add).add(pago.getImporte());
        if (nuevoTotal.compareTo(calcularTotal()) > 0) {
            throw new IllegalArgumentException("Los pagos superan el importe total de la venta.");
        }
        pagos.add(pago);
    }

    public void confirmarPagada() {
        BigDecimal importePagado = pagos.stream().map(PagoTienda::getImporte)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (lineas.isEmpty() || importePagado.compareTo(calcularTotal()) != 0) {
            throw new IllegalStateException("La venta requiere líneas y pago por el total exacto.");
        }
        estado = EstadoVenta.PAGADA;
    }

    public Integer getId() { return id; }
    public String getReferenciaTicket() { return referenciaTicket; }
    public Estacion getEstacion() { return estacion; }
    public Empleado getEmpleado() { return empleado; }
    public Cliente getCliente() { return cliente; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public List<LineaVenta> getLineas() { return Collections.unmodifiableList(lineas); }
    public List<PagoTienda> getPagos() { return Collections.unmodifiableList(pagos); }
    public EstadoVenta getEstado() { return estado; }
}
