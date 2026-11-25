import java.time.LocalDate;

public class AsignacionFuncion {
    private final Integer id;
    private final Empleado empleado;
    private final Funcion funcion;
    private final LocalDate inicio;
    private LocalDate fin;
    private final DetalleFuncion detalle;

    public AsignacionFuncion(Integer id, Empleado empleado, Funcion funcion, LocalDate inicio,
                             LocalDate fin, DetalleFuncion detalle) {
        this.id = Validacion.idOpcional(id);
        if (empleado == null || funcion == null || inicio == null) {
            throw new IllegalArgumentException("Empleado, función e inicio son obligatorios.");
        }
        if (fin != null && fin.isBefore(inicio)) {
            throw new IllegalArgumentException("El fin no puede ser anterior al inicio.");
        }
        if (!detalleCompatible(funcion.getTipo(), detalle)) {
            throw new IllegalArgumentException("El detalle no corresponde a la función asignada.");
        }
        this.empleado = empleado;
        this.funcion = funcion;
        this.inicio = inicio;
        this.fin = fin;
        this.detalle = detalle;
        empleado.agregarAsignacion(this);
    }

    public boolean solapa(AsignacionFuncion otra) {
        if (otra == null || empleado != otra.empleado || !mismaFuncion(otra)) {
            return false;
        }
        LocalDate finA = fin == null ? LocalDate.MAX : fin;
        LocalDate finB = otra.fin == null ? LocalDate.MAX : otra.fin;
        return !inicio.isAfter(finB) && !otra.inicio.isAfter(finA);
    }

    private boolean mismaFuncion(AsignacionFuncion otra) {
        Integer idFuncion = funcion.getId();
        Integer idOtraFuncion = otra.funcion.getId();
        if (idFuncion != null && idOtraFuncion != null) {
            return idFuncion.equals(idOtraFuncion);
        }
        return funcion == otra.funcion;
    }

    public void finalizar(LocalDate fecha) {
        if (fin != null || fecha == null || fecha.isBefore(inicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior al inicio.");
        }
        fin = fecha;
    }

    private boolean detalleCompatible(TipoFuncion tipo, DetalleFuncion detalle) {
        return (tipo == TipoFuncion.CAJERO && detalle instanceof DetalleCajero)
                || (tipo == TipoFuncion.EXPENDEDOR && detalle instanceof DetalleExpendedor)
                || (tipo == TipoFuncion.RESPONSABLE_TIENDA
                && detalle instanceof DetalleResponsableTienda)
                || (tipo == TipoFuncion.JEFE_ESTACION && detalle instanceof DetalleJefeEstacion);
    }

    public Integer getId() { return id; }
    public Empleado getEmpleado() { return empleado; }
    public Funcion getFuncion() { return funcion; }
    public LocalDate getInicio() { return inicio; }
    public LocalDate getFin() { return fin; }
    public DetalleFuncion getDetalle() { return detalle; }
}
