/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.app.base.domain;

import java.time.LocalDate;

/**
 *
 * @author ECortesHe
 */
public class ContactoEmergencia {
    
    private String nombreContacto;
    private int telefonoContacto;
    private String observaciones;

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public int getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(int telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "ContactoEmergencia{" + "nombreContacto=" + nombreContacto + ", telefonoContacto=" + telefonoContacto + ", observaciones=" + observaciones + '}';
    }

    
}
