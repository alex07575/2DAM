import java.math.BigDecimal;

public class ProductoGranel extends Producto {
    private final int precisionPesada;

    public ProductoGranel(Integer id, String referencia, String descripcion, BigDecimal precio,
                          boolean activo, int precisionPesada) {
        super(id, referencia, descripcion, precio, activo);
        if (precisionPesada < 1 || precisionPesada > 3) {
            throw new IllegalArgumentException("La precisión de pesada debe estar entre 1 y 3.");
        }
        this.precisionPesada = precisionPesada;
    }

    @Override
    public ModalidadProducto getModalidad() { return ModalidadProducto.GRANEL; }

    @Override
    public void validarCantidad(BigDecimal cantidad) {
        Validacion.positivo(cantidad, "La cantidad");
        if (cantidad.stripTrailingZeros().scale() > precisionPesada) {
            throw new IllegalArgumentException("La cantidad supera la precisión de pesada admitida.");
        }
    }

    public int getPrecisionPesada() { return precisionPesada; }
}
