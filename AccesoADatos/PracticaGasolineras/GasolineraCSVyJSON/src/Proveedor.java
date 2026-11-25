import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Proveedor {
    private final Integer id;
    private final String codigo;
    private final String identificacionFiscal;
    private final String razonSocial;
    private final String telefono;
    private final String email;
    private final List<AcuerdoSuministro> acuerdos = new ArrayList<>();

    public Proveedor(Integer id, String codigo, String identificacionFiscal, String razonSocial,
                     String telefono, String email) {
        this.id = Validacion.idOpcional(id);
        this.codigo = Validacion.texto(codigo, "El código del proveedor");
        this.identificacionFiscal = Validacion.texto(identificacionFiscal, "La identificación fiscal");
        this.razonSocial = Validacion.texto(razonSocial, "La razón social");
        this.telefono = telefono == null || telefono.trim().isEmpty() ? null : telefono.trim();
        this.email = email == null || email.trim().isEmpty() ? null : email.trim();
        if (this.telefono == null && this.email == null) {
            throw new IllegalArgumentException("Debe facilitar teléfono o correo electrónico.");
        }
    }

    void registrarAcuerdo(AcuerdoSuministro acuerdo) {
        acuerdos.add(acuerdo);
    }

    boolean suministra(Producto producto) {
        for (AcuerdoSuministro acuerdo : acuerdos) {
            if (acuerdo.getProducto() == producto) {
                return true;
            }
        }
        return false;
    }

    public void validarParaCatalogo() {
        if (acuerdos.isEmpty()) {
            throw new IllegalStateException("El proveedor debe tener al menos un producto acordado.");
        }
    }

    public Integer getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getIdentificacionFiscal() { return identificacionFiscal; }
    public String getRazonSocial() { return razonSocial; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public List<AcuerdoSuministro> getAcuerdos() {
        return Collections.unmodifiableList(acuerdos);
    }
}
