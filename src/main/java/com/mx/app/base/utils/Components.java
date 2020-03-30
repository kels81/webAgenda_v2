/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.app.base.utils;

import com.mx.app.base.data.dummy.DataProviderStatic;
import com.mx.app.base.domain.DiasSemana;
import com.vaadin.data.Result;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.*;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.*;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.*;
import java.util.Date;
import java.util.Locale;
import org.vaadin.textfieldformatter.CustomStringBlockFormatter;

/**
 *
 * @author Eduardo
 */
public class Components {
    
    private static final DataProviderStatic DATAPROVIDER = new DataProviderStatic();
    
    // [ BUTTONS ]

    public static final Button createButtonPrimary(String caption) {
        Button btn = new Button(caption);
        //btn.addStyleName(ValoTheme.BUTTON_SMALL);
        btn.addStyleName(ValoTheme.BUTTON_PRIMARY);
        return btn;
    }

    public static final Button createButtonIconTiny() {
        Button btn = new Button();
        btn.addStyleName(ValoTheme.BUTTON_TINY);
        btn.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        return btn;
    }
    
    public static final Button createButtonIconNormal() {
        Button btn = new Button();
        btn.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        return btn;
    }
    
    public static final Button createButtonIconNormal(Resource icon) {
        Button btn = new Button(icon);
        btn.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        return btn;
    }
    
    public static final Button createButtonIconNormalSmall(Resource icon) {
        Button btn = new Button(icon);
        btn.addStyleName(ValoTheme.BUTTON_SMALL);
        btn.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        btn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        return btn;
    }

    public static final Button createButtonNormal(String caption) {
        Button btn = new Button(caption);
        return btn;
    }
    
    public static final Button createButtonNormal(Resource icon) {
        Button btn = new Button(icon);
        return btn;
    }

    public static final Button createButtonAddDirectory(String caption) {
        Button btn = new Button(caption);
        btn.setIcon(FontAwesome.PLUS);
        //btn.addStyleName(ValoTheme.BUTTON_SMALL);
        btn.addStyleName(ValoTheme.BUTTON_TINY);
        btn.addStyleName(ValoTheme.BUTTON_PRIMARY);
        return btn;
    }
    
    public static final Button createButtonBorderlessSmall(String caption) {
        Button btn = new Button(caption);
        btn.setCaptionAsHtml(true);
        btn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        btn.addStyleName(ValoTheme.BUTTON_SMALL);
        return btn;
    }
    
    public static final Button createButtonLink(String caption) {
        Button btn = new Button(caption);
        btn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        btn.addStyleName(ValoTheme.BUTTON_SMALL);
        return btn;
    }
    
    
    // [ COMBOBOX ]
    
    public static final ComboBox<String> createComboEstados(String caption) {
        ComboBox<String> cmb = new ComboBox<>(caption);
        //cmb.setInputPrompt("Seleccione Estado");
        cmb.setEmptySelectionAllowed(false);
        cmb.addFocusListener(focusListener);
        cmb.addBlurListener(blurListener);
        cmb.setItems(DATAPROVIDER.getEstados());
        return cmb;
    }

    public static final ComboBox<String> createComboParentesco(String caption) {
        ComboBox<String> cmb = new ComboBox<>(caption);
        //cmb.setInputPrompt("Seleccione Parentesco");
        cmb.setEmptySelectionAllowed(false);
        cmb.addFocusListener(focusListener);
        cmb.addBlurListener(blurListener);
        cmb.setItems(DATAPROVIDER.getParentesco());
        return cmb;
    }

    public static final ComboBox<String> createComboEdoCivil(String caption) {
        ComboBox<String> cmb = new ComboBox<>(caption);
        //cmb.setInputPrompt("Seleccione Estado Civil");
        cmb.setEmptySelectionAllowed(false);
        cmb.addFocusListener(focusListener);
        cmb.addBlurListener(blurListener);
        cmb.setItems(DATAPROVIDER.getEstadoCivil());
        return cmb;
    }

//    public static final ComboBox<String> createComboGenero(String caption) {
//        ComboBox<String> cmb = new ComboBox<>(caption);
//        //cmb.setInputPrompt("Seleccione GÃ©nero");
//        cmb.setEmptySelectionAllowed(false);
//        cmb.addFocusListener(focusListener);
//        cmb.addBlurListener(blurListener);
//        cmb.setItems(DATAPROVIDER.getGenero());
//        return cmb;
//    }

