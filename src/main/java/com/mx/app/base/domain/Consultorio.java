package com.mx.app.base.domain;

import com.mx.app.base.data.Status;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public final class Consultorio implements Serializable, Cloneable {

    private Long idConsultorio;
    private String nombreConsultorio;
    private Map<String, List<LocalTime>> diasHorariosConsultorio;
    private Status statusConsultorio;

    public Long getIdConsultorio() {
        return idConsultorio;
    }

    public void setIdConsultorio(Long idConsultorio) {
        this.idConsultorio = idConsultorio;
    }

    public String getNombreConsultorio() {
        return nombreConsultorio;
    }

    public void setNombreConsultorio(String nombreConsultorio) {
        this.nombreConsultorio = nombreConsultorio;
    }

    public Map<String, List<LocalTime>> getDiasHorariosConsultorio() {
        return diasHorariosConsultorio;
    }

    public void setDiasHorariosConsultorio(Map<String, List<LocalTime>> diasHorariosConsultorio) {
        this.diasHorariosConsultorio = diasHorariosConsultorio;
    }

    public Status getStatusConsultorio() {
        return statusConsultorio;
    }

    public void setStatusConsultorio(Status statusConsultorio) {
        this.statusConsultorio = statusConsultorio;
    }


    
    public boolean isPersisted() {
        return idConsultorio != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.idConsultorio == null) {
            return false;
        }

        if (obj instanceof Consultorio && obj.getClass().equals(getClass())) {
            return this.idConsultorio.equals(((Consultorio) obj).idConsultorio);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (idConsultorio == null ? 0 : idConsultorio.hashCode());
        return hash;
    }

    @Override
    public Consultorio clone() throws CloneNotSupportedException {
        return (Consultorio) super.clone();
    }

    @Override
    public String toString() {
        return nombreConsultorio;
    }

}
