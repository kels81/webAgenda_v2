/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.app.base.logic;

import com.mx.app.base.component.Notifications;
import com.mx.app.base.component.window.AgregarPacienteWindow;
import com.mx.app.base.data.paciente.service.PacienteService;
import com.mx.app.base.domain.Paciente;
import com.mx.app.base.event.AppEvent.PacienteEditEvent;
import com.mx.app.base.event.AppEventBus;
import com.mx.app.base.view.AppViewType;
import com.mx.app.base.view.pacientes.PacientesView;
import com.mx.app.base.view.pacientes_card.CardView;
import com.vaadin.ui.UI;

/**
 *
 * @author ECortesHe
 */
public class PacienteLogic {

    private CardView cardView;
    private PacientesView pacientesView;

    private final Notifications notification = new Notifications();

    private final PacienteService service = PacienteService.getInstance();

    public PacienteLogic(CardView view) {
        this.cardView = view;
    }

    public PacienteLogic(PacientesView view) {
        this.pacientesView = view;
    }

    //METODOS A USAR
    public void updateList() {
        this.pacientesView.updateList();
        Notifications.createSuccess("Se actualizo con éxito");
    }

    public void updateCardList() {
        this.cardView.updateList();
        Notifications.createSuccess("Se actualizo con éxito");
    }
    
    public void openWindow(Paciente paciente, String bnd) {
        AgregarPacienteWindow.open(this, paciente, bnd);
    }

    public void itemSelected(Paciente paciente, String bnd) {
        //AgregarPacienteWindow.open(this, paciente, bnd);        
        UI.getCurrent().getNavigator().navigateTo(AppViewType.EDITPACIENTE.getViewName()+ "/" + paciente.getId() + ":" + paciente.getNombre());
        AppEventBus.post(new PacienteEditEvent(this, paciente, bnd));
        //AppEventBus.post(new PacienteEditEvent(paciente)); 

//        Page page = Page.getCurrent();
//        page.setUriFragment(
//                "!" + EditPacienteView.VIEW_NAME + "/" + 33,
//                false);

    }

}
