package com.mx.app.base.component.form;

import com.mx.app.base.domain.Paciente;
import com.mx.app.base.utils.CURP;
import com.mx.app.base.utils.Components;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.Result;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import org.vaadin.textfieldformatter.DateFieldFormatter;

/**
 *
 * @author Eduardo
 */
public class PacienteGeneralForm extends FormLayout {

    public final Label lblSeccion;
    public final TextField txtNombre;
    public final TextField txtApPaterno;
    public final TextField txtApmaterno;
    public final DateField txtFechNac;
    //public final TextField txtFechNac2;   
    public final TextField txtEdad;
    public final RadioButtonGroup<String> rdbGenero;
    public final ComboBox<String> cmbEstados;
    public final TextField txtCURP;

    private Binder<Paciente> binder = new Binder<>();
    //private final Binder<Paciente> binder = new BeanValidationBinder<>(Paciente.class);
    private Paciente paciente;

    public PacienteGeneralForm() {
        addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        String strDateFormat = "dd/MM/yyyy";

        lblSeccion = Components.createLabelH4("INFORMACIÓN GENERAL");
        txtNombre = Components.createTextField("Nombre(s)");
        txtApPaterno = Components.createTextField("Apellido Paterno");
        txtApmaterno = Components.createTextField("Apellido Materno");
        //txtFechNac2 = Components.createTextField("Fecha Nacimiento");
        txtEdad = Components.createTextFieldDisabled("Edad");
        rdbGenero = Components.createRadioGenero("Género");
        cmbEstados = Components.createComboEstados("Lugar Nacimiento");
        txtCURP = Components.createTextFieldDisabled();

        txtFechNac = new DateField("Fecha Nacimiento") {
            @Override
            protected Result<LocalDate> handleUnparsableDateString(String dateString) {
                dateString = dateString.replaceFirst("(\\d{2})(\\d{2})(\\d+)", "$1/$2/$3");
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(strDateFormat);
                    LocalDate parsedAtServer = LocalDate.parse(dateString, formatter);
                    System.out.println("Result = " + Result.ok(parsedAtServer));
                    return Result.ok(parsedAtServer);
                } catch (DateTimeParseException e) {
                    System.out.println("Result = " + Result.error("Fecha Incorrecta"));
                    return Result.error("Fecha Incorrecta");

                }
            }
        };
        txtFechNac.setDateFormat(strDateFormat);
        txtFechNac.setPlaceholder(strDateFormat);
        txtFechNac.setRangeEnd(LocalDate.now());
        txtFechNac.setLocale(new Locale("es", "MX"));
        txtFechNac.addValueChangeListener(event -> getEdad(event.getValue()));

        //cmbEstados.addValueChangeListener(event -> getCURP(event.getValue().toString()));
        //new DateFieldFormatter("ddMMyyyy", LocalDate.of(1900, 01, 01), LocalDate.now(), "/").extend(txtFechNac2);
        Button btnCURP = new Button();
        btnCURP.setIcon(FontAwesome.MAGIC);
        btnCURP.addClickListener(event -> getCURP());

        HorizontalLayout wrapCURP = new HorizontalLayout();
        wrapCURP.setWidth(100.0f, Unit.PERCENTAGE);
        wrapCURP.setStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
        wrapCURP.setCaption("CURP");
        wrapCURP.setSpacing(false);
        wrapCURP.addComponents(txtCURP, btnCURP);
//        wrapCURP.addComponentsAndExpand(btnCURP);
        wrapCURP.setExpandRatio(txtCURP, 80.0f);
        wrapCURP.setExpandRatio(btnCURP, 20.0f);
        wrapCURP.setComponentAlignment(btnCURP, Alignment.MIDDLE_RIGHT);

        addComponents(lblSeccion,
                txtNombre,
                txtApPaterno,
                txtApmaterno,
                txtFechNac,
                txtEdad,
                rdbGenero,
                cmbEstados,
                wrapCURP
        );

        binder.bind(txtNombre, Paciente::getNombre, Paciente::setNombre);
        binder.bind(txtApPaterno, Paciente::getApPaterno, Paciente::setApPaterno);
        binder.bind(txtApmaterno, Paciente::getApMaterno, Paciente::setApMaterno);
        binder.bind(txtFechNac, Paciente::getFechaNacimiento, Paciente::setFechaNacimiento);
        binder.bind(rdbGenero, Paciente::getGenero, Paciente::setGenero);
        binder.bind(cmbEstados, Paciente::getLugarNacimiento, Paciente::setLugarNacimiento);
        binder.bind(txtCURP, Paciente::getCurp, Paciente::setCurp);
        
//        binder.addStatusChangeListener(event -> {
//            boolean isValid = !event.hasValidationErrors();
//            System.out.println("isValidGral = " + isValid);
//            boolean hasChanges = binder.hasChanges();
//            System.out.println("hasChangesGral = " + hasChanges);
//        });
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        //binder.setBean(paciente);
        this.binder.readBean(paciente);
        
//        for (Object comp : binder.getFields().toArray()) {
//            if (comp instanceof TextField) {
//                System.out.println("Caption = " + ((TextField) comp).getCaption());
//                System.out.println("Value = " + ((TextField) comp).getValue()); 
//            } else if (comp instanceof ComboBox) {
//                System.out.println("Caption = " + ((ComboBox) comp).getCaption());
//                System.out.println("Value = " + ((ComboBox) comp).getValue()); 
//            } else if (comp instanceof Component) {
//                System.out.println("Caption = " + ((Component) comp).getCaption());
//                System.out.println("Class = " + ((Component) comp).getClass().getTypeName()); 
//            }
//        }
    }
    
    private void getEdad(LocalDate fechaNacimiento) {
        LocalDate ahora = LocalDate.now();
        Period periodo = Period.between(fechaNacimiento, ahora);
        txtEdad.setValue(String.valueOf(periodo.getYears()).concat(" años"));
    }

    private void getCURP() {
        String curp = CURP.generarCURP(paciente.getApPaterno(),
                paciente.getApMaterno(),
                paciente.getNombre(),
                paciente.getGenero().equals("Masculino") ? "H" : "M",
                String.valueOf(paciente.getFechaNacimiento()),
                paciente.getLugarNacimiento());
        txtCURP.setValue(curp);
    }

}
