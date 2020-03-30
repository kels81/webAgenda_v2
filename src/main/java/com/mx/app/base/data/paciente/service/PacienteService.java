/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.app.base.data.paciente.service;

import com.mx.app.base.data.dummy.DummyDataGenerator;
import com.mx.app.base.domain.Paciente;
import com.mx.app.base.utils.CURP;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author ECortesHe
 */
public class PacienteService {

    private static PacienteService instance;
    private static final Logger LOGGER = Logger.getLogger(PacienteService.class.getName());

    private final HashMap<Long, Paciente> pacientes = new HashMap<>();
    private long nextId = 0;

    private PacienteService() {
    }

    /**
     * @return a reference to an example facade for Paciente objects.
     */
    public static PacienteService getInstance() {
        if (instance == null) {
            instance = new PacienteService();
            instance.ensureTestData();
        }
        return instance;
    }

    /**
     * @return all available Paciente objects.
     */
    public synchronized List<Paciente> findAll() {
        return findAll(null);
    }

    /**
     * Finds all Paciente's that match given filter.
     *
     * @param stringFilter filter that returned objects should match or
     * null/empty string if all objects should be returned.
     * @return list a Paciente objects
     */
    public synchronized List<Paciente> findAll(String stringFilter) {
        ArrayList<Paciente> arrayList = new ArrayList<>();
        for (Paciente paciente : pacientes.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || paciente.toString().toLowerCase().contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(paciente.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(PacienteService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, (Paciente o1, Paciente o2) -> (int) (o2.getId() - o1.getId()));
        return arrayList;
    }

    /**
     * Finds all Paciente's that match given filter and limits the resultset.
     *
     * @param stringFilter filter that returned objects should match or
     * null/empty string if all objects should be returned.
     * @param start the index of first result
     * @param maxresults maximum result count
     * @return list a Paciente objects
     */
    public synchronized List<Paciente> findAll(String stringFilter, int start, int maxresults) {
        ArrayList<Paciente> arrayList = new ArrayList<>();
        for (Paciente paciente : pacientes.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || paciente.toString().toLowerCase().contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(paciente.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(PacienteService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<Paciente>() {

            @Override
            public int compare(Paciente o1, Paciente o2) {
                return (int) (o2.getId() - o1.getId());
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
        return pacientes.size();
    }

    /**
     * Deletes a customer from a system
     *
     * @param value the Paciente to be deleted
     */
    public synchronized void delete(Paciente value) {
        pacientes.remove(value.getId());
    }

    /**
     * Persists or updates customer in the system. Also assigns an identifier
     * for new Paciente instances.
     *
     * @param entry
     */
    public synchronized void save(Paciente entry) {
        if (entry == null) {
            LOGGER.log(Level.SEVERE,
                    "Paciente is null. Are you sure you have connected your form to the application as described in tutorial chapter 7?");
            return;
        }
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (Paciente) entry.clone();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        pacientes.put(entry.getId(), entry);
    }
    
    private String buildEmail(Paciente p) {
        return p.getNombre().toLowerCase() + "." + DummyDataGenerator.eliminarAcentos(p.getApPaterno().toLowerCase()) 
                            + "@" + DummyDataGenerator.randomMailCompany().toLowerCase() + ".com";
    }

    /**
     * Sample data generation
     */
    public void ensureTestData() {
        int start = 0;
        int limit = 5;
        
        if (findAll().isEmpty()) {
            for (int i = start; i < limit; i++) {
                Paciente p = new Paciente();
                p.setNombre(DummyDataGenerator.randomNombrePaciente());
                p.setApPaterno(DummyDataGenerator.randomApellidos());
                p.setApMaterno(DummyDataGenerator.randomApellidos());
                p.setGenero(DummyDataGenerator.findGenero(p.getNombre()));
                p.setFechaNacimiento(DummyDataGenerator.randomFechaNacimiento());
                p.setLugarNacimiento(DummyDataGenerator.randomLugarNacimiento());
                p.setCurp(CURP.generarCURP(p.getApPaterno(), p.getApMaterno(), p.getNombre(), p.getGenero().equals("Masculino") ? "H" : "M", String.valueOf(p.getFechaNacimiento()), p.getLugarNacimiento()));
                p.setAvatar(DummyDataGenerator.getAvatar(p.getGenero()));
                p.setStatus(DummyDataGenerator.randomStatus());
                p.setEmail(buildEmail(p));
                p.setTelFijo(DummyDataGenerator.formatTelefono(DummyDataGenerator.randomNumeroTelefonico(8), true));
                p.setTelCelular(DummyDataGenerator.formatTelefono(DummyDataGenerator.randomNumeroTelefonico(8), false));
                
                                
                save(p);
            }
        }
    }
}
