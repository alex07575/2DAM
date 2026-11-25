import java.math.BigDecimal;

public class AcuerdoSuministro {
    private final Integer id;
    private final Proveedor proveedor;
    private final Producto producto;
    private final String referenciaProveedor;
    private final BigDecimal precioCompra;
    private final int plazoEntregaDias;

    public AcuerdoSuministro(Integer id, Proveedor proveedor, Producto producto,
                             String referenciaProveedor, BigDecimal precioCompra,
                             int plazoEntregaDias) {
        this.id = Validacion.idOpcional(id);
        if (proveedor == null || producto == null) {
            throw new IllegalArgumentException("Proveedor y producto son obligatorios.");
        }
        if (proveedor.suministra(producto) || producto.tieneProveedor(proveedor)) {
            throw new IllegalArgumentException("Ya existe un acuerdo entre este proveedor y producto.");
        }
        this.proveedor = proveedor;
        this.producto = producto;
        this.referenciaProveedor = Validacion.texto(referenciaProveedor, "La referencia del proveedor");
        this.precioCompra = Validacion.positivo(precioCompra, "El precio de compra");
        if (plazoEntregaDias <= 0) {
            throw new IllegalArgumentException("El plazo de entrega debe ser positivo.");
        }
        this.plazoEntregaDias = plazoEntregaDias;
        proveedor.registrarAcuerdo(this);
        producto.registrarAcuerdo(this);
    }

    public Integer getId() { return id; }
    public Proveedor getProveedor() { return proveedor; }
    public Producto getProducto() { return producto; }
    public String getReferenciaProveedor() { return referenciaProveedor; }
    public BigDecimal getPrecioCompra() { return precioCompra; }
    public int getPlazoEntregaDias() { return plazoEntregaDias; }
}
