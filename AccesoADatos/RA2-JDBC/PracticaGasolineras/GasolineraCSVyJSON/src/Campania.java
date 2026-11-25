import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Campania {
    private final Integer id;
    private final String codigo;
    private final String nombre;
    private final String descripcion;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private final List<InscripcionCampania> inscripciones = new ArrayList<>();

    public Campania(Integer id, String codigo, String nombre, String descripcion,
                    LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = Validacion.idOpcional(id);
        this.codigo = Validacion.texto(codigo, "El código de campaña");
        this.nombre = Validacion.texto(nombre, "El nombre de campaña");
        this.descripcion = descripcion == null || descripcion.trim().isEmpty()
                ? null : descripcion.trim();
        if (fechaInicio == null || fechaFin == null || fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("Las fechas de campaña no son válidas.");
        }
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    void registrarInscripcion(InscripcionCampania inscripcion) {
        inscripciones.add(inscripcion);
    }

    boolean tieneInscrito(Cliente cliente) {
        for (InscripcionCampania inscripcion : inscripciones) {
            if (inscripcion.getCliente() == cliente) {
                return true;
            }
        }
        return false;
    }

    public Integer getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public List<InscripcionCampania> getInscripciones() {
        return Collections.unmodifiableList(inscripciones);
    }
}
