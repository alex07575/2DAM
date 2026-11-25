import java.math.BigDecimal;

public class DetalleCajero implements DetalleFuncion {
    private final BigDecimal limiteDevolucion;

    public DetalleCajero(BigDecimal limiteDevolucion) {
        this.limiteDevolucion = Validacion.positivo(limiteDevolucion, "El límite de devolución");
    }

    public BigDecimal getLimiteDevolucion() { return limiteDevolucion; }
}
