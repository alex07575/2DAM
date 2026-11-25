import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LineaVenta {
    private final Integer id;
    private final int numeroOrden;
    private final Producto producto;
    private final BigDecimal cantidad;
    private final BigDecimal precioUnitarioAplicado;
    private final BigDecimal importe;
    private final List<Devolucion> devoluciones = new ArrayList<>();
    private Venta venta;

    public LineaVenta(Integer id, int numeroOrden, Producto producto, BigDecimal cantidad) {
        this.id = Validacion.idOpcional(id);
        if (numeroOrden <= 0 || producto == null || !producto.isActivo()) {
            throw new IllegalArgumentException("Número, producto activo y orden son obligatorios.");
        }
        producto.validarCantidad(cantidad);
        this.numeroOrden = numeroOrden;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitarioAplicado = producto.getPrecioVenta();
        this.importe = precioUnitarioAplicado.multiply(cantidad).setScale(2, RoundingMode.HALF_UP);
    }

    void asignarVenta(Venta venta) {
        if (this.venta != null || venta == null) {
            throw new IllegalStateException("La línea ya pertenece a una venta.");
        }
        this.venta = venta;
    }

    BigDecimal calcularReintegro(BigDecimal cantidadDevuelta) {
        producto.validarCantidad(cantidadDevuelta);
        BigDecimal cantidadDevueltaAnterior = devoluciones.stream()
                .map(Devolucion::getCantidad)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (cantidadDevueltaAnterior.add(cantidadDevuelta).compareTo(cantidad) > 0) {
            throw new IllegalArgumentException("La devolución supera la cantidad vendida.");
        }
        BigDecimal reintegradoAnterior = devoluciones.stream()
                .map(Devolucion::getImporteReintegrado)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal disponible = importe.subtract(reintegradoAnterior);
        BigDecimal calculado = precioUnitarioAplicado.multiply(cantidadDevuelta)
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal reintegro = calculado.min(disponible);
        if (reintegro.signum() <= 0) {
            throw new IllegalArgumentException("La devolución no genera un importe reintegrable.");
        }
        return reintegro;
    }

    void registrarDevolucion(Devolucion devolucion) {
        devoluciones.add(devolucion);
    }

    public Integer getId() { return id; }
    public int getNumeroOrden() { return numeroOrden; }
    public Producto getProducto() { return producto; }
    public BigDecimal getCantidad() { return cantidad; }
    public BigDecimal getPrecioUnitarioAplicado() { return precioUnitarioAplicado; }
    public BigDecimal getImporte() { return importe; }
    public Venta getVenta() { return venta; }
    public List<Devolucion> getDevoluciones() {
        return Collections.unmodifiableList(devoluciones);
    }
}
