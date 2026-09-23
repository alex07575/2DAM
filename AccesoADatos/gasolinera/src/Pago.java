import java.time.LocalDate;

public class Pago {
    private int id;
    private int id_cliente;
    private LocalDate fecha;
    private double importe;
    private double litros;
    private String combustible;


    public Pago(int id, int id_cliente, LocalDate fecha, double importe, double litros, String combustible) {
        if (id <= 0){
            throw new IllegalArgumentException("El id debe ser positivo. ");
        }

        if (id_cliente <= 0){
            throw new IllegalArgumentException("El id del cliente debe ser positivo. ");
        }

        if(fecha == null){
            throw new IllegalArgumentException("La fecha es obligatoria. ");
        }

        if(importe <=0){
            throw new IllegalArgumentException("El importe debe ser mayor que 0. ");
        }

        if(litros <= 0){
            throw new IllegalArgumentException("Los litros deben ser mayor que 0. ");
        }

        if(combustible == null || combustible.trim().isEmpty()){
            throw new IllegalArgumentException("Es obligatoria la cantidad de combustible. ");
        }

        this.id = id;
        this.id_cliente = id_cliente;
        this.fecha = fecha;
        this.importe = importe;
        this.litros = litros;
        this.combustible = combustible;
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

    public double getImporte() {
        return importe;
    }

    public double getLitros() {
        return litros;
    }

    public String getCombustible() {
        return combustible;
    }


}
