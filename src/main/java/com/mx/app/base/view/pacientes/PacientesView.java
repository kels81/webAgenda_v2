package com.mx.app.base.view.pacientes;

import com.mx.app.base.data.paciente.service.PacienteService;
import com.mx.app.base.domain.Paciente;
import com.mx.app.base.logic.PacienteLogic;
import com.mx.app.base.utils.Components;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.*;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.themes.ValoTheme;
import java.util.List;

@SuppressWarnings("serial")
public final class PacientesView extends VerticalLayout implements View {

    private final PacienteService service = PacienteService.getInstance();
    private final PacienteLogic viewLogic = new PacienteLogic(this);

    private final Grid<Paciente> gridPacientes = new Grid<>();
    private TextField txtFilter;

    public PacientesView() {
        setSizeFull();
        addStyleName("pacientes");
        setMargin(false);
        setSpacing(false);

        Responsive.makeResponsive(this);

        addComponent(buildHeader());
        addComponent(buildSearch());
        addComponentsAndExpand(buildTable());
    }

    private Component buildHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.addStyleName("viewheader");
        Responsive.makeResponsive(header);

        header.addComponent(Components.createLabelHeader("Pacientes"));
        return header;
    }

    private Component buildSearch() {
        HorizontalLayout tools = new HorizontalLayout();
        tools.addStyleName("tools");
        tools.setWidth(100.0f, Unit.PERCENTAGE);
        tools.setMargin(true);
        
        Responsive.makeResponsive(tools);

        txtFilter = Components.createTextFilter();
        txtFilter.addValueChangeListener(e -> updateList());
        txtFilter.setValueChangeMode(ValueChangeMode.LAZY);

        Button btnClearFilter = Components.createButtonNormal(FontAwesome.TIMES);
        btnClearFilter.setDescription("Limpiar filtro");
        btnClearFilter.addClickListener(e -> txtFilter.clear());
        btnClearFilter.setClickShortcut(KeyCode.ESCAPE);

        CssLayout filtering = new CssLayout();
        filtering.addComponents(txtFilter, btnClearFilter);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        filtering.setSizeFull();

        Button btnAddPaciente = Components.createButtonPrimary("Paciente");
        btnAddPaciente.setIcon(FontAwesome.PLUS);
        btnAddPaciente.addClickListener(e -> openWindow(new Paciente()));

        tools.addComponents(filtering, btnAddPaciente);
        tools.setComponentAlignment(btnAddPaciente, Alignment.MIDDLE_RIGHT);

        return tools;
    }

    private Component buildTable() {
        HorizontalLayout layGrid = new HorizontalLayout();
        layGrid.setSizeFull();
        layGrid.setMargin(true);

        gridPacientes.setSizeFull();
        //gridPacientes.setLocale(new Locale("es", "MX"));
        gridPacientes.setStyleName("gridPacientes");
        gridPacientes.setItems(service.findAll());
        gridPacientes.addColumn(Paciente::getNombre)
                .setId("idNombre")
                .setCaption("Nombre")
                .setResizable(false);
        gridPacientes.addColumn(Paciente::getApPaterno)
                .setId("idPaterno")
                .setCaption("ApellidoPaterno")
                .setResizable(false);
//        gridPacientes.addColumn(Paciente::getApMaterno)
//                .setId("idMaterno")
//                .setCaption("Apellido Materno")
//                .setResizable(false);
        gridPacientes.addColumn(Paciente::getFechaNacimiento)
                .setId("idFechaNac")
                .setCaption("Fecha Nacimiento")
                .setResizable(false);
        gridPacientes.addColumn(Paciente::getGenero)
                .setId("idGenero")
                .setCaption("Género")
                .setResizable(false);
        gridPacientes.addColumn(Paciente::getStatus)
                .setId("idStatus")
                .setCaption("Status")
                .setResizable(false);
        gridPacientes.addColumn(Paciente::getEmail)
                .setId("idEmail")
                .setCaption("Email")
                .setResizable(false);
        gridPacientes.addColumn(Paciente::getTelCelular)
                .setId("idCelular")
                .setCaption("Telefono Celular")
                .setResizable(false);
        //gridPacientes.getColumn("idFechaNac").setRenderer((Renderer) new DateRenderer("%1$td/%1$tm/%1$tY")); 

        //gridPacientes.asSingleSelect().addValueChangeListener(event -> openWindow(event.getValue()));
        gridPacientes.addItemClickListener(event -> itemClicked(event));

        layGrid.addComponent(gridPacientes);

        return layGrid;
    }

    private void itemClicked(ItemClick<Paciente> event) {
        Paciente paciente = event.getItem();
        if (paciente != null) {
            if (event.getMouseEventDetails().isDoubleClick()) {
                gridPacientes.select(paciente);
                //openWindow(paciente);
                goTo(paciente);
            }
        }
    }

    private void openWindow(Paciente paciente) {
        viewLogic.openWindow(paciente, "grid");
    }
    
    private void goTo(Paciente paciente) {
        viewLogic.itemSelected(paciente, "grid");
    }

    public void updateList() {
        List<Paciente> pacientes = service.findAll(txtFilter.getValue());
        gridPacientes.setItems(pacientes);
    }

    @Override
    public void enter(final ViewChangeEvent event) {
    }

}
