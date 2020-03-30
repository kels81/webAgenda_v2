/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.app.base.view.configuracion;

import com.mx.app.base.domain.DiasSemana;
import com.google.common.eventbus.Subscribe;
import com.mx.app.base.component.HorarioDia;
import com.mx.app.base.data.Status;
import com.mx.app.base.data.consultorio.service.ConsultorioService;
import com.mx.app.base.domain.Consultorio;
import com.mx.app.base.event.AppEvent;
import com.mx.app.base.event.AppEvent.BrowserResizeEvent;
import com.mx.app.base.event.AppEventBus;
import com.mx.app.base.utils.Components;
import com.mx.app.base.view.AppViewType;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *
 * @author ECortesHe
 */
@SuppressWarnings("serial")
public final class ConfiguracionView extends VerticalLayout implements View {

    private final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private final ConsultorioService service = ConsultorioService.getInstance();
    private final Binder<Consultorio> binder = new Binder<>();

    private Consultorio consultorio;

    private final Button btnCancelar = Components.createButtonNormal("Cancelar");
    private final Button btnGuardar = Components.createButtonPrimary("Guardar");

    private TextField txtNombre;
    private ComboBox<LocalTime> cmbHorasDisponibles;
    private CheckBoxGroup<DiasSemana> chbDias;
    private Button btnAgregarHoras;

    private HorizontalLayout htlHorarios;
    private HorizontalLayout wrapHoras;

    private HorarioDia horaLun;
    private HorarioDia horaMar;
    private HorarioDia horaMie;
    private HorarioDia horaJue;
    private HorarioDia horaVie;
    private HorarioDia horaSab;
    private HorarioDia horaDom;

    public ConfiguracionView() {
        setSizeFull();
        addStyleName("configuracion");
        setMargin(false);
        setSpacing(false);
        AppEventBus.register(this);   //NECESARIO PARA CONOCER LA ORIENTACION Y RESIZE DEL BROWSER Y ENVIAR INFORMACION ENTRE CLASES
        Responsive.makeResponsive(this);

        addComponent(buildHeader());
        addComponentsAndExpand(buildForm());
        addComponent(buildFooter());

        bindItems();
    }

    @Subscribe
    public void createTransactionEvent(final AppEvent.ConsultorioEditEvent event) {
        if (event.getConsultorio() != null) {
            this.setConsultorio(event.getConsultorio());
        } else {    //SI CONSULTORIO ES NULL, SIGNIFICA QUE ES NUEVO CONSULTORIO
            //txtNombre.setValue("");
            //limpiarDiasSemana();
            this.setConsultorio(new Consultorio());
        }
    }

    private Component buildHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.addStyleName("viewheader");
        Responsive.makeResponsive(header);

        header.addComponent(Components.createLabelHeader("Configuración"));
        return header;
    }

    private Component buildForm() {
        CssLayout root = new CssLayout();
        root.setSizeFull();
        root.addStyleName("horariosView");

        root.addComponents(
                buildConsultorioTab(),
                //buildConfigHorarios(),
                buildHorarioSemanalTab()
        );
        return root;
    }

    private Component buildConsultorioTab() {
        HorizontalLayout formLayConsultorio = Components.createHorizontalForm();
        formLayConsultorio.setId("idConsultorio");
        formLayConsultorio.addStyleName("consultorio-form");

        Label label = Components.createLabelH4("INFORMACION CONSULTORIO");

        FormLayout consultorioForm = Components.createFormLight();
        txtNombre = Components.createTextField("Consultorio");
        cmbHorasDisponibles = Components.createComboHoras("Horarios");
        chbDias = Components.createCheckBoxGroupDiasSemana();
        //chbDias.setItemCaptionGenerator(diaSemana -> diaSemana.getDia());

        btnAgregarHoras = Components.createButtonNormal("Horario");
        btnAgregarHoras.setIcon(FontAwesome.PLUS);
        btnAgregarHoras.addClickListener(e -> setHorasDias(chbDias.getSelectedItems(), null));

        wrapHoras = Components.createHorizontalWrapping("Días Laborales", chbDias, btnAgregarHoras);
        wrapHoras.setExpandRatio(chbDias, 1);
        //wrapHoras.setExpandRatio(btnAgregarHoras, 20.0f);
        wrapHoras.setComponentAlignment(btnAgregarHoras, Alignment.MIDDLE_RIGHT);

        consultorioForm.addComponents(
                txtNombre,
                cmbHorasDisponibles,
                wrapHoras
        );

        formLayConsultorio.addComponents(label, consultorioForm);
        formLayConsultorio.setExpandRatio(label, 20.0f);
        formLayConsultorio.setExpandRatio(consultorioForm, 80.0f);

        return formLayConsultorio;
    }

