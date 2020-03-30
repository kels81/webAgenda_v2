package com.mx.app.base.view.configuracion;

import com.mx.app.base.component.CardConsultorioLayout;
import com.mx.app.base.data.consultorio.service.ConsultorioService;
import com.mx.app.base.event.*;
import com.mx.app.base.utils.Components;
import com.mx.app.base.view.AppViewType;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.*;
import com.vaadin.ui.*;

/**
 *
 * @author ECortesHe
 */
@SuppressWarnings("serial")
public final class ConsultorioView extends VerticalLayout implements View {

    private final ConsultorioService service = ConsultorioService.getInstance();

    private final CardConsultorioLayout layCards = new CardConsultorioLayout();

    public ConsultorioView() {
        setSizeFull();
        addStyleName("consultorios");
        setMargin(false);
        setSpacing(false);
        Responsive.makeResponsive(this);
        //AppEventBus.register(this);   //NECESARIO PARA CONOCER LA ORIENTACION Y RESIZE DEL BROWSER

        addComponent(buildHeader());
        addComponent(buildToolbar());
        addComponentsAndExpand(buildCards());

    }

    private Component buildHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.addStyleName("viewheader");
        Responsive.makeResponsive(header);

        header.addComponent(Components.createLabelHeader("Consultorios"));
        return header;
    }

    private Component buildToolbar() {
        HorizontalLayout tools = new HorizontalLayout();
        tools.addStyleName("tools");
        tools.setWidth(100.0f, Unit.PERCENTAGE);
        tools.setMargin(true);

        Button btnAgregarConsultorio = Components.createButtonPrimary("Consultorio");
        btnAgregarConsultorio.setIcon(FontAwesome.PLUS);
        btnAgregarConsultorio.addClickListener(e -> agregarConsultorio());

        tools.addComponent(btnAgregarConsultorio);
        tools.setComponentAlignment(btnAgregarConsultorio, Alignment.MIDDLE_RIGHT);
        return tools;
    }

    private Component buildCards() {
        layCards.setItems(service.findAll());
        return layCards;
    }
    
    private void agregarConsultorio() {
        UI.getCurrent().getNavigator().navigateTo(AppViewType.CREAR_CONSULTORIO.getViewName());
        AppEventBus.post(new AppEvent.ConsultorioEditEvent(null));
    }

    @Override
    public void enter(final ViewChangeEvent event) {
    }

}
