import java.util.Objects;

public class Cliente {
    private int id;
    private String nombre;
    private String telefono;
    private String matricula;


    public Cliente(int id, String nombre, String telefono, String matricula) {
        if (id <= 0){
            throw new IllegalArgumentException("El id debe ser positivo.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono es obligatorio.");
        }
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("La matrícula es obligatoria.");
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

    public boolean coincideCon(String texto) {
        String buscado = texto.toLowerCase();

        if (nombre.toLowerCase().indexOf(buscado) >= 0) {
            return true;
        }
        if (telefono.toLowerCase().indexOf(buscado) >= 0) {
            return true;
        }
        if (matricula.toLowerCase().indexOf(buscado) >= 0) {
            return true;
        }
        return false;
    }
}


