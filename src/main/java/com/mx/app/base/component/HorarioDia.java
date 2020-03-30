package com.mx.app.base.component;

import com.mx.app.base.utils.Components;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author ECortesHe
 */
public class HorarioDia extends VerticalLayout {

    private final String dia;
    private Label lblDia;
    public VerticalLayout htlHoras;

    public HorarioDia(String dia) {
        this.dia = dia;

        setMargin(false);
        setSpacing(false);
        //addStyleName("horarios-apertura");

        addComponents(
                setHeaderColumn(),
                buildContentHorarios()
        );

        setComponentAlignment(lblDia, Alignment.MIDDLE_CENTER);
        setComponentAlignment(htlHoras, Alignment.MIDDLE_CENTER);

        //Page.getCurrent().getStyles().add(".v-verticallayout {border: 1px solid blue;} .v-verticallayout .v-slot {border: 1px solid red;}");
    }

    private Component setHeaderColumn() {
        lblDia = Components.createLabelColumnDia(dia);
        lblDia.addStyleName("header-column");
        lblDia.setWidth(100.0f, Unit.PERCENTAGE);

        return lblDia;
    }

    private Component buildContentHorarios() {
        htlHoras = new VerticalLayout();
        htlHoras.setStyleName("content-horarios");

        return htlHoras;
    }

    public void setValue(LocalTime hora) {
        if (hora != null) {
            setOrdenTokens(hora);
        }
    }

    private HorizontalLayout buildToken(LocalTime hora) {
        HorizontalLayout htlToken = new HorizontalLayout();
        htlToken.setWidth(100.0f, Unit.PERCENTAGE);
        htlToken.setStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
        htlToken.setSpacing(false);

        Button lblToken = Components.createButtonBorderlessSmall(FontAwesome.CLOCK_O.getHtml() + "&nbsp;&nbsp;&nbsp;" + String.valueOf(hora));
        Button btnEliminar = Components.createButtonIconNormalSmall(FontAwesome.REMOVE);
        btnEliminar.addClickListener(e -> eliminarToken(htlToken));

        htlToken.addComponents(
                lblToken,
                btnEliminar
        );

        htlToken.setExpandRatio(lblToken, 70.0f);
        htlToken.setExpandRatio(btnEliminar, 30.0f);
        htlToken.setComponentAlignment(lblToken, Alignment.MIDDLE_CENTER);

        return htlToken;
    }

    private void eliminarToken(Component token) {
        htlHoras.removeComponent(token);
    }

    private void setOrdenTokens(LocalTime hora) {
        List<String> tokens = getValues();
        tokens.add(String.valueOf(hora));
        tokens = tokens.stream().sorted().distinct().collect(Collectors.toList());      //ORDERNAR Y ELIMINAR DUPLICADOS DE LA LISTA DESCENDENTE
        
        htlHoras.removeAllComponents();
        for (String token : tokens) {
            LocalTime horario = LocalTime.parse(token, DateTimeFormatter.ofPattern("HH:mm"));
            htlHoras.addComponent(buildToken(horario));
        }
    }

    public String getDiaSemana() {
        return dia;
    }

    public List<String> getValues() {
        List<String> values = new ArrayList<>();
        for (int i = 0; i < htlHoras.getComponentCount(); i++) {
            Component compHorizontal = htlHoras.getComponent(i);
            if (compHorizontal instanceof HorizontalLayout) {
                Component compHora = ((HorizontalLayout) compHorizontal).getComponent(0);
                if (compHora instanceof Button) {
                    String hora = compHora.getCaption().substring(compHora.getCaption().lastIndexOf(';') + 1);
                    values.add(hora);
                }
            }
        }
        return values;
    }

}
