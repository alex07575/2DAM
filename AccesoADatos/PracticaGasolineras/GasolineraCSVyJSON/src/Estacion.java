import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Estacion {
    private final Integer id;
    private final String codigo;
    private final String nombre;
    private final String direccion;
    private final String telefono;
    private final LocalDate fechaApertura;
    private ConfiguracionFacturacion configuracion;
    private final List<Surtidor> surtidores = new ArrayList<>();

    public Estacion(Integer id, String codigo, String nombre, String direccion,
                    String telefono, LocalDate fechaApertura) {
        this.id = Validacion.idOpcional(id);
        this.codigo = Validacion.texto(codigo, "El código de estación");
        this.nombre = Validacion.texto(nombre, "El nombre");
        this.direccion = Validacion.texto(direccion, "La dirección");
        this.telefono = Validacion.texto(telefono, "El teléfono");
        if (fechaApertura == null) {
            throw new IllegalArgumentException("La fecha de apertura es obligatoria.");
        }
        this.fechaApertura = fechaApertura;
    }

    public void configurar(ConfiguracionFacturacion configuracion) {
        if (this.configuracion != null || configuracion == null
                || configuracion.getEstacion() != null) {
            throw new IllegalStateException("La estación debe tener una única configuración.");
        }
        configuracion.asignarEstacion(this);
        this.configuracion = configuracion;
    }

    public void registrarSurtidor(Surtidor surtidor) {
        if (surtidor == null) {
            throw new IllegalArgumentException("El surtidor es obligatorio.");
        }
        if (surtidor.getEstacion() != null) {
            throw new IllegalArgumentException("El surtidor ya pertenece a una estación.");
        }
        for (Surtidor actual : surtidores) {
            if (actual.getNumeroVisible() == surtidor.getNumeroVisible()) {
                throw new IllegalArgumentException("El número de surtidor ya existe en esta estación.");
            }
        }
        surtidor.asignarEstacion(this);
        surtidores.add(surtidor);
    }

    public void retirarSurtidor(Surtidor surtidor) {
        if (surtidores.size() <= 1) {
            throw new IllegalStateException("La estación debe conservar al menos un surtidor.");
        }
        if (!surtidores.remove(surtidor)) {
            throw new IllegalArgumentException("El surtidor no pertenece a esta estación.");
        }
        surtidor.retirarDeEstacion(this);
    }

    public void validarParaAlta() {
        if (configuracion == null || surtidores.isEmpty()) {
            throw new IllegalStateException("La estación debe tener configuración y al menos un surtidor.");
        }
    }

    public Integer getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public LocalDate getFechaApertura() { return fechaApertura; }
    public ConfiguracionFacturacion getConfiguracion() { return configuracion; }
    public List<Surtidor> getSurtidores() { return Collections.unmodifiableList(surtidores); }
}
