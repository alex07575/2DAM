import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Pagos {
    private int id;
    private int id_cliente;
    private LocalDate fecha;
    private BigDecimal importe;
    private BigDecimal litros;
    private String combustible;


    public Pagos(int id, int id_cliente, LocalDate fecha, BigDecimal importe, BigDecimal litros, String combustible) {
        if (id <= 0){
            System.out.println("El id debe ser positivo. ");
        }

        if (id_cliente <= 0){
            System.out.println("El id del cliente debe ser positivo. ");
        }

        if(fecha == null){
            System.out.println("La fecha es obligatoria. ");
        }

        if(importe == null || importe.signum()<=0){
            System.out.println("El importe debe ser mayor que cero. ");
        }

        if(litros == null || litros.signum()<=0){
            System.out.println("Los litros deben ser mayor que 0. ");
        }

        if(combustible == null || combustible.trim().isEmpty()){
            System.out.println("Es obligatoria la cantidad de combustible. ");
        }

        this.id = id;
        this.id_cliente = id_cliente;
        this.fecha = fecha;
        this.importe = importe.setScale(2);
        this.litros = litros.setScale(2);
        this.combustible = combustible.trim();
    }

    @Override
    public String toString() {
        return "Pagos{" +
                "id=" + id +
                ", id_cliente=" + id_cliente +
                ", fecha=" + fecha +
                ", importe=" + importe +
                ", litros=" + litros +
                ", combustible='" + combustible + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public BigDecimal getLitros() {
        return litros;
    }

    public String getCombustible() {
        return combustible;
    }
}
