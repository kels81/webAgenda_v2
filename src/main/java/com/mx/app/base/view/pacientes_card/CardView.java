package com.mx.app.base.view.pacientes_card;

import com.mx.app.base.component.CardPacienteLayout;
import com.mx.app.base.data.paciente.service.PacienteService;
import com.mx.app.base.domain.Paciente;
import com.mx.app.base.logic.PacienteLogic;
import com.mx.app.base.utils.Components;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import java.util.List;
import org.vaadin.addons.ResetButtonForTextField;

@SuppressWarnings("serial")
public final class CardView extends VerticalLayout implements View {

    private final PacienteService service = PacienteService.getInstance();
    private final CardPacienteLayout layCards = new CardPacienteLayout();
    private final PacienteLogic viewLogic = new PacienteLogic(this);

    private TextField txtFilter;

    public CardView() {
        setSizeFull();
        addStyleName("pacientes");
        setMargin(false);
        setSpacing(false);

        Responsive.makeResponsive(this);

        addComponent(buildHeader());
        addComponent(buildSearch());
        addComponentsAndExpand(buildCards());

    }

    private Component buildHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.addStyleName("viewheader");
        Responsive.makeResponsive(header);

        Label lblTitle = Components.createLabelHeader("Pacientes");

        header.addComponent(lblTitle);

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

        ResetButtonForTextField.extend(txtFilter);

        Button btnAgregarPaciente = Components.createButtonPrimary("Paciente");
        btnAgregarPaciente.setIcon(FontAwesome.PLUS);
        btnAgregarPaciente.addClickListener(e -> openWindow(new Paciente()));

        tools.addComponents(txtFilter, btnAgregarPaciente);
        tools.setComponentAlignment(btnAgregarPaciente, Alignment.MIDDLE_RIGHT);
        return tools;
    }

    private Component buildCards() {
        layCards.setItems(viewLogic, service.findAll());
        return layCards;
    }

    private void openWindow(Paciente paciente) {
        viewLogic.openWindow(paciente, "card");
    }

    public void updateList() {
        List<Paciente> pacientes = service.findAll(txtFilter.getValue());
        layCards.setItems(viewLogic, pacientes);
    }

    @Override
    public void enter(final ViewChangeEvent event) {
    }

}
