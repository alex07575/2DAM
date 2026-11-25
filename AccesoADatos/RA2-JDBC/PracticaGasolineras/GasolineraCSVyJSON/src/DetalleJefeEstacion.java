public class DetalleJefeEstacion implements DetalleFuncion {
    private final int nivelAutorizacion;

    public DetalleJefeEstacion(int nivelAutorizacion) {
        if (nivelAutorizacion <= 0) {
            throw new IllegalArgumentException("El nivel de autorización debe ser positivo.");
        }
        this.nivelAutorizacion = nivelAutorizacion;
    }

    public int getNivelAutorizacion() { return nivelAutorizacion; }
}