    public static final ComboBox<String> createComboReligion(String caption) {
        ComboBox<String> cmb = new ComboBox<>(caption);
        //cmb.setInputPrompt("Seleccione Religión");
        cmb.setEmptySelectionAllowed(false);
        cmb.addFocusListener(focusListener);
        cmb.addBlurListener(blurListener);
        cmb.setItems(DATAPROVIDER.getReligion());
        return cmb;
    }
    
    public static final ComboBox<LocalTime> createComboHorasSmall() {
        ComboBox<LocalTime> cmb = new ComboBox<>();
        cmb.addStyleName(ValoTheme.COMBOBOX_SMALL);
        cmb.setEmptySelectionAllowed(false);
        cmb.setTextInputAllowed(false);
        //cmb.setWidth(100, Unit.PIXELS);
        cmb.addFocusListener(focusListener);
        cmb.addBlurListener(blurListener);
        cmb.setItems(DATAPROVIDER.getHorasHabiles());
        return cmb;
    }
    
    public static final ComboBox<LocalTime> createComboHoras(String caption) {
        ComboBox<LocalTime> cmb = new ComboBox<>(caption);
        cmb.setEmptySelectionAllowed(false);
        cmb.setTextInputAllowed(false);
        //cmb.setWidth(100, Unit.PIXELS);
        cmb.addFocusListener(focusListener);
        cmb.addBlurListener(blurListener);
        cmb.setItems(DATAPROVIDER.getHorasHabiles());
        return cmb;
    }
    
    public static final ComboBox<LocalTime> createComboHoras() {
        ComboBox<LocalTime> cmb = new ComboBox<>();
        cmb.setEmptySelectionAllowed(false);
        cmb.addStyleName(ValoTheme.COMBOBOX_BORDERLESS);
        cmb.setTextInputAllowed(false);
        //cmb.setWidth(100, Unit.PIXELS);
        cmb.addFocusListener(focusListener);
        cmb.addBlurListener(blurListener);
        cmb.setItems(DATAPROVIDER.getHorasHabiles());
        return cmb;
    }

    public static final ComboBox<String> createComboHours(String caption, Integer hourStart, Integer hourEnd, Integer comodin) {
        ComboBox hours = new ComboBox(caption);
        /*SimpleDateFormat df12 = new SimpleDateFormat();
         SimpleDateFormat df24 = new SimpleDateFormat();
         df12.applyPattern("hh:mm a");
         df24.applyPattern("HH:mm");*/
        hourStart = comodin == 0 ? hourStart + 1 : hourStart;
        hourEnd = comodin == 1 ? hourEnd - 1 : hourEnd;

        for (int i = hourStart; i <= hourEnd; i++) {
            String h = (i < 10 ? "0" + String.valueOf(i) : i > 12 && i < 22 ? "0" + String.valueOf(i - 12) : i >= 22 ? String.valueOf(i - 12) : String.valueOf(i));
            String type = (i < 12 ? " AM" : i >= 12 ? " PM" : "");
            //System.out.println("i"+i);
            //hours.addItem(df12.format(i));
            
            if (i == 24) {
//                hours.addItem(h + ":00" + type);
            } else {
//                hours.addItem(h + ":00" + type);
//                hours.addItem(h + ":30" + type);
            }
        }

        hours.addFocusListener(focusListener);
        hours.addBlurListener(blurListener);
        hours.setEmptySelectionAllowed(false);
        hours.setTextInputAllowed(false);

        return hours;
    }

    public static final String getMonth() {
        String month = "";
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
        month = sdf.format(today);
        return month;
    }

    public static final String getToday() {
        String day = "";
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        day = sdf.format(today);
        return day;
    }
    
    public static final String setUpperMonth(Date date) {
        String upperMonth = "";
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd MMMM yyyy");
        //Obteniendo mes de la fecha
        upperMonth = df.format(date).substring(df.format(date).indexOf(" ") + 1, df.format(date).length() - 4);
        //Primera letra del mes en Mayuscula
        upperMonth = df.format(date).replaceFirst(upperMonth.substring(0, 1), upperMonth.substring(0, 1).toUpperCase());

        return upperMonth;
    }
    
    // [ CHECKBOX ]
    
    public static final CheckBox createCheckBoxSmall(String caption, boolean checked) {
        CheckBox chb = new CheckBox(caption, checked);
        chb.addStyleName(ValoTheme.CHECKBOX_SMALL);
        return chb;
    }
    
