package com.jimsimrodev.guerrasOlvidadas.domain.model.direccion;

//import jakarta.persistence.Embeddable;

import com.jimsimrodev.guerrasOlvidadas.adapter.dto.direccion.DatosDireccion;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Direccion {

    private String calle;
    private String numero;
    private String complemento;
    private String distrito;
    private String ciudad;
    private String urbanizacion;
    private String codigoPostal;
    private String provincia;

    public Direccion(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.numero = direccion.numero();
        this.complemento = direccion.complemento();
        this.distrito = direccion.distrito();
        this.ciudad = direccion.ciudad();
        this.urbanizacion = direccion.urbanizacion();
        this.codigoPostal = direccion.codigoPostal();
        this.provincia = direccion.provincia();
    }

    public Direccion actulizarDatos(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.numero = direccion.numero();
        this.complemento = direccion.complemento();
        this.distrito = direccion.distrito();
        this.ciudad = direccion.ciudad();
        this.urbanizacion = direccion.urbanizacion();
        this.codigoPostal = direccion.codigoPostal();
        this.provincia = direccion.provincia();

        return this;
    }

}
