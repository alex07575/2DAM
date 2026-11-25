import java.time.LocalDate;

public class InscripcionCampania {
    private final Integer id;
    private final Cliente cliente;
    private final Campania campania;
    private final LocalDate fechaInscripcion;

    public InscripcionCampania(Integer id, Cliente cliente, Campania campania,
                               LocalDate fechaInscripcion) {
        this.id = Validacion.idOpcional(id);
        if (cliente == null || campania == null || fechaInscripcion == null) {
            throw new IllegalArgumentException("Cliente, campaña y fecha son obligatorios.");
        }
        if (cliente.participaEn(campania) || campania.tieneInscrito(cliente)) {
            throw new IllegalArgumentException("El cliente ya está inscrito en esta campaña.");
        }
        this.cliente = cliente;
        this.campania = campania;
        this.fechaInscripcion = fechaInscripcion;
        cliente.registrarInscripcion(this);
        campania.registrarInscripcion(this);
    }

    public Integer getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Campania getCampania() { return campania; }
    public LocalDate getFechaInscripcion() { return fechaInscripcion; }
}
