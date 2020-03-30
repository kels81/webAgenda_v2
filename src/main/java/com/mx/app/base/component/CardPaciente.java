/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.app.base.component;

import com.mx.app.base.domain.Paciente;
import com.mx.app.base.utils.Components;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author ECortesHe
 */
public class CardPaciente<T> extends CssLayout {

    private VerticalLayout card;
    private final Paciente paciente;

    public CardPaciente(Paciente paciente) {
        super();
        this.paciente = paciente;

        addStyleName("mainPanel");
        addComponent(buildCard());
    }

    private Component buildCard() {
        card = new VerticalLayout();
        card.setSizeFull();
        card.addStyleName("frame");
        card.setMargin(true);
        card.setSpacing(true);
        card.addStyleName(ValoTheme.LAYOUT_CARD);

        Component icon = buildIcon();
        Component title = buildTitle();
        Component description = buildDescription();
        
        card.addComponents(icon, title, description);
        card.setComponentAlignment(icon, Alignment.MIDDLE_CENTER);
        card.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
        card.setComponentAlignment(description, Alignment.MIDDLE_CENTER);
        return card;
    }

    private Component buildIcon() {
        Image icon = new Image(null, new ThemeResource("img/avatars/" + paciente.getAvatar()));
        icon.setWidth(64.0f, Unit.PIXELS);
        icon.setHeight(64.0f, Unit.PIXELS);
        return icon;
    }

    private Component buildTitle() {
        String nombre = paciente.getNombre() + " "
                + paciente.getApPaterno() + " "
                + paciente.getApMaterno();
        Label title = Components.createLabel(nombre);
        return title;
    }

    private Component buildDescription() {
        Label title = new Label();
        return title;
    }

}
