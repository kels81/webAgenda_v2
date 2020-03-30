package com.mx.app.base.component.window;

import com.mx.app.base.data.consultorio.service.ConsultorioService;
import com.mx.app.base.domain.Consultorio;
import com.mx.app.base.utils.Components;
import com.mx.app.base.view.AppViewType;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author Edrd
 */
public class ConfirmWindow extends Window {

    private final VerticalLayout content;
    private VerticalLayout body;
    private HorizontalLayout footer;

    private Button btnCancelar;
    private Button btnAceptar;

    private final TabSheet detailsWrapper;
    
    private final Consultorio consultorio;
    
    private final ConsultorioService service = ConsultorioService.getInstance();

    public ConfirmWindow(Consultorio consultorio) {
        this.consultorio = consultorio;

        addStyleName("confirm-window");
        Responsive.makeResponsive(this);

        setModal(true);
        setResizable(false);
        setClosable(true);
        setHeight(90.0f, Sizeable.Unit.PERCENTAGE);

        content = new VerticalLayout();
        content.setSizeFull();

        detailsWrapper = new TabSheet();
        detailsWrapper.setSizeFull();
        detailsWrapper.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        detailsWrapper.addComponent(body());

        content.addComponent(detailsWrapper);
        content.setExpandRatio(detailsWrapper, 1.0f);
        content.addComponent(buildFooter());

        setContent(content);
        //Page.getCurrent().getStyles().add(".v-verticallayout {border: 1px solid blue;} .v-verticallayout .v-slot {border: 1px solid red;}");
    }

    private Component body() {
        body = new VerticalLayout();
        body.setCaption("Confirmar");
        body.setSizeFull();
        body.setMargin(true);

        Label messageLbl = new Label("¿Está seguro de que desea eliminar este consultorio?");

        body.addComponent(messageLbl);
        body.setComponentAlignment(messageLbl, Alignment.MIDDLE_LEFT);

        return body;
    }

    private Component buildFooter() {
        footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
        footer.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);

        btnCancelar = Components.createButtonNormal("Cancelar");
        btnCancelar.addClickListener(e -> cancelar());
        btnCancelar.setClickShortcut(ShortcutAction.KeyCode.ESCAPE, null);

        btnAceptar = Components.createButtonPrimary("Aceptar");
        btnAceptar.focus();
        btnAceptar.addClickListener(e -> aceptar());
        btnAceptar.setClickShortcut(ShortcutAction.KeyCode.ENTER, null);

        footer.addComponents(btnCancelar, btnAceptar);
        footer.setExpandRatio(btnCancelar, 1);
        footer.setComponentAlignment(btnCancelar, Alignment.TOP_RIGHT);

        return footer;
    }

    private void aceptar() {
        service.delete(consultorio);
        UI.getCurrent().getNavigator().navigateTo(AppViewType.CONSULTORIO.getViewName());
        close();
    }

    private void cancelar() {
        close();
    }
}
