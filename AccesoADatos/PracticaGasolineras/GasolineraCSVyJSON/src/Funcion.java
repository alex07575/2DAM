public class Funcion {
    private final Integer id;
    private final TipoFuncion tipo;
    private final String nombre;

    public Funcion(Integer id, TipoFuncion tipo, String nombre) {
        this.id = Validacion.idOpcional(id);
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de función es obligatorio.");
        }
        this.tipo = tipo;
        this.nombre = Validacion.texto(nombre, "El nombre de función");
    }

    public Integer getId() { return id; }
    public TipoFuncion getTipo() { return tipo; }
    public String getNombre() { return nombre; }
}
