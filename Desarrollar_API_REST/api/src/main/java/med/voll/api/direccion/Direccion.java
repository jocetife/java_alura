package med.voll.api.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Direccion {
    
    public Direccion(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.numero = direccion.numero();
        this.complemento = direccion.complemento();
        this.barrio = direccion.barrio();
        this.ciudad = direccion.ciudad();
        this.codigoPostal = direccion.codigo_postal();
        this.estado = direccion.estado();
    }

    private String calle;
    private String numero;
    private String complemento;
    private String barrio;
    private String ciudad;
    private String codigoPostal;
    private String estado;
}
