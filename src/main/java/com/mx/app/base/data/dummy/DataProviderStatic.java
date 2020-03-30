/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.app.base.data.dummy;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author ECortesHe
 */
public class DataProviderStatic {

    public List<String> getEstados() {
        List<String> lstEstados = Arrays.asList(DummyDataStatic.estados());
        return lstEstados;
    }

    public List<String> getParentesco() {
        List<String> lstParentesco = Arrays.asList(DummyDataStatic.parentesco());
        return lstParentesco;
    }

    public List<String> getEstadoCivil() {
        List<String> lstEdoCivil = Arrays.asList(DummyDataStatic.estadoCivil());
        return lstEdoCivil;
    }

    public List<String> getGenero() {
        List<String> lstGenero = Arrays.asList(DummyDataStatic.genero());
        return lstGenero;
    }

    public List<String> getReligion() {
        List<String> lstReligion = Arrays.asList(DummyDataStatic.religión());
        return lstReligion;
    }
    
    public Stream<LocalTime> getHorasHabiles() {
        Stream<LocalTime> strHoras = IntStream.range(7, 22).mapToObj(h -> LocalTime.of(h, 0));
        return strHoras;
    }

}
