import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
            Gasolinera gasolinera = new Gasolinera();
            int opcion;

                opcion = leerEntero("Opción: ");
                switch (opcion) {
                    case 1 -> altaCliente(gasolinera);
                    case 2 -> gasolinera.listarClientes();
                    case 3 -> buscarCliente(gasolinera);
                    case 4 -> procesarPago(gasolinera);
                    case 5 -> gasolinera.listarPagos();
                    case 0 -> System.out.println("Hasta pronto.");
                    default -> System.out.println("Opción no válida.");
                }

            } while (opcion != 0);

        } catch (IOException e) {
            System.out.println("Error con los archivos: " + e.getMessage());
        }
    }

    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine().trim();
    }

    public static int leerEntero(String mensaje) {
        boolean esValido = false;
        int numero = 0;
        while (!esValido) {
            try {
                numero = Integer.parseInt(leerTexto(mensaje));
                esValido = true;
            } catch (Exception e) {
                System.out.println("Introduce un número entero.");
            }
        }
        return numero;
    }

    public static double leerDouble(String mensaje) {
        boolean esValido = false;
        double numero = 0;
        while (!esValido) {
            try {
                numero = Double.parseDouble(leerTexto(mensaje).replace(",", "."));
                esValido = true;
            } catch (Exception e) {
                System.out.println("Introduce una cantidad válida.");
            }
        }
        return numero;
    }

    public static boolean dosDecimales(double numero) {
        double multiplicado = numero * 100;
        return multiplicado % 1 == 0;
    }

    public static void mostrarMenu() {
        System.out.println("1. Alta de cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Buscar clientes");
        System.out.println("5. Consultar pago");
        System.out.println("0. Salir");
    }

    public static void altaCliente(Gasolinera gasolinera) {
        String nombre;
        do {
            nombre = leerTexto("Nombre: ");
            if (nombre.isEmpty()) {
                System.out.println("El nombre es obligatorio.");
            }
        } while (nombre.isEmpty());

        String telefono;
        do {
            telefono = leerTexto("Teléfono: ");
            if (telefono.isEmpty()) {
                System.out.println("El teléfono es obligatorio.");
            }
        } while (telefono.isEmpty());

        String matricula;
        do {
            matricula = leerTexto("Matrícula: ");
            if (matricula.isEmpty()) {
                System.out.println("La matrícula es obligatoria.");
            }
        } while (matricula.isEmpty());

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
        String texto;
        do {
            texto = leerTexto("Texto que buscar: ");
            if (texto.isEmpty()) {
                System.out.println("Debes introducir un texto.");
            }
        } while (texto.isEmpty());
        gasolinera.buscarClientes(texto);
    }
    public static void procesarPago(Gasolinera gasolinera) {
        if (!gasolinera.hayClientes()) {
            System.out.println("Primero debes dar de alta un cliente.");
            return;
        }
        gasolinera.mostrarClientes();

        int id = 0;
        boolean idValido = false;
        while (!idValido) {
            id = leerEntero("ID del cliente: ");
            if (id > 0) {
                idValido = true;
            } else {
                System.out.println("El ID debe ser positivo.");
            }
        }

        if (gasolinera.buscarCliente(id) == null) {
            System.out.println("No existe un cliente con ese identificador.");
            return;
        }

        LocalDate fecha = null;
        while (fecha == null) {
            String texto = leerTexto("Fecha (dd/MM/yyyy; vacío para hoy): ");
            if (texto.isEmpty()) {
                fecha = LocalDate.now();
            } else {
                fecha = gasolinera.convertirFecha(texto);
                if (fecha == null) {
                    System.out.println("La fecha no es válida.");
                }
            }
        }

        double importe = 0;
        boolean importeValido = false;
        while (!importeValido) {
            if (importe > 0 && dosDecimales(importe)) {
                importeValido = true;
            } else {
                System.out.println("Introduce una cantidad mayor que cero y con un máximo de dos decimales.");
            }
        }

        double litros = 0;
        boolean litrosValidos = false;
        while (!litrosValidos) {
            litros = leerDouble("Litros: ");
            if (litros > 0 && dosDecimales(litros)) {
                litrosValidos = true;
            } else {
                System.out.println("Introduce una cantidad mayor que cero y con un máximo de dos decimales.");
            }
        }

        String combustible;
        do {
            combustible = leerTexto("Combustible: ");
            if (combustible.isEmpty()) {
                System.out.println("El combustible es obligatorio.");
            }
        } while (combustible.isEmpty());
        try {
            gasolinera.registrarPago(id, fecha, importe, litros, combustible);
        } catch (IOException e) {
            System.out.println("No se ha podido guardar el pago.");
        }
    }
    }