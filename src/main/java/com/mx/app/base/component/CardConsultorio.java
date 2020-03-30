package com.mx.app.base.component;

import com.mx.app.base.domain.Consultorio;
import com.mx.app.base.utils.Components;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author ECortesHe
 */
public class CardConsultorio<T> extends CssLayout {

    private HorizontalLayout card;
    private final Consultorio consultorio;

    public CardConsultorio(Consultorio consultorio) {
        super();
        this.consultorio = consultorio;

        addStyleName("mainPanel");
        addComponent(buildCard());
    }

    private Component buildCard() {
        card = new HorizontalLayout();
        card.setSizeFull();
        card.addStyleName("frame");
        card.setMargin(true);
        card.setSpacing(true);
        card.addStyleName(ValoTheme.LAYOUT_CARD);

        Component title = buildTitle();
        ButtonContextMenu btnContextMenu = new ButtonContextMenu(consultorio);

        card.addComponents(title, btnContextMenu);
        card.setExpandRatio(title, 1.0f);
        card.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
        card.setComponentAlignment(btnContextMenu, Alignment.TOP_RIGHT);
        return card;
    }
    
    private Component buildTitle() {
        String nombre = consultorio.getNombreConsultorio();
        Label title = Components.createLabel(nombre);
        return title;
    }

}
