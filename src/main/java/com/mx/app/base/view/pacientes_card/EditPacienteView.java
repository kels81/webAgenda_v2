/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.app.base.view.pacientes_card;

import com.google.common.eventbus.Subscribe;
import com.mx.app.base.data.dummy.DummyDataGenerator;
import com.mx.app.base.data.Status;
import com.mx.app.base.data.paciente.service.PacienteService;
import com.mx.app.base.domain.Paciente;
import com.mx.app.base.event.AppEvent.PacienteEditEvent;
import com.mx.app.base.event.AppEventBus;
import com.mx.app.base.logic.PacienteLogic;
import com.mx.app.base.utils.*;
import com.mx.app.base.view.AppViewType;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import java.time.*;
import java.util.*;
import org.vaadin.textfieldformatter.CustomStringBlockFormatter;

/**
 *
 * @author ECortesHe
 */
@SuppressWarnings("serial")
public class EditPacienteView extends VerticalLayout implements View {

    private final Button btnCancelar = Components.createButtonNormal("Cancelar");
    private final Button btnGuardar = Components.createButtonPrimary("Guardar");
    private final Binder<Paciente> binder = new Binder<>();
    private final PacienteService service = PacienteService.getInstance();

    private Paciente paciente;
    private PacienteLogic viewLogic;
    private String bnd;

    private TextField txtNombre;
    private TextField txtApPaterno;
    private TextField txtApmaterno;
    private TextField txtEdad;
    private TextField txtCURP;
    private TextField txtEmail;
    private TextField txtTelFijo;
    private TextField txtTelCelular;

    private DateField txtFechNac;
    
    private RadioButtonGroup<String> rdbGenero;
    private RadioButtonGroup<String> rdbConsultorio;
    
    private ComboBox<String> cmbEstados;
    private ComboBox<String> cmbServicios;
    private ComboBox<String> cmbCostos;

    public EditPacienteView() {
        setSizeFull();
        addStyleName("edit_pacientes");
        setMargin(false);
        setSpacing(false);
        AppEventBus.register(this);
        Responsive.makeResponsive(this);

        addComponent(buildHeader());
        addComponentsAndExpand(buildForm());
        addComponent(buildFooter());

        bindItems();
    }

    @Subscribe
    public void createTransactionReport(final PacienteEditEvent event) {
        this.viewLogic = event.getLogic();
        this.bnd = event.getBnd();

        if (event.getPaciente() != null) {
            this.setPaciente(event.getPaciente());
        }
    }

    private Component buildHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.addStyleName("viewheader");
        Responsive.makeResponsive(header);

        header.addComponent(Components.createLabelHeader("Pacientes"));

