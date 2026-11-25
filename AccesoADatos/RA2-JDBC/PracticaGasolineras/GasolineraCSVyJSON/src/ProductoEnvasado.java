import java.math.BigDecimal;

public class ProductoEnvasado extends Producto {
    private final String formatoEnvase;

    public ProductoEnvasado(Integer id, String referencia, String descripcion, BigDecimal precio,
                            boolean activo, String formatoEnvase) {
        super(id, referencia, descripcion, precio, activo);
        this.formatoEnvase = Validacion.texto(formatoEnvase, "El formato del envase");
    }

    @Override
    public ModalidadProducto getModalidad() { return ModalidadProducto.ENVASADO; }

    @Override
    public void validarCantidad(BigDecimal cantidad) {
        Validacion.positivo(cantidad, "La cantidad");
        if (cantidad.stripTrailingZeros().scale() > 0) {
            throw new IllegalArgumentException("Un producto envasado se vende en unidades enteras.");
        }
    }

    public String getFormatoEnvase() { return formatoEnvase; }
}
