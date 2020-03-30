package com.mx.app.base.component;

import com.mx.app.base.component.window.ConfirmWindow;
import com.mx.app.base.domain.Consultorio;
import com.mx.app.base.event.AppEvent;
import com.mx.app.base.event.AppEventBus;
import com.mx.app.base.view.AppViewType;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author Edrd
 */
public class ButtonContextMenu extends MenuBar {

    public ButtonContextMenu(Consultorio consultorio) {
        addStyleName(ValoTheme.MENUBAR_SMALL);
        addStyleName("btn-contextmenu");

        MenuBar.MenuItem menu = addItem("", FontAwesome.ELLIPSIS_H, null);

        //EDITAR
        MenuBar.MenuItem editar = menu.addItem("Editar", FontAwesome.PENCIL, e -> {
            UI.getCurrent().getNavigator().navigateTo(AppViewType.CREAR_CONSULTORIO.getViewName() + "/" + consultorio.getNombreConsultorio());
            AppEventBus.post(new AppEvent.ConsultorioEditEvent(consultorio));
        });

        //BORRAR
        MenuBar.MenuItem borrar = menu.addItem("Eliminar", FontAwesome.TRASH, e -> {
            ConfirmWindow confirmWindow = new ConfirmWindow(consultorio);
            Window w = confirmWindow;
            UI.getCurrent().addWindow(w);
            w.focus();
        });

    }

}
