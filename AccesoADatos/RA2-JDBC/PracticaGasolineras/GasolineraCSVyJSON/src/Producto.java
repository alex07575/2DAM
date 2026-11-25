import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Producto {
    private final Integer id;
    private final String referenciaComercial;
    private final String descripcion;
    private BigDecimal precioVenta;
    private boolean activo;
    private final List<AcuerdoSuministro> acuerdos = new ArrayList<>();

    protected Producto(Integer id, String referenciaComercial, String descripcion,
                       BigDecimal precioVenta, boolean activo) throws IllegalArgumentException {
        this.id = Validacion.idOpcional(id);
        this.referenciaComercial = Validacion.texto(referenciaComercial, "La referencia comercial");
        this.descripcion = Validacion.texto(descripcion, "La descripción");
        this.precioVenta = Validacion.positivo(precioVenta, "El precio");
        this.activo = activo;
    }

    public abstract ModalidadProducto getModalidad();

    public abstract void validarCantidad(BigDecimal cantidad);

    public BigDecimal calcularImporte(BigDecimal cantidad) {
        validarCantidad(cantidad);
        return precioVenta.multiply(cantidad).setScale(2, java.math.RoundingMode.HALF_UP);
    }

    public void cambiarPrecio(BigDecimal precio) {
        precioVenta = Validacion.positivo(precio, "El precio");
    }

    public void retirar() { activo = false; }

    void registrarAcuerdo(AcuerdoSuministro acuerdo) {
        acuerdos.add(acuerdo);
    }

    boolean tieneProveedor(Proveedor proveedor) {
        for (AcuerdoSuministro acuerdo : acuerdos) {
            if (acuerdo.getProveedor() == proveedor) {
                return true;
            }
        }
        return false;
    }

    public void validarParaCatalogo() {
        if (acuerdos.isEmpty()) {
            throw new IllegalStateException("El producto debe tener al menos un proveedor.");
        }
    }

    public Integer getId() { return id; }
    public String getReferenciaComercial() { return referenciaComercial; }
    public String getDescripcion() { return descripcion; }
    public BigDecimal getPrecioVenta() { return precioVenta; }
    public boolean isActivo() { return activo; }
    public List<AcuerdoSuministro> getAcuerdos() {
        return Collections.unmodifiableList(acuerdos);
    }
}
