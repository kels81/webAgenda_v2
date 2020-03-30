package com.mx.app.base.component;

import com.mx.app.base.domain.Consultorio;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import java.util.List;

/**
 *
 * @author Edrd
 */
public class CardConsultorioLayout extends CssLayout {

    public CardConsultorioLayout() {
        addStyleName("consultorioCardView");
        setSizeFull();
        Responsive.makeResponsive(this);

        //System.out.println("width-->" + Page.getCurrent().getBrowserWindowWidth());
        //System.out.println("height-->" + Page.getCurrent().getBrowserWindowHeight());
    }

    public void setItems(List<Consultorio> consultorios) {
        removeAllComponents();
        for (Consultorio consultorio : consultorios) {
            CardConsultorio card = new CardConsultorio(consultorio);
//            CardConsultorio2 card = new CardConsultorio2();
//            card.setObjectConsultorio(consultorio);
//            card.setCaption(consultorio.getNombreConsultorio());
//            card.showButtonContextMenu(true);
            addComponent(card);
        }
    }

}
