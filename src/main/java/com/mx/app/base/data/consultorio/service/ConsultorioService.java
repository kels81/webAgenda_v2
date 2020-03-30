/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.app.base.data.consultorio.service;

import com.mx.app.base.data.dummy.DummyDataGenerator;
import com.mx.app.base.domain.Consultorio;
import com.mx.app.base.utils.CURP;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author ECortesHe
 */
public class ConsultorioService {

    private static ConsultorioService instance;
    private static final Logger LOGGER = Logger.getLogger(ConsultorioService.class.getName());

    private final HashMap<Long, Consultorio> consultorios = new HashMap<>();
    private long nextId = 0;

    private ConsultorioService() {
    }

    /**
     * @return a reference to an example facade for Consultorio objects.
     */
    public static ConsultorioService getInstance() {
        if (instance == null) {
            instance = new ConsultorioService();
            instance.ensureTestData();
        }
        return instance;
    }

    /**
     * @return all available Consultorio objects.
     */
    public synchronized List<Consultorio> findAll() {
        return findAll(null);
    }

    /**
     * Finds all Consultorio's that match given filter.
     *
     * @param stringFilter filter that returned objects should match or
     * null/empty string if all objects should be returned.
     * @return list a Consultorio objects
     */
    public synchronized List<Consultorio> findAll(String stringFilter) {
        ArrayList<Consultorio> arrayList = new ArrayList<>();
        for (Consultorio paciente : consultorios.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || paciente.toString().toLowerCase().contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(paciente.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(ConsultorioService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, (Consultorio o1, Consultorio o2) -> (int) (o2.getIdConsultorio() - o1.getIdConsultorio()));
        return arrayList;
    }

    /**
     * Finds all Consultorio's that match given filter and limits the resultset.
     *
     * @param stringFilter filter that returned objects should match or
     * null/empty string if all objects should be returned.
     * @param start the index of first result
     * @param maxresults maximum result count
     * @return list a Consultorio objects
     */
    public synchronized List<Consultorio> findAll(String stringFilter, int start, int maxresults) {
        ArrayList<Consultorio> arrayList = new ArrayList<>();
        for (Consultorio paciente : consultorios.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || paciente.toString().toLowerCase().contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(paciente.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(ConsultorioService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<Consultorio>() {

            @Override
            public int compare(Consultorio o1, Consultorio o2) {
                return (int) (o2.getIdConsultorio() - o1.getIdConsultorio());
            }
        });
        int end = start + maxresults;
        if (end > arrayList.size()) {
            end = arrayList.size();
        }
        return arrayList.subList(start, end);
    }

    /**
     * @return the amount of all customers in the system
     */
    public synchronized long count() {
        return consultorios.size();
    }

    /**
     * Deletes a customer from a system
     *
     * @param value the Consultorio to be deleted
     */
    public synchronized void delete(Consultorio value) {
        consultorios.remove(value.getIdConsultorio());
    }

    /**
     * Persists or updates customer in the system. Also assigns an identifier
     * for new Consultorio instances.
     *
     * @param entry
     */
    public synchronized void save(Consultorio entry) {
        if (entry == null) {
            LOGGER.log(Level.SEVERE,
                    "Consultorio is null. Are you sure you have connected your form to the application as described in tutorial chapter 7?");
            return;
        }
        if (entry.getIdConsultorio() == null) {
            entry.setIdConsultorio(nextId++);
        }
        try {
            entry = (Consultorio) entry.clone();
            if (consultorios.containsValue(entry)) {
                consultorios.remove(entry); 
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        consultorios.put(entry.getIdConsultorio(), entry);
    }

    /**
     * Sample data generation
     */
    public void ensureTestData() {
        int start = 0;
        int limit = 2;

        if (findAll().isEmpty()) {
            for (int i = start; i < limit; i++) {
                Consultorio c = new Consultorio();
                c.setNombreConsultorio(DummyDataGenerator.randomNombreConsultorio());
                c.setDiasHorariosConsultorio(DummyDataGenerator.randomHorariosDia());
                c.setStatusConsultorio(DummyDataGenerator.randomStatus());

                save(c);
            }
        }
    }
}
