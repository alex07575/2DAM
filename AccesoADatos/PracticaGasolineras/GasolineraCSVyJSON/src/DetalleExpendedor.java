import java.time.LocalDate;

public class DetalleExpendedor implements DetalleFuncion {
    private final String numeroAcreditacion;
    private final LocalDate caducidad;

    public DetalleExpendedor(String numeroAcreditacion, LocalDate caducidad) {
        this.numeroAcreditacion = Validacion.texto(numeroAcreditacion, "El número de acreditación");
        if (caducidad == null) {
            throw new IllegalArgumentException("La fecha de caducidad es obligatoria.");
        }
        this.caducidad = caducidad;
    }

    public String getNumeroAcreditacion() { return numeroAcreditacion; }
    public LocalDate getCaducidad() { return caducidad; }
}
