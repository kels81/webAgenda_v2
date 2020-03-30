/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.app.base.component.window;

import com.mx.app.base.data.dummy.DummyDataGenerator;
import com.mx.app.base.data.Status;
import com.mx.app.base.data.paciente.service.PacienteService;
import com.mx.app.base.domain.Paciente;
import com.mx.app.base.logic.PacienteLogic;
import com.mx.app.base.utils.*;
import com.vaadin.data.Binder;
import com.vaadin.server.*;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import java.time.LocalDate;
import java.time.Period;
import org.vaadin.textfieldformatter.CustomStringBlockFormatter;

/**
 *
 * @author Eduardo
 */
public class AgregarPacienteWindow extends Window {

    //private final PacienteGeneralForm pacienteGralForm = new PacienteGeneralForm();
    //private final PacienteContactoForm pacienteContactoForm = new PacienteContactoForm();
    private final PacienteService service = PacienteService.getInstance();

    private Paciente paciente;
    private final PacienteLogic viewLogic;
    private final String bnd;

    public Label lblSeccion_Gral;
    public Label lblSeccion_Cto;
    public TextField txtNombre;
    public TextField txtApPaterno;
    public TextField txtApmaterno;
    public DateField txtFechNac;
    public TextField txtEdad;
    public TextField txtCURP;
    private TextField txtEmail;
    private TextField txtTelFijo;
    private TextField txtTelCelular;
    public RadioButtonGroup<String> rdbGenero;
    public ComboBox<String> cmbEstados;

    private final Button btnCancelar= Components.createButtonNormal("Cancelar");
    private final Button btnGuardar = Components.createButtonPrimary("Guardar");
    private final Binder<Paciente> binder = new Binder<>();

    public AgregarPacienteWindow(PacienteLogic pacienteLogic, Paciente paciente, String bnd) {
        this.viewLogic = pacienteLogic;
        this.paciente = paciente;
        this.bnd = bnd;

        addStyleName("patient-window");
        setId("idWndPatient");
        Responsive.makeResponsive(this);

        setModal(true);
//        setCloseShortcut(KeyCode.ESCAPE, null);
        setResizable(false);
        setClosable(false);
        setHeight(90.0f, Sizeable.Unit.PERCENTAGE);

        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        content.setMargin(new MarginInfo(true, false, false, false));
        setContent(content);

        TabSheet tabDatosPaciente = Components.createTabSheetWindow();

        content.addComponent(tabDatosPaciente);
        content.setExpandRatio(tabDatosPaciente, 1f);

        tabDatosPaciente.addComponent(buildDatosPersonalesTab());
        tabDatosPaciente.addComponent(buildDatosGeneralesContactoTab());

        content.addComponent(buildFooter());

        bindItems();
        setPaciente();
    }

    private void setPaciente() {
        if (this.paciente != null) {
            this.setPaciente(paciente);
            //pacienteGralForm.setPaciente(this.paciente);
            //pacienteContactoForm.setPaciente(this.paciente);
        }
    }

    // [ PRIMER TAB ]
    private Component buildDatosPersonalesTab() {
        VerticalLayout root = new VerticalLayout();
        root.setCaption("Datos Paciente");
        root.setIcon(FontAwesome.USER);
        root.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);     //importante
        root.setSpacing(true);
        root.setMargin(true);
        //root.addStyleName("profile-form");

        FormLayout pacienteGralForm = Components.createFormLight();

        lblSeccion_Gral = Components.createLabelH4("INFORMACIÓN GENERAL");
        txtNombre = Components.createTextField("Nombre(s)");
        txtApPaterno = Components.createTextField("Apellido Paterno");
        txtApmaterno = Components.createTextField("Apellido Materno");
        txtEdad = Components.createTextFieldDisabled("Edad");
        rdbGenero = Components.createRadioGenero("Género");
        cmbEstados = Components.createComboEstados("Lugar Nacimiento");
        txtCURP = Components.createTextFieldDisabled();
        txtFechNac = Components.createDateFieldNac("Fecha Nacimiento");
        txtFechNac.addValueChangeListener(event -> getEdad(event.getValue()));

        Button btnCURP = Components.createButtonIconNormal();
        btnCURP.setIcon(FontAwesome.MAGIC);
        btnCURP.addClickListener(e -> getCURP());

        HorizontalLayout wrapCURP = Components.createHorizontalWrapping("CURP", txtCURP, btnCURP);
        wrapCURP.setExpandRatio(txtCURP, 80.0f);
        wrapCURP.setExpandRatio(btnCURP, 20.0f);
        wrapCURP.setComponentAlignment(btnCURP, Alignment.MIDDLE_RIGHT);