    public static final CheckBoxGroup<DiasSemana> createCheckBoxGroupDiasSemana() {
        CheckBoxGroup<DiasSemana> chb = new CheckBoxGroup<>();
        chb.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
        chb.setItems(new DiasSemana().getDiasSemana());
        return chb;
    }
    
    public static final CheckBoxGroup<String> createCheckBoxGroupDiasSemana(String caption) {
        CheckBoxGroup<String> chb = new CheckBoxGroup<>(caption);
        chb.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
        //chb.setItems(DATAPROVIDER.getDiasSemanaShort());
        return chb;
    }
    
    // [ DATEFIELD ]
    
    public static final DateField createDateFieldNac(String caption) {
        String strDateFormat = "dd/MM/yyyy";
        DateField f = new DateField(caption){
            @Override
            protected Result<LocalDate> handleUnparsableDateString(String dateString) {
                dateString = dateString.replaceFirst("(\\d{2})(\\d{2})(\\d+)", "$1/$2/$3");
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(strDateFormat);
                    LocalDate parsedAtServer = LocalDate.parse(dateString, formatter);
                    System.out.println("Result22 = " + Result.ok(parsedAtServer));
                    return Result.ok(parsedAtServer);
                } catch (DateTimeParseException e) {
                    System.out.println("Result22 = " + Result.error("Fecha Incorrecta"));
                    return Result.error("Fecha Incorrecta");

                }
            }
        };
        f.setDateFormat(strDateFormat);
        f.setPlaceholder(strDateFormat);
        f.setRangeEnd(LocalDate.now());
        f.setLocale(new Locale("es", "MX"));
        return f;
    }
    
    public static final DateField createDateField(String caption) {
        DateField f = new DateField(caption);
        f.setDateFormat("dd MMM yyyy");
//        f.setTextFieldEnabled(false);
        return f;
    }
    
    
    // [ FORM LAYOUT ]
    
    public static final FormLayout createFormLight() {
        FormLayout frl = new FormLayout();
        frl.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        return frl;
    }
    
    // [ HORIZONTAL LAYOUT ]

    public static final HorizontalLayout createHorizontalWrapping(String caption, Component... childrens) {
        HorizontalLayout hrl = new HorizontalLayout(childrens);
        hrl.setCaption(caption);
        hrl.setWidth(100.0f, Unit.PERCENTAGE);
        hrl.setStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
        hrl.setSpacing(false);
        return hrl;
    }
    
    public static final HorizontalLayout createHorizontalWrapping(Component... childrens) {
        HorizontalLayout hrl = new HorizontalLayout(childrens);
        hrl.setWidth(100.0f, Unit.PERCENTAGE);
        hrl.setStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
        hrl.setSpacing(false);
        return hrl;
    }
   
    public static final HorizontalLayout createHorizontalForm() {
        HorizontalLayout hrl = new HorizontalLayout();
        hrl.setWidth(100.0f, Unit.PERCENTAGE);
        hrl.setMargin(true);
        hrl.setSpacing(true);
        return hrl;
    }
    
    public static final HorizontalLayout createHorizontalFormIntermedio() {
        HorizontalLayout hrl = new HorizontalLayout();
        hrl.setWidth(100.0f, Unit.PERCENTAGE);
        hrl.setMargin(new MarginInfo(false, true, true, true));
        hrl.setSpacing(true);
        return hrl;
    }
    
    
    
    // [ LABEL ]
    
    public static final Label createLabel(String caption) {
        Label lbl = new Label(caption);
        return lbl;
    }
    
    public static final Label createLabelHeader(String caption) {
        Label lbl = new Label(caption);
        lbl.setSizeUndefined();
        lbl.addStyleName(ValoTheme.LABEL_H1);
        lbl.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        return lbl;
    }
    
    public static final Label createLabelColumnDia(String caption) {
        Label lbl = new Label(caption);
        lbl.setSizeFull();
        lbl.addStyleName(ValoTheme.LABEL_H4);
        lbl.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        lbl.addStyleName(ValoTheme.LABEL_COLORED);
        return lbl;
    }

    public static final Label createLabelH4(String caption) {
        Label lbl = new Label(caption);
        lbl.addStyleName(ValoTheme.LABEL_H4);
        lbl.addStyleName(ValoTheme.LABEL_COLORED);
        return lbl;
    }
    
    public static final Label createLabelTitleCard(String caption) {
        Label lbl = new Label(caption);
        lbl.setSizeFull();
        lbl.addStyleName("labelName");
        return lbl;
    }
    
    public static final Label createLabelToken(String caption) {
        Label lbl = new Label(caption);
        lbl.setSizeFull();
        lbl.setContentMode(ContentMode.HTML);
        lbl.addStyleName(ValoTheme.LABEL_LIGHT);
        lbl.addStyleName(ValoTheme.LABEL_SMALL);
        return lbl;
    }


    // [ MENUBAR ]
    
    public static final MenuBar createMenuButtonPath() {
        MenuBar menubtn = new MenuBar();
        menubtn.setStyleName(ValoTheme.MENUBAR_BORDERLESS);
        return menubtn;
    }
    
    
    
    // [ PUPLOAD ]
    
