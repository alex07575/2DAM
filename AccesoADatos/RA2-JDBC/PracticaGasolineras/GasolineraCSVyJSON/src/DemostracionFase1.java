import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DemostracionFase1 {
    public static void main(String[] args) {
        Estacion estacion = new Estacion(null, "EST-001", "Estación Centro",
                "Calle Mayor 10", "910000000", LocalDate.of(2020, 1, 1));
        estacion.configurar(new ConfiguracionFacturacion(null, "CFG-001", "A",
                1, "Gracias por su visita"));
        estacion.registrarSurtidor(new Surtidor(null, "SUR-001", 1, "GASOLINA 95",
                EstadoSurtidor.OPERATIVO));
        estacion.validarParaAlta();

        Empleado empleado = new Empleado(null, "EMP-001", "12345678A", "Ana López",
                "600000001", null, LocalDate.of(2022, 5, 1), estacion);
        Cliente cliente = new Cliente(null, "CLI-001", "Luis Pérez",
                "600000002", null, null);

        Producto envasado = new ProductoEnvasado(null, "BOLSA-001",
                "Frutos secos en bolsa", new BigDecimal("2.50"), true, "Bolsa de 250 g");
        Producto granel = new ProductoGranel(null, "GRANEL-001",
                "Frutos secos a granel", new BigDecimal("10.00"), true, 3);

        Proveedor proveedor = new Proveedor(null, "PROV-001", "B12345678",
                "Distribuciones Centro", "910000001", null);
        new AcuerdoSuministro(null, proveedor, envasado, "BOL-25",
                new BigDecimal("1.20"), 2);
        new AcuerdoSuministro(null, proveedor, granel, "GRA-10",
                new BigDecimal("6.00"), 3);
        proveedor.validarParaCatalogo();
        envasado.validarParaCatalogo();
        granel.validarParaCatalogo();

        Venta venta = new Venta(null, "TICKET-001", estacion, empleado, cliente,
                LocalDateTime.now());
        LineaVenta lineaEnvasada = new LineaVenta(null, 1, envasado, new BigDecimal("2"));
        LineaVenta lineaGranel = new LineaVenta(null, 2, granel, new BigDecimal("0.250"));
        venta.agregarLinea(lineaEnvasada);
        venta.agregarLinea(lineaGranel);

        new PagoTienda(null, venta, new BigDecimal("5.00"), LocalDateTime.now(),
                MedioPago.EFECTIVO);
        new PagoTienda(null, venta, new BigDecimal("2.50"), LocalDateTime.now(),
                MedioPago.TARJETA);
        venta.confirmarPagada();

        System.out.println("Importe de 2 unidades envasadas: "
                + lineaEnvasada.getImporte() + " €");
        System.out.println("Importe de 0,250 kg a granel: "
                + lineaGranel.getImporte() + " €");
        System.out.println("Total pagado con dos medios: " + venta.calcularTotal() + " €");

        new Devolucion(null, lineaEnvasada, BigDecimal.ONE, "Envase sin abrir",
                LocalDateTime.now(), empleado, true);
        System.out.println("Reintegro de una unidad: "
                + lineaEnvasada.getDevoluciones().get(0).getImporteReintegrado() + " €");

        try {
            envasado.calcularImporte(new BigDecimal("1.5"));
        } catch (IllegalArgumentException e) {
            System.out.println("Cantidad fraccionaria rechazada: " + e.getMessage());
        }
    }
}