//    private Component buildConfigHorarios() {
//        HorizontalLayout formLayConfigHora = Components.createHorizontalForm();
//        formLayConfigHora.setId("idHorarios");
//        formLayConfigHora.addStyleName("consultorio-form");
//
//        Label label = Components.createLabelH4("HORARIO SEMANAL");
//        
//        FormLayout horaForm = Components.createFormLight();
//        cmbHorasDisponibles = Components.createComboHoras("Horarios");
//
//        chbDias = new CheckBoxGroup<>();
//        chbDias.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
//        chbDias.setItems(Arrays.asList(DIAS_SEMANA));
//
//        Button btnAgregarHoras = Components.createButtonIconCaptionNormal("Horario");
//        btnAgregarHoras.setIcon(FontAwesome.PLUS);
//        btnAgregarHoras.addClickListener(e -> setHoras(chbDias.getSelectedItems()));
//
//        HorizontalLayout wrapHoras = Components.createHorizontalWrapping("Dias Laborales", chbDias, btnAgregarHoras);
//        wrapHoras.setExpandRatio(chbDias, 90.0f);
//        wrapHoras.setExpandRatio(btnAgregarHoras, 10.0f);
//        wrapHoras.setComponentAlignment(btnAgregarHoras, Alignment.MIDDLE_RIGHT);
//
//        horaForm.addComponents(
//                cmbHorasDisponibles,
//                wrapHoras
//        );
//
//        formLayConfigHora.addComponents(label, horaForm);
//        formLayConfigHora.setExpandRatio(label, 20.0f);
//        formLayConfigHora.setExpandRatio(horaForm, 80.0f);
//
//        return formLayConfigHora;
//    }
    private Component buildHorarioSemanalTab() {
        htlHorarios = Components.createHorizontalFormIntermedio();

        horaLun = new HorarioDia(new DiasSemana().getDiasSemana().get(0).getDia());
        horaMar = new HorarioDia(new DiasSemana().getDiasSemana().get(1).getDia());
        horaMie = new HorarioDia(new DiasSemana().getDiasSemana().get(2).getDia());
        horaJue = new HorarioDia(new DiasSemana().getDiasSemana().get(3).getDia());
        horaVie = new HorarioDia(new DiasSemana().getDiasSemana().get(4).getDia());
        horaSab = new HorarioDia(new DiasSemana().getDiasSemana().get(5).getDia());
        horaDom = new HorarioDia(new DiasSemana().getDiasSemana().get(6).getDia());

        htlHorarios.addComponents(
                horaLun,
                horaMar,
                horaMie,
                horaJue,
                horaVie,
                horaSab,
                horaDom
        );
        return htlHorarios;
    }

    private void setHorasDias(Set<DiasSemana> dias, List<LocalTime> lstHorasDia) {
        for (DiasSemana dia : dias) {
            HorarioDia comp = getDiasSemana().get(dia.getIndex());
            if (lstHorasDia != null) {
                for (LocalTime hora : lstHorasDia) {
                    comp.setValue(hora);
                }
            } else {
                LocalTime hora = LocalTime.parse(cmbHorasDisponibles.getValue().toString(), TIME_FORMATTER);
                comp.setValue(hora);
            }
        }
    }

    private List<HorarioDia> getDiasSemana() {
        List<HorarioDia> lstDiasSemana = new ArrayList<>();
        lstDiasSemana.add(horaLun);
        lstDiasSemana.add(horaMar);
        lstDiasSemana.add(horaMie);
        lstDiasSemana.add(horaJue);
        lstDiasSemana.add(horaVie);
        lstDiasSemana.add(horaSab);
        lstDiasSemana.add(horaDom);
        return lstDiasSemana;
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
        binder.bind(txtNombre, Consultorio::getNombreConsultorio, Consultorio::setNombreConsultorio);

        binder.addStatusChangeListener(event -> {
            boolean isValid = !event.hasValidationErrors();
            boolean hasChanges = binder.hasChanges();
            //btnGuardar.setEnabled(hasChanges && isValid);
        });
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
        binder.readBean(consultorio);
        limpiarDiasSemana();
        if (consultorio.getIdConsultorio() != null) {
            for (Map.Entry<String, List<LocalTime>> entry : consultorio.getDiasHorariosConsultorio().entrySet()) {
                Set<DiasSemana> dias = new HashSet<>();
                for (DiasSemana dia : new DiasSemana().getDiasSemana()) {
                    if (dia.getDia().equals(entry.getKey())) {
                        dias.add(dia);
                    }
                }
                setHorasDias(dias, entry.getValue());
            }
        }
    }


    private Map<String, List<LocalTime>> getValuesNvoConsultorio() {
        Map<String, List<LocalTime>> mapDiaHoras = new HashMap<>();
        List<HorarioDia> values = new ArrayList<>();
        for (int i = 0; i < htlHorarios.getComponentCount(); i++) {
            Component component = htlHorarios.getComponent(i);
            if (component instanceof HorarioDia) {
                values.add((HorarioDia) component);
            }
        }

        for (HorarioDia hora : values) {
            List<LocalTime> lstTime = new ArrayList<>();
            if (!hora.getValues().isEmpty()) {
                for (String token : hora.getValues()) {
                    lstTime.add(LocalTime.parse(token, TIME_FORMATTER));
                };
                mapDiaHoras.put(hora.getDiaSemana(), lstTime);
            }
        }

        return mapDiaHoras;
    }

    private void guardar() {
        if (consultorio.getIdConsultorio() == null) {
            Map<String, List<LocalTime>> mapDiaHoras = getValuesNvoConsultorio();
            consultorio.setDiasHorariosConsultorio(mapDiaHoras);
            consultorio.setStatusConsultorio(Status.Activo);
        }

        if (consultorio != null && binder.writeBeanIfValid(consultorio)) {      //SE USA SIEMPRE Y CUANDO EL BINDER Y LOS BINDER.BIND ESTEN AQUI MISMO TAMBIEN BINDER.READ
            service.save(consultorio);
        }

        cancelar();
    }
    
    private void cancelar() {
        UI.getCurrent().getNavigator().navigateTo(AppViewType.CONSULTORIO.getViewName());
    }

    private void limpiarDiasSemana() {
        cmbHorasDisponibles.setValue(null);
        chbDias.deselectAll();
        for (HorarioDia horas : getDiasSemana()) {
            horas.htlHoras.removeAllComponents();
        }

    }

    @Subscribe
    public void browserWindowResized(final BrowserResizeEvent event) {
        float browserWindowWidth = Page.getCurrent().getBrowserWindowWidth();
        if (browserWindowWidth < 800) {
            chbDias.setWidth(Math.round(browserWindowWidth / 2), Unit.PIXELS);
            chbDias.setItemCaptionGenerator(diaSemana -> diaSemana.getDiaIniciales());

        } else {
            chbDias.setWidth(100, Unit.PERCENTAGE);
            chbDias.setItemCaptionGenerator(diaSemana -> diaSemana.getDiaCorto());
        }
    }

    @Override
    public void enter(final ViewChangeEvent event) {
    }

}
