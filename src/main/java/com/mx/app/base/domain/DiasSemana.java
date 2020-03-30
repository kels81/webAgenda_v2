/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.app.base.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ECortesHe
 */
public class DiasSemana {
    
    private final String[] DIAS_SEMANA = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    
    private String dia;
    private int index;
    private String diaCorto;
    private String  diaIniciales;

    public DiasSemana() {
    }
    
    public DiasSemana(String dia) {
        this.dia = dia;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDiaCorto() {
        return diaCorto;
    }

    public void setDiaCorto(String diaCorto) {
        this.diaCorto = diaCorto;
    }

    public String getDiaIniciales() {
        return diaIniciales;
    }

    public void setDiaIniciales(String diaIniciales) {
        this.diaIniciales = diaIniciales;
    }

    public List<DiasSemana> getDiasSemana(){
        List<DiasSemana> lstDiasSemanas = new ArrayList<>();
        int cont = 0;
        for (String strDia : DIAS_SEMANA) {
            DiasSemana diasSemana = new DiasSemana();
            diasSemana.setIndex(cont);
            diasSemana.setDia(strDia);
            diasSemana.setDiaCorto(strDia.substring(0, 3));
            diasSemana.setDiaIniciales(strDia.substring(0, 1));
            lstDiasSemanas.add(diasSemana);
            cont++;
        }
        return lstDiasSemanas;
    }
    
    
    
    
}
