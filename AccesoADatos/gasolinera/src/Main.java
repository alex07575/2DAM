

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Flujo ficheros;
    static Gasolinera gasolinera;
    static Scanner teclado;
    public static void main(String[] args) {
        Flujo gestorArchivos = new Flujo();
        gasolinera = new Gasolinera(ficheros);

        try {
            gasolinera.iniciar();
        } catch (IOException e) {
            System.err.println("No se ha podido preparar el almacenamiento: " + e.getMessage());
            return;
        }

        try (Scanner sc = new Scanner(System.in)) {
            teclado = sc;
            ejecutarMenu();
        }
    }
    private static void ejecutarMenu() {
        boolean salir = false;
        while (!salir) {
            mostrarOpciones();
            String opcion = teclado.nextLine().trim();

            switch (opcion) {
                case "1" -> darDeAltaCliente();
                case "2" -> listarClientes();
                case "3" -> buscarClientes();
                case "4" -> procesarPago();
                case "5" -> consultarPagos();
                case "0" -> salir = true;
                default -> System.out.println("Opción no reconocida. Elige una de la lista.");
            }
            System.out.println();
        }
        System.out.println("Hasta pronto.");
    }

    private static void mostrarOpciones() {
        System.out.println("=== GESTIÓN DE GASOLINERA ===");
        System.out.println("1. Dar de alta un cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Buscar clientes");
        System.out.println("4. Procesar un pago de repostaje");
        System.out.println("5. Consultar pagos");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }

    private static void darDeAltaCliente() {
        String nombre = ("Nombre: ");
        String telefono = ("Teléfono: ");
        String matricula = ("Matrícula: ");

        try {
            Cliente c = gasolinera.darDeAltaCliente(nombre, telefono, matricula);
            System.out.println("Cliente registrado con ID " + c.getId() + ".");
        } catch (IllegalStateException e) {
            System.out.println("Esa matrícula ya está registrada. No se ha creado el cliente.");
        } catch (IOException e) {
            System.out.println("No se ha podido guardar el cliente: " + e.getMessage());
        }
    }




}