import java.math.BigDecimal;

public class DetalleResponsableTienda implements DetalleFuncion {
    private final BigDecimal presupuestoCompras;

    public DetalleResponsableTienda(BigDecimal presupuestoCompras) {
        this.presupuestoCompras = Validacion.positivo(presupuestoCompras, "El presupuesto");
    }

    public BigDecimal getPresupuestoCompras() { return presupuestoCompras; }
}
