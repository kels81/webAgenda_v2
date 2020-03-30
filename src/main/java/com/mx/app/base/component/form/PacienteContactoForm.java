package com.mx.app.base.component.form;

import com.mx.app.base.domain.Paciente;
import com.mx.app.base.utils.Components;
import com.vaadin.data.Binder;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.textfieldformatter.*;
import org.vaadin.textfieldformatter.CustomStringBlockFormatter.Options;

/**
 *
 * @author Eduardo
 */
public class PacienteContactoForm extends FormLayout {

    private final TextField txtEmail;
    private final TextField txtTelFijo;
    private final TextField txtTelCelular;

    private final Label lblSeccion_1;

    private final Binder<Paciente> binder = new Binder<>();
    private Paciente paciente;

    public PacienteContactoForm() {
        addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

        lblSeccion_1 = Components.createLabelH4("MEDIOS DE CONTACTO");
        txtEmail = Components.createTextField("Email");
        txtTelFijo = Components.createTextField("Teléfono Fijo");
        txtTelCelular = Components.createTextField("Teléfono Celular");

        
        new CustomStringBlockFormatter(formatTelFijo()).extend(txtTelFijo);
        new CustomStringBlockFormatter(formatTelCelular()).extend(txtTelCelular);

        addComponents(lblSeccion_1,
                txtEmail,
                txtTelFijo,
                txtTelCelular
        );

        binder.bind(txtEmail, Paciente::getEmail, Paciente::setEmail);
        binder.bind(txtTelFijo, Paciente::getTelFijo, Paciente::setTelFijo);
        binder.bind(txtTelCelular, Paciente::getTelCelular, Paciente::setTelCelular);
        
//        binder.addStatusChangeListener(event -> {
//            boolean isValid = !event.hasValidationErrors();
//            System.out.println("isValidCont = " + isValid);
//            boolean hasChanges = binder.hasChanges();
//            System.out.println("hasChangesCont = " + hasChanges);
//        });
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        //binder.setBean(paciente);
        binder.readBean(paciente);
    }
    
    private Options formatTelFijo() {
        Options optTelFijo = new Options();
        optTelFijo.setBlocks(4, 4);
        optTelFijo.setNumericOnly(true);
        optTelFijo.setDelimiters("-");
        return optTelFijo;
    }
    
    private Options formatTelCelular() {
        Options optTelFijo = new Options();
        optTelFijo.setBlocks(2, 4, 4);
        optTelFijo.setNumericOnly(true);
        optTelFijo.setDelimiters("-");
        return optTelFijo;
    }

}
