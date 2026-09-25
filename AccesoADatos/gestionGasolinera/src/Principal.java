import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Principal {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Almacenamiento.preparar();
        Gasolinera gasolinera = new Gasolinera();
        System.out.println("1. Alta de cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Buscar clientes");
        System.out.println("4. Registrar pago");
        System.out.println("5. Consultar pagos");
        System.out.println("0. Salir");
        System.out.println("Ponga su opción: ");
        int opcion = teclado.nextInt();
        teclado.nextLine();
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
        System.out.println("Nombre: ");
        String nombre = teclado.nextLine();
        if (nombre.isEmpty()) {
            System.out.println("El nombre es obligatorio.");
            return;
        }
        System.out.println("Teléfono: ");
        String telefono = teclado.nextLine();
        if (telefono.isEmpty()) {
            System.out.println("El teléfono es obligatorio.");
            return;
        }
        System.out.println("Matrícula: ");
        String matricula = teclado.nextLine();
        if (matricula.isEmpty()) {
            System.out.println("La matrícula es obligatoria.");
            return;
        }
        if (gasolinera.matriculaExiste(matricula)) {
            System.out.println("Esa matrícula ya está registrada.");
            return;
        }
        try {
            gasolinera.altaCliente(nombre, telefono, matricula);
            System.out.println("Se ha registrado correctamente.");
        } catch (IOException e) {
            System.out.println("No se ha podido guardar el cliente.");
        }
    }

    public static void buscarCliente(Gasolinera gasolinera) {
        System.out.println("Escriba el nombre del cliente: ");
        String texto = teclado.nextLine();
        if (texto.isEmpty()) {
            System.out.println("Debes introducir un texto.");
            return;
        }
        gasolinera.buscarClientes(texto);
    }

    public static void procesarPago(Gasolinera gasolinera) {
        if (!gasolinera.hayClientes()) {
            System.out.println("Primero debes dar de alta un cliente.");
            return;
        }
        gasolinera.mostrarClientes();
        System.out.println("ID del cliente: ");
        int id = teclado.nextInt();
        if (id <= 0) {
            System.out.println("El ID debe ser positivo.");
            return;
        }
        if (gasolinera.buscarCliente(id) == null) {
            System.out.println("No existe un cliente con ese identificador.");
            return;
        }
        teclado.nextLine();
        System.out.println("Fecha (dd/MM/yyyy; vacío para hoy): ");
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
        System.out.println("Escribe el importe: ");
        double importe = teclado.nextDouble();
        if (importe <= 0 || !dosDecimales(importe)) {
            System.out.println("El importe debe ser mayor que cero y tener como máximo dos decimales.");
            return;
        }
        System.out.println("Escribe los litros: ");
        double litros = teclado.nextDouble();
        if (litros <= 0 || !dosDecimales(litros)) {
            System.out.println("Introduce una cantidad mayor que cero y con un máximo de dos decimales.");
            return;
        }
        teclado.nextLine();
        System.out.println("Escribe el tipo de combustible: ");
        String combustible = teclado.nextLine();
        if (combustible.isEmpty()) {
            System.out.println("El combustible es obligatorio.");
            return;
        }
        try {
            gasolinera.registrarPago(id, fecha, importe, litros, combustible);
            System.out.println("Pago registrado correctamente.");
        } catch (IOException e) {
            System.out.println("No se ha podido guardar el pago.");
        }
    }
}