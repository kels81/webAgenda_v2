/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.app.base.component;

import com.mx.app.base.component.window.AgregarPacienteWindow;
import com.mx.app.base.domain.Paciente;
import com.mx.app.base.logic.PacienteLogic;
import com.vaadin.event.LayoutEvents;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import java.util.List;

/**
 *
 * @author Edrd
 */
public class CardPacienteLayout extends CssLayout {

    public CardPacienteLayout() {

        addStyleName("pacienteCardView");
        setSizeFull();
        Responsive.makeResponsive(this);

        //System.out.println("width-->" + Page.getCurrent().getBrowserWindowWidth());
        //System.out.println("height-->" + Page.getCurrent().getBrowserWindowHeight());

    }

    public void setItems(PacienteLogic viewLogic, List<Paciente> pacientes) {
        removeAllComponents();
        for (Paciente paciente : pacientes) {
            CardPaciente card = new CardPaciente(paciente);
            card.addLayoutClickListener((LayoutEvents.LayoutClickEvent event) -> {
                if (event.isDoubleClick()) {
                    viewLogic.itemSelected(paciente, "card");
                }
            });
            addComponent(card);
        }
    }

}
