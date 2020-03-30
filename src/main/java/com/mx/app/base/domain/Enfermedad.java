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
public class Enfermedad {
    
    private String enfermedad;
    private LocalDate fechaEnfermedad;
    private String tratamiento;
    private String  nombreMedicamento;
    private String finalidadMedicamento;

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public LocalDate getFechaEnfermedad() {
        return fechaEnfermedad;
    }

    public void setFechaEnfermedad(LocalDate fechaEnfermedad) {
        this.fechaEnfermedad = fechaEnfermedad;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public String getFinalidadMedicamento() {
        return finalidadMedicamento;
    }

    public void setFinalidadMedicamento(String finalidadMedicamento) {
        this.finalidadMedicamento = finalidadMedicamento;
    }
    
    
    
    
    
}