        return header;
    }

    private Component buildForm() {
        CssLayout root = new CssLayout();
        root.setSizeFull();
        root.addStyleName("formView");

        root.addComponents(
                buildDatosPersonalesTab(),
                buildDatosContactoTab(),
                buildDatosConsultorioTab()
        );
        
        return root;
    }

    private Component buildDatosPersonalesTab() {
        HorizontalLayout formLayGeneral = Components.createHorizontalForm();
        formLayGeneral.setId("idFormGeneral");
        formLayGeneral.addStyleName("paciente-form");

        FormLayout pacienteGralForm = Components.createFormLight();
        Label label = Components.createLabelH4("INFORMACIÓN GENERAL");
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

        pacienteGralForm.addComponents(txtNombre,
                txtApPaterno,
                txtApmaterno,
                txtFechNac,
                txtEdad,
                rdbGenero,
                cmbEstados,
                wrapCURP
        );

//        formGeneral.addComponents(pacienteGralForm, buildDatosContactoTab());  //ESTE ES UNEJEMPLO SIEMPRE Y CUANDO EL SEGUNDO ESTE EN UN HORIZONTALLAYOUT
        formLayGeneral.addComponents(label,pacienteGralForm);
        formLayGeneral.setExpandRatio(label, 20.0f);
        formLayGeneral.setExpandRatio(pacienteGralForm, 80.0f);

        return formLayGeneral;
    }

    private Component buildDatosContactoTab() {
        HorizontalLayout formLayContacto = Components.createHorizontalFormIntermedio();
        formLayContacto.setId("idFormContacto");
        formLayContacto.addStyleName("paciente-form");

        FormLayout pacienteContactoForm = Components.createFormLight();
        Label label = Components.createLabelH4("MEDIOS DE CONTACTO");
        txtEmail = Components.createTextField("Email");
        txtTelFijo = Components.createTextField("Teléfono Fijo");
        txtTelCelular = Components.createTextField("&nbsp;&nbsp;&nbsp;&nbsp;Teléfono Celular");

        new CustomStringBlockFormatter(Components.formatTelFijo()).extend(txtTelFijo);
        new CustomStringBlockFormatter(Components.formatTelCelular()).extend(txtTelCelular);

        pacienteContactoForm.addComponents(
                txtEmail,
                txtTelFijo,
                txtTelCelular
        );

        formLayContacto.addComponents(label, pacienteContactoForm);
        formLayContacto.setExpandRatio(label, 20.0f);
        formLayContacto.setExpandRatio(pacienteContactoForm, 80.0f);

        return formLayContacto;
    }

    private Component buildDatosConsultorioTab() {
        HorizontalLayout formLayServicio = Components.createHorizontalFormIntermedio();
        formLayServicio.setId("idFormServicio");
        formLayServicio.addStyleName("paciente-form");

        Map<String, List<String>> lstServicios = new HashMap<>();
        lstServicios.put("Asesoria tesis", Arrays.asList("$ 600.00", "$ 700.00"));
        lstServicios.put("Terapia individual", Arrays.asList("$ 700.00", "$ 800.00"));
        lstServicios.put("Terapia pareja", Arrays.asList("$ 900.00", "$ 1,000.00"));
        
        List<String> lstConsultorios = Arrays.asList("Condesa", "Coyoacan", "San Angel");

        FormLayout pacienteSevicioForm = Components.createFormLight();
        Label label = Components.createLabelH4("Servicio");
        rdbConsultorio= new RadioButtonGroup<>("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Consultorio");
        rdbConsultorio.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
        rdbConsultorio.setCaptionAsHtml(true);
        rdbConsultorio.setItems(lstConsultorios);
        rdbConsultorio.setSelectedItem(lstConsultorios.get(0));
        
        cmbServicios = new ComboBox("Servicio", lstServicios.keySet());
        cmbServicios.setEmptySelectionAllowed(false);
        cmbServicios.addValueChangeListener(e -> {
            String servicio = e.getValue();
            boolean enabled = servicio != null && !servicio.isEmpty();
            cmbCostos.setEnabled(enabled);
            if (enabled) {
                cmbCostos.setItems(lstServicios.get(servicio));
            }

        });
        cmbCostos = new ComboBox("Costo", Collections.emptyList());
        cmbCostos.setCaptionAsHtml(true);
        cmbCostos.setEmptySelectionAllowed(false);
        cmbCostos.setEnabled(false);

        pacienteSevicioForm.addComponents(
                rdbConsultorio,
                cmbServicios,
                cmbCostos
        );

        formLayServicio.addComponents(label, pacienteSevicioForm);
        formLayServicio.setExpandRatio(label, 20.0f);
        formLayServicio.setExpandRatio(pacienteSevicioForm, 80.0f);

        return formLayServicio;
    }

    private Component buildFooter() {
        HorizontalLayout footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addStyleName("footer");
        footer.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);

        btnCancelar.addClickListener(e -> cancelar());
        //btnCancelar.setClickShortcut(ShortcutAction.KeyCode.ESCAPE, null);

        btnGuardar.addClickListener(e -> guardar());
//        btnGuardar.setClickShortcut(KeyCode.ENTER, null);

        footer.addComponents(btnCancelar, btnGuardar);
        footer.setExpandRatio(btnCancelar, 1);
        footer.setComponentAlignment(btnCancelar, Alignment.TOP_RIGHT);
        return footer;
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
        });
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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
        cancelar();
    }

    private void cancelar() {
        if (bnd.equals("grid")) {
            UI.getCurrent().getNavigator().navigateTo(AppViewType.PACIENTES.getViewName());
        } else {
            UI.getCurrent().getNavigator().navigateTo(AppViewType.PACIENTES_CARD.getViewName());
        }
    }

    @Override
    public void enter(final ViewChangeEvent event) {
    }

}
