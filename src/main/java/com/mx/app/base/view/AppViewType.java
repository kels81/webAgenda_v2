package com.mx.app.base.view;

import com.mx.app.base.view.configuracion.ConfiguracionView;
import com.mx.app.base.view.configuracion.ConsultorioView;
import com.mx.app.base.view.pacientes.PacientesView;
import com.mx.app.base.view.pacientes_card.CardView;
import com.mx.app.base.view.pacientes_card.EditPacienteView;
import com.mx.app.base.view.schedule.ScheduleView;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

public enum AppViewType {
    PACIENTES("pacientes", PacientesView.class, FontAwesome.TABLE, true),
    PACIENTES_CARD("pacientes_card", CardView.class, FontAwesome.COMPASS, false),
    SCHEDULE("schedule", ScheduleView.class, FontAwesome.CALENDAR_O, false),
    CONSULTORIO("consultorio", ConsultorioView.class, FontAwesome.COG, false),
    
    
    CREAR_CONSULTORIO("crear_consultorio", ConfiguracionView.class, true),
    EDITPACIENTE("edit", EditPacienteView.class, true);

    private final String viewName;
    private final Class<? extends View> viewClass;
    private final View viewPage;
    private final Resource icon;
    private final boolean stateful;
    private final boolean visibleInMenu;

    private AppViewType(
            final String viewName,
            final Class<? extends View> viewClass,
            final Resource icon,
            final boolean stateful) {
        this(true, viewName, viewClass, icon, null, stateful);
    }

    private AppViewType(
            final String viewName,
            final Class<? extends View> viewClass,
            final boolean stateful) {
        this(false, viewName, viewClass, null, null, stateful);
    }

    private AppViewType(
            final boolean visible,
            final String viewName,
            final Class<? extends View> viewClass,
            final Resource icon,
            final View viewPage,
            final boolean stateful) {
        this.viewName = viewName;
        this.viewClass = viewClass;
        this.viewPage = viewPage;
        this.icon = icon;
        this.stateful = stateful;
        this.visibleInMenu = visible;
    }

    public boolean isVisibleInMenu() {
        return visibleInMenu;
    }

    public boolean isStateful() {
        return stateful;
    }

    public View getViewPage() {
        return viewPage;
    }

    public String getViewName() {
        return viewName;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public Resource getIcon() {
        return icon;
    }

    public static AppViewType getByViewName(final String viewName) {
        AppViewType result = null;
        for (AppViewType viewType : values()) {
            if (viewType.getViewName().equals(viewName)) {
                result = viewType;
                break;
            }
        }
        return result;
    }

}
