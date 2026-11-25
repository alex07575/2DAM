import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagoTienda {
    private final Integer id;
    private final Venta venta;
    private final BigDecimal importe;
    private final LocalDateTime fechaHora;
    private final MedioPago medioPago;

    public PagoTienda(Integer id, Venta venta, BigDecimal importe, LocalDateTime fechaHora,
                      MedioPago medioPago) {
        this.id = Validacion.idOpcional(id);
        if (venta == null || fechaHora == null || medioPago == null) {
            throw new IllegalArgumentException("Venta, fecha y medio de pago son obligatorios.");
        }
        this.venta = venta;
        this.importe = Validacion.positivo(importe, "El importe del pago");
        this.fechaHora = fechaHora;
        this.medioPago = medioPago;
        venta.agregarPago(this);
    }

    public Integer getId() { return id; }
    public Venta getVenta() { return venta; }
    public BigDecimal getImporte() { return importe; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public MedioPago getMedioPago() { return medioPago; }
}
