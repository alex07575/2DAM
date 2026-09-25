import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
public class Principal {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Gasolinera gasolinera = new Gasolinera();
        System.out.println("1. Alta de cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Buscar clientes");
        System.out.println("5. Consultar pago");
        System.out.println("0. Salir");
        int opcion;
        opcion = teclado.nextInt();
        switch (opcion) {
            case 1 -> altaCliente(gasolinera);
            case 2 -> gasolinera.listarClientes();
            case 3 -> buscarCliente(gasolinera);
            case 4 -> procesarPago(gasolinera);
            case 5 -> gasolinera.listarPagos();
            case 0 -> System.out.println("Hasta pronto.");
            default -> System.out.println("Opción no válida.");
        }
    }

    public static boolean dosDecimales(double numero) {
        double multiplicado = numero * 100;
        return multiplicado % 1 == 0;
    }

    public static void altaCliente(Gasolinera gasolinera) {
        String nombre = teclado.nextLine();
            if (nombre.isEmpty()) {
                System.out.println("El nombre es obligatorio.");
            } else {
                System.out.println("Se ha registrado correctamente");
            }

        String telefono = teclado.nextLine();
            if (telefono.isEmpty()) {
                System.out.println("El teléfono es obligatorio.");
            } else {
                System.out.println("Se ha registrado correctamente");
            }

        String matricula = teclado.nextLine();
            if (matricula.isEmpty()) {
                System.out.println("La matrícula es obligatoria.");
            } else {
                System.out.println("Se ha registrado correctamente");
            }

        if (gasolinera.matriculaExiste(matricula)) {
            System.out.println("Esa matrícula ya está registrada.");
            return;
        }

        try {
            gasolinera.altaCliente(nombre, telefono, matricula);
        } catch (IOException e) {
            System.out.println("No se ha podido guardar el cliente.");
        }
    }

    public static void buscarCliente(Gasolinera gasolinera) {
        String texto = teclado.nextLine();
            if (texto.isEmpty()) {
                System.out.println("Debes introducir un texto.");
            } else {
                System.out.println("Se ha registrado correctamente");
            }
        gasolinera.buscarClientes(texto);
    }
    public static void procesarPago(Gasolinera gasolinera) {
        if (!gasolinera.hayClientes()) {
            System.out.println("Primero debes dar de alta un cliente.");
            return;
        }
        gasolinera.mostrarClientes();

        int id = teclado.nextInt();
        if (id <= 0) {
            System.out.println("El ID debe ser positivo.");
        }

        if (gasolinera.buscarCliente(id) == null) {
            System.out.println("No existe un cliente con ese identificador.");
            return;
        }

        String texto = teclado.nextLine();
        LocalDate fecha;
        if (texto.isEmpty()) {
            fecha = LocalDate.now();
        } else {
            fecha = gasolinera.convertirFecha(texto);
            if (fecha == null) {
                System.out.println("La fecha no es válida.");
                return;
            }
        }

        double importe = teclado.nextDouble();

        if (importe <= 0 || !dosDecimales(importe)) {
            System.out.println("El importe debe ser mayor que cero y tener como máximo dos decimales.");
            return;
        }

        double litros = teclado.nextDouble();
        if (litros <= 0 || !dosDecimales(litros)) {
            System.out.println("Introduce una cantidad mayor que cero y con un máximo de dos decimales.");
            return;
        }

        String combustible = teclado.nextLine();

        if (combustible.isEmpty()) {
            System.out.println("El combustible es obligatorio.");
            return;
        }
    }
}
