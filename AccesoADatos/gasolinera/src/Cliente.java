import java.util.Objects;

public class Cliente {
    private int id;
    private String nombre;
    private String telefono;
    private String matricula;


    public Cliente(int id, String nombre, String telefono, String matricula) {
        if (id <= 0){
            System.out.println("El id debe ser positiv. ");
        }

        if(nombre == null || nombre.trim().isEmpty()){
            System.out.println("El nombre es obligatorio. ");
        }

        if(matricula == null || matricula.trim().isEmpty()){
            System.out.println("La matricula es obligatoria. ");
        }

        if(telefono == null || telefono.trim().isEmpty()){
            System.out.println("El telefono es obligatorio. ");
        }

        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.matricula = matricula.toUpperCase();
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono=" + telefono +
                ", matricula='" + matricula + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getMatricula() {
        return matricula;
    }
}


