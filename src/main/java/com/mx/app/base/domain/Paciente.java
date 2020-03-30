package com.mx.app.base.domain;

import com.mx.app.base.data.Status;
import java.io.Serializable;
import java.time.LocalDate;

public final class Paciente implements Serializable, Cloneable {

    //DATOS PERSONALES PACIENTE
    private Long id;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String genero;
    private LocalDate fechaNacimiento;
    private String lugarNacimiento;
    private String curp;
    private String avatar;
    private Status status;
    
    //DATOS GENERALES PACIENTE
//    private String ocupacion;
//    private String religion;
//    private String edoCivil;
//    private boolean tieneHijos;
//    private int numeroHijos;
    //private int edadHijo;
//    private String nombreRecomendo;
//    private String queEsperasTerapia;
    
    //DATOS CONTACTO EMERGENCIA PACIENTE
//    private ContactoEmergencia contactoEmergencia;
    
    //DATOS CONTACTO PACIENTE
    private String email;
    private String telFijo; 
    private String telCelular;
    
    //DATOS CLINICOS PACIENTE
//    private Enfermedad enfermedad;


    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }
    

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

//    public String getOcupacion() {
//        return ocupacion;
//    }
//
//    public void setOcupacion(String ocupacion) {
//        this.ocupacion = ocupacion;
//    }
//
//    public String getReligion() {
//        return religion;
//    }
//
//    public void setReligion(String religion) {
//        this.religion = religion;
//    }
//
//    public String getEdoCivil() {
//        return edoCivil;
//    }
//
//    public void setEdoCivil(String edoCivil) {
//        this.edoCivil = edoCivil;
//    }
//
//    public boolean isTieneHijos() {
//        return tieneHijos;
//    }
//
//    public void setTieneHijos(boolean tieneHijos) {
//        this.tieneHijos = tieneHijos;
//    }
//
//    public int getNumeroHijos() {
//        return numeroHijos;
//    }
//
//    public void setNumeroHijos(int numeroHijos) {
//        this.numeroHijos = numeroHijos;
//    }
//
//    public String getNombreRecomendo() {
//        return nombreRecomendo;
//    }
//
//    public void setNombreRecomendo(String nombreRecomendo) {
//        this.nombreRecomendo = nombreRecomendo;
//    }
//
//    public String getQueEsperasTerapia() {
//        return queEsperasTerapia;
//    }
//
//    public void setQueEsperasTerapia(String queEsperasTerapia) {
//        this.queEsperasTerapia = queEsperasTerapia;
//    }
//
//    public ContactoEmergencia getContactoEmergencia() {
//        return contactoEmergencia;
//    }
//
//    public void setContactoEmergencia(ContactoEmergencia contactoEmergencia) {
//        this.contactoEmergencia = contactoEmergencia;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelFijo() {
        return telFijo;
    }

    public void setTelFijo(String telFijo) {
        this.telFijo = telFijo;
    }

    public String getTelCelular() {
        return telCelular;
    }

    public void setTelCelular(String telCelular) {
        this.telCelular = telCelular;
    }

//    public Enfermedad getEnfermedad() {
//        return enfermedad;
//    }
//
//    public void setEnfermedad(Enfermedad enfermedad) {
//        this.enfermedad = enfermedad;
//    }


    
    

    public boolean isPersisted() {
        return id != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }

        if (obj instanceof Paciente && obj.getClass().equals(getClass())) {
            return this.id.equals(((Paciente) obj).id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (id == null ? 0 : id.hashCode());
        return hash;
    }

    @Override
    public Paciente clone() throws CloneNotSupportedException {
        return (Paciente) super.clone();
    }

    @Override
    public String toString() {
        return nombre + " " + apPaterno + " " + apMaterno;
    }

}
