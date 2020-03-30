/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.app.base.component.window;

import com.mx.app.base.domain.Paciente;
import com.mx.app.base.utils.Components;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author Edrd
 */
public class EditWindow extends Window {

    private final VerticalLayout content;
    private VerticalLayout body;
    private HorizontalLayout footer;
    private TextField txtEditName;

    private Button btnCancelar;
    private Button btnGuardar;

//    private final FileLogic viewLogicFile;
//    private final DirectoryLogic viewLogicDirectory;

    private final TabSheet detailsWrapper;

//    public EditWindow(FileLogic editFileLogic, DirectoryLogic editDirectoryLogic,  Item file) {
    public EditWindow(Paciente paciente) {
//        this.viewLogicFile = editFileLogic;
//        this.viewLogicDirectory = editDirectoryLogic;

        addStyleName("createfolder-window");
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
        detailsWrapper.addComponent(body(paciente));

        content.addComponent(detailsWrapper);
        content.setExpandRatio(detailsWrapper, 1.0f);
        content.addComponent(buildFooter(paciente));

        setContent(content);
    }

    private Component body(Paciente paciente) {
        body = new VerticalLayout();
        body.setCaption("Renombrar");
        body.setSizeFull();
        body.setSpacing(true);
        body.setMargin(true);

        txtEditName = new TextField();
        txtEditName.focus();
        txtEditName.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);
        txtEditName.setValue(paciente.getNombre());    //Para mostrar solamente el nombre del archivo sin la extensión
//        txtEditName.setInputPrompt("Nuevo nombre del archivo");
//        txtEditName.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);          //EAGER, Para que evento no sea lento
//        txtEditName.setTextChangeTimeout(200);
//        txtEditName.setImmediate(true);
//        txtEditName.addTextChangeListener((FieldEvents.TextChangeEvent event) -> {
//            btnGuardar.setEnabled(StringUtils.isNotBlank(event.getText()));
//        });

        body.addComponent(txtEditName);
        body.setComponentAlignment(txtEditName, Alignment.MIDDLE_CENTER);

        return body;
    }

    private Component buildFooter(Paciente paciente) {
        footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
        footer.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);

        btnCancelar = Components.createButtonNormal("Cancelar");
        btnCancelar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });
        btnCancelar.setClickShortcut(ShortcutAction.KeyCode.ESCAPE, null);

        btnGuardar = Components.createButtonPrimary("Guardar");
        btnGuardar.addClickListener((Button.ClickEvent event) -> {
//            Path source = Paths.get(paciente.getPath());
            String newName = txtEditName.getValue().trim();

//            Item newFile = null;
//            if (file.isDirectory()) {
//                newFile = new Item(source.getParent().toString() + "\\" + newName);
//                viewLogicDirectory.renameDirectory(source, file, newFile);
//            } else {
//                //DOCUMENTO
//                String extension = file.getName().substring(file.getName().lastIndexOf('.') + 1);
//                newFile = new Item(source.getParent().toString() + "\\" + newName + "." + extension);
//                viewLogicFile.renameFile(source, file, newFile);
//            }
            
            close();
        });
        btnGuardar.setClickShortcut(ShortcutAction.KeyCode.ENTER, null);

        footer.addComponents(btnCancelar, btnGuardar);
        footer.setExpandRatio(btnCancelar, 1);
        footer.setComponentAlignment(btnCancelar, Alignment.TOP_RIGHT);

        return footer;
    }

}
