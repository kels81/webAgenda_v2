package com.mx.app.base.view.configuracion;

import com.mx.app.base.component.HorarioDia;
import com.mx.app.base.utils.Components;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.*;
import com.vaadin.shared.Registration;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("serial")
public final class ConfiguracionView2 extends VerticalLayout implements View {

    private static String[] LANGUAGES = {"PHP", "Java", "JavaScript", "Scala", "Python", "C", "Ruby", "C++"};

    private final Button btnCancelar = Components.createButtonNormal("Cancelar");
    private final Button btnGuardar = Components.createButtonPrimary("Guardar");

    private ComboBox<LocalTime> cmbIniHora;
    private ComboBox<LocalTime> cmbFinHora;

    private HorizontalLayout htlLun;
    private HorizontalLayout htlMar;
    private HorizontalLayout htlMie;
    private HorizontalLayout htlJue;
    private HorizontalLayout htlVie;
    private HorizontalLayout htlSab;
    private HorizontalLayout htlDom;

    public TextField txtNombre;

    private Registration valueChangeListenerRegistration;
    private Set<Registration> tokenChangeListeners;

    public ConfiguracionView2() {
        setSizeFull();
        addStyleName("configuracion");
        setMargin(false);
        setSpacing(false);

        addComponent(buildHeader());
        addComponentsAndExpand(buildForm());
        addComponent(buildFooter());

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
        //root.addStyleName("horariosView");

        root.addComponents(buildConfigTab());

        return root;
    }

    private Component buildConfigTab() {
        VerticalLayout consultorioForm = new VerticalLayout();

        Label label = Components.createLabelH4("CONFIGURAR HORARIOS");
        txtNombre = Components.createTextField("Consultorio");

        consultorioForm.addComponents(
                label,
                txtNombre,
                createHorarios()
        );

        return consultorioForm;
    }

    private Component createHorarios() {
        VerticalLayout vtlHorarios = new VerticalLayout();
        vtlHorarios.setMargin(false);
        HorizontalLayout htlConfig = new HorizontalLayout();
        htlConfig.setMargin(false);

        htlLun = new HorizontalLayout();
        htlMar = new HorizontalLayout();
        htlMie = new HorizontalLayout();
        htlJue = new HorizontalLayout();
        htlVie = new HorizontalLayout();
        htlSab = new HorizontalLayout();
        htlDom = new HorizontalLayout();

        Button btnLun = new Button("Lunes");
        Button btnMar = new Button("Martes");
        Button btnMie = new Button("Miercoles");
        Button btnJue = new Button("Jueves");
        Button btnVie = new Button("Viernes");
        Button btnSab = new Button("Sabado");
        Button btnDom = new Button("Domingo");

        cmbIniHora = new ComboBox<>();
        cmbIniHora.setEmptySelectionAllowed(false);
        cmbIniHora.setTextInputAllowed(false);
        cmbIniHora.setItems(IntStream.range(7, 19).mapToObj(h -> LocalTime.of(h, 0)));
        cmbIniHora.addValueChangeListener(e -> setFinHora(e.getValue()));

        cmbFinHora = new ComboBox<>();
        cmbFinHora.setEmptySelectionAllowed(false);
        cmbFinHora.setTextInputAllowed(false);
        cmbFinHora.setEnabled(false);
        cmbFinHora.addValueChangeListener(e -> createHoras(e.getValue()));
        //cmbFinHora.setItems(IntStream.range(7, 17).mapToObj(h -> LocalTime.of(h, 0)));

        List<String> lstDuraciones = Arrays.asList("60 min", "30 min", "15 min");
        CheckBoxGroup<String> chbDuracion = new CheckBoxGroup<>();
        chbDuracion.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
        chbDuracion.setItems(lstDuraciones);

        htlConfig.addComponents(cmbIniHora, cmbFinHora, chbDuracion);

        htlLun.addComponent(btnLun);
        htlMar.addComponents(btnMar); 
        htlMie.addComponent(btnMie);
        htlJue.addComponent(btnJue);
        htlVie.addComponent(btnVie);
        htlSab.addComponent(btnSab);
        htlDom.addComponent(btnDom);

        vtlHorarios.addComponents(htlConfig, htlLun, htlMar, htlMie, htlJue, htlVie, htlSab, htlDom);

        return vtlHorarios;
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

    private void setFinHora(LocalTime value) {
        cmbFinHora.setEnabled(true);
        cmbFinHora.setItems(IntStream.range(value.getHour() + 1, 19).mapToObj(h -> LocalTime.of(h, 0)));
    }

    private void createHoras(LocalTime value) {

        Stream<LocalTime> strHoras = IntStream.range(cmbIniHora.getValue().getHour(), cmbFinHora.getValue().getHour() + 1).mapToObj(h -> LocalTime.of(h, 0));

        Iterable<LocalTime> iterable = strHoras::iterator;

        for (LocalTime time : iterable) {

            Button btnHora = new Button(String.valueOf(time));
            htlLun.addComponent(btnHora);
        }
    }

    private void guardar() {
        List<HorarioDia> values = new ArrayList<>();

        //Map<String, List<Token>> mapDiaHoras = new HashMap<>();
        Map<String, List<LocalTime>> mapDiaHoras = new HashMap<>();
//        for (int i = 0; i < consultorioForm.getComponentCount(); i++) {
//            Component component = consultorioForm.getComponent(i);
//            if (component instanceof HorarioDia) {
//                values.add((HorarioDia) component);
//            }
//        }

//        for (HorarioDia hora : values) {
//            List<LocalTime> lstTime = new ArrayList<>();
//            if (!hora.getValues().isEmpty()) {
//                for (Token token : hora.getValues()) {
//                    lstTime.add(LocalTime.parse(token.getCaption(), DateTimeFormatter.ofPattern("HH:mm")));
//                };
//                mapDiaHoras.put(hora.getDiaSemana(), lstTime);
//            }
//        }

//        for (Map.Entry<String, List<Token>> entry : mapDiaHoras.entrySet()) {
//            System.out.format("Key : %s, Value : %s \n", entry.getKey(), Arrays.toString(entry.getValue().toArray()));
//        }
        for (Map.Entry<String, List<LocalTime>> entry : mapDiaHoras.entrySet()) {
            System.out.format("Key : %s, Value : %s \n", entry.getKey(), Arrays.toString(entry.getValue().toArray()));
        }
        cancelar();
    }

    private void cancelar() {
        //UI.getCurrent().getNavigator().navigateTo(AppViewType.PACIENTES_CARD.getViewName());
    }

    @Override
    public void enter(final ViewChangeEvent event) {
    }

}
