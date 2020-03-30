/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.app.base.component;

import com.mx.app.base.domain.Consultorio;
import com.mx.app.base.utils.Components;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author ECortesHe
 */
public class CardConsultorio2<T> extends CssLayout {

    private HorizontalLayout card;
    private Consultorio consultorio;

    private String caption;

    public CardConsultorio2() {
        super();
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

        return card;
    }

    @Override
    public void setCaption(String caption) {
        this.caption = caption;
        Component title = buildTitle();
        card.addComponents(title);
        card.setExpandRatio(title, 1.0f);
        card.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
    }

    public void setObjectConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public void showButtonContextMenu(boolean bnd) {
        if (bnd) {
            ButtonContextMenu btnContextMenu = new ButtonContextMenu(consultorio);
            card.addComponent(btnContextMenu);
            card.setComponentAlignment(btnContextMenu, Alignment.TOP_RIGHT);
        }
    }

    private Component buildTitle() {
        Label title = Components.createLabel(caption);
        return title;
    }

}