        pacienteGralForm.addComponents(lblSeccion_Gral,
                txtNombre,
                txtApPaterno,
                txtApmaterno,
                txtFechNac,
                txtEdad,
                rdbGenero,
                cmbEstados,
                wrapCURP
        );

        root.addComponents(pacienteGralForm);
        return root;
    }

    // [ SEGUNDO TAB ]
    private Component buildDatosGeneralesContactoTab() {
        VerticalLayout root = new VerticalLayout();
        root.setCaption("Contacto Paciente");
        root.setIcon(FontAwesome.PHONE);
        root.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);
        root.setMargin(true);

        FormLayout pacienteContactoForm = Components.createFormLight();

        lblSeccion_Cto = Components.createLabelH4("MEDIOS DE CONTACTO");
        txtEmail = Components.createTextField("Email");
        txtTelFijo = Components.createTextField("Teléfono Fijo");
        txtTelCelular = Components.createTextField("Teléfono Celular");

        new CustomStringBlockFormatter(Components.formatTelFijo()).extend(txtTelFijo);
        new CustomStringBlockFormatter(Components.formatTelCelular()).extend(txtTelCelular);

        pacienteContactoForm.addComponents(lblSeccion_Cto,
                txtEmail,
                txtTelFijo,
                txtTelCelular
        );

        root.addComponents(pacienteContactoForm);
        return root;
    }

    private void bindItems() {
        binder.bind(txtNombre, Paciente::getNombre, Paciente::setNombre);
        binder.bind(txtApPaterno, Paciente::getApPaterno, Paciente::setApPaterno);
        binder.bind(txtApmaterno, Paciente::getApMaterno, Paciente::setApMaterno);
        binder.bind(txtFechNac, Paciente::getFechaNacimiento, Paciente::setFechaNacimiento);
        binder.bind(rdbGenero, Paciente::getGenero, Paciente::setGenero);
        binder.bind(cmbEstados, Paciente::getLugarNacimiento, Paciente::setLugarNacimiento);
        binder.bind(txtCURP, Paciente::getCurp, Paciente::setCurp);
        binder.bind(txtEmail, Paciente::getEmail, Paciente::setEmail);
        binder.bind(txtTelFijo, Paciente::getTelFijo, Paciente::setTelFijo);
        binder.bind(txtTelCelular, Paciente::getTelCelular, Paciente::setTelCelular);
        
        binder.addStatusChangeListener(event -> {
            boolean isValid = !event.hasValidationErrors();
            boolean hasChanges = binder.hasChanges();
            btnGuardar.setEnabled(hasChanges && isValid);
            //btnCancelar.setEnabled(hasChanges);
        });
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        //binder.setBean(paciente);
        binder.readBean(paciente);
    }

    private void getEdad(LocalDate fechaNacimiento) {
        LocalDate ahora = LocalDate.now();
        Period periodo = Period.between(fechaNacimiento, ahora);
        txtEdad.setValue(String.valueOf(periodo.getYears()).concat(" años"));
    }

    private void getCURP() {
        String curp = CURP.generarCURP(txtApPaterno.getValue(),
                txtApmaterno.getValue(),
                txtNombre.getValue(),
                rdbGenero.getValue().equals("Masculino") ? "H" : "M",
                String.valueOf(txtFechNac.getValue()),
                cmbEstados.getValue().toString());
        txtCURP.setValue(curp);
    }

    private Component buildFooter() {
        HorizontalLayout footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
        footer.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);

        btnCancelar.addClickListener(e -> close());
        //btnCancelar.setClickShortcut(ShortcutAction.KeyCode.ESCAPE, null);

        btnGuardar.addClickListener(e -> guardar());
//        btnGuardar.setClickShortcut(KeyCode.ENTER, null);

        footer.addComponents(btnCancelar, btnGuardar);
        footer.setExpandRatio(btnCancelar, 1);
        footer.setComponentAlignment(btnCancelar, Alignment.TOP_RIGHT);
        return footer;
    }

    private void guardar() {
        paciente.setAvatar(DummyDataGenerator.getAvatar(rdbGenero.getValue()));
        paciente.setStatus(Status.Activo);
        if (paciente != null && binder.writeBeanIfValid(paciente)) {      //SE USA SIEMPRE Y CUANDO EL BINDER Y LOS BINDER.BIND ESTEN AQUI MISMO TAMBIEN BINDER.READ
            service.save(paciente);
        }
        if (bnd.equals("grid")) {
            viewLogic.updateList();
        } else {
            viewLogic.updateCardList();
        }
        close();
    }

    public static void open(PacienteLogic pacienteLogic, Paciente paciente, String bnd) {       //BND es temporal
        Window w = new AgregarPacienteWindow(pacienteLogic, paciente, bnd);
        UI.getCurrent().addWindow(w);
        w.focus();
    }

}