//    public static final Plupload createPluploadPrimary(String caption){
//        Plupload uploader = new Plupload(caption, FontAwesome.UPLOAD);
//        //uploader.addStyleName(ValoTheme.BUTTON_SMALL);
//        uploader.addStyleName(ValoTheme.BUTTON_TINY);
//        uploader.addStyleName(ValoTheme.BUTTON_PRIMARY);
//        return uploader;
//    }

    
    // [ RADIOBUTTONGROUP ]

    public static final RadioButtonGroup<String> createRadioGenero(String caption) {
        RadioButtonGroup<String> rdb = new RadioButtonGroup<>(caption);
        rdb.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
        rdb.setItems(DATAPROVIDER.getGenero());
        rdb.setSelectedItem(DATAPROVIDER.getGenero().get(0));
        return rdb;
    }
    
    // [ TABSHEET ]
    
    public static final TabSheet createTabSheetWindow() {
        TabSheet tab = new TabSheet();
        tab.setSizeFull();
        tab.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tab.addStyleName(ValoTheme.TABSHEET_ICONS_ON_TOP);
        tab.addStyleName(ValoTheme.TABSHEET_CENTERED_TABS);
        tab.setSelectedTab(0);
        return tab;
    }
    
    
    // [ TEXTAREA ]
    
    public static final TextArea createTextArea(String caption) {
        TextArea f = new TextArea(caption);
//        f.setNullRepresentation("");
        f.setRows(3);
        f.addFocusListener(focusListener);
        f.addBlurListener(blurListener);
        return f;
    }
    
    
    // [ TEXTFIELD ]
    
    public static final TextField createTextSmall(String caption, String value) {
        TextField txt = new TextField(caption);
        txt.addStyleName(ValoTheme.TEXTFIELD_SMALL);
        txt.setWidth("260px");
        txt.setValue(value);
        return txt;
    }
    
    public static final TextField createTextFilter() {
        TextField txt = new TextField();
        txt.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        txt.setPlaceholder("Buscar por nombre y/o apellidos");
        txt.setIcon(FontAwesome.SEARCH);
        txt.setWidth(100.0f, Unit.PERCENTAGE);
        return txt;
    }
    
    public static final TextField createTextField(String caption) {
        TextField f = new TextField(caption);
        f.setCaptionAsHtml(true);
        f.addBlurListener(blurListener);
        return f;
    }
    
    public static final TextField createTextFieldDisabled(String caption) {
        TextField f = new TextField(caption);
        f.setEnabled(false);
        return f;
    }
    
    public static final TextField createTextFieldDisabled() {
        TextField f = new TextField();
        f.setWidth(100.0f, Unit.PERCENTAGE);
        f.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        f.setEnabled(false);
        return f;
    }
    
    
    static FieldEvents.FocusListener focusListener = (FieldEvents.FocusEvent event) -> {
        event.getComponent().addStyleName("blue-caption");
    };

    static FieldEvents.BlurListener blurListener = (FieldEvents.BlurEvent event) -> {
        event.getComponent().removeStyleName("blue-caption");
    };
    
    public static CustomStringBlockFormatter.Options formatTelFijo() {
        CustomStringBlockFormatter.Options optTelFijo = new CustomStringBlockFormatter.Options();
        optTelFijo.setBlocks(4, 4);
        optTelFijo.setNumericOnly(true);
        optTelFijo.setDelimiters("-");
        return optTelFijo;
    }

    public static CustomStringBlockFormatter.Options formatTelCelular() {
        CustomStringBlockFormatter.Options optTelFijo = new CustomStringBlockFormatter.Options();
        optTelFijo.setBlocks(2, 4, 4);
        optTelFijo.setNumericOnly(true);
        optTelFijo.setDelimiters("-");
        return optTelFijo;
    }

}
