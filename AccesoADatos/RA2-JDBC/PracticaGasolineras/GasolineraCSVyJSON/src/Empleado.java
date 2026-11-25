import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Empleado {
    private final Integer id;
    private final String numeroInterno;
    private final String documento;
    private final String nombre;
    private final String telefono;
    private final String email;
    private final LocalDate fechaIncorporacion;
    private Estacion estacion;
    private Empleado supervisor;
    private final Set<AsignacionFuncion> asignaciones = new HashSet<>();

    public Empleado(Integer id, String numeroInterno, String documento, String nombre,
                    String telefono, String email, LocalDate fechaIncorporacion,
                    Estacion estacion) {
        this.id = Validacion.idOpcional(id);
        this.numeroInterno = Validacion.texto(numeroInterno, "El número interno");
        this.documento = Validacion.texto(documento, "El documento");
        this.nombre = Validacion.texto(nombre, "El nombre");
        this.telefono = telefono == null || telefono.trim().isEmpty() ? null : telefono.trim();
        this.email = email == null || email.trim().isEmpty() ? null : email.trim();
        if (this.telefono == null && this.email == null) {
            throw new IllegalArgumentException("Debe facilitar teléfono o correo electrónico.");
        }
        if (fechaIncorporacion == null || estacion == null) {
            throw new IllegalArgumentException("La fecha de incorporación y la estación son obligatorias.");
        }
        this.fechaIncorporacion = fechaIncorporacion;
        this.estacion = estacion;
    }

    public void asignarSupervisor(Empleado nuevo) {
        if (nuevo == this) {
            throw new IllegalArgumentException("Un empleado no puede supervisarse a sí mismo.");
        }
        Empleado actual = nuevo;
        while (actual != null) {
            if (actual == this) {
                throw new IllegalArgumentException("La supervisión no puede formar ciclos.");
            }
            actual = actual.supervisor;
        }
        supervisor = nuevo;
    }

    public void agregarAsignacion(AsignacionFuncion asignacion) {
        if (asignacion == null || asignacion.getEmpleado() != this) {
            throw new IllegalArgumentException("La asignación debe pertenecer a este empleado.");
        }
        for (AsignacionFuncion existente : asignaciones) {
            if (asignacion.solapa(existente)) {
                throw new IllegalArgumentException("Los periodos de la misma función no pueden solaparse.");
            }
        }
        asignaciones.add(asignacion);
    }

    public void cambiarEstacion(Estacion nuevaEstacion) {
        if (nuevaEstacion == null) {
            throw new IllegalArgumentException("La estación es obligatoria.");
        }
        estacion = nuevaEstacion;
    }

    public Integer getId() { return id; }
    public String getNumeroInterno() { return numeroInterno; }
    public String getDocumento() { return documento; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public LocalDate getFechaIncorporacion() { return fechaIncorporacion; }
    public Estacion getEstacion() { return estacion; }
    public Empleado getSupervisor() { return supervisor; }
    public Set<AsignacionFuncion> getAsignaciones() { return Set.copyOf(asignaciones); }
}
