package com.mx.app.base.event;

import com.mx.app.base.domain.Consultorio;
import com.mx.app.base.domain.Paciente;
import com.mx.app.base.logic.PacienteLogic;
import com.mx.app.base.view.AppViewType;


public abstract class AppEvent {

    public static final class UserLoginRequestedEvent {
        private final String userName, password;

        public UserLoginRequestedEvent(final String userName,
                final String password) {
            this.userName = userName;
            this.password = password;
        }

        public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }
    }

    public static class BrowserResizeEvent {

    }

    public static class UserLoggedOutEvent {

    }
    
    public static final class PostViewChangeEvent {
        private final AppViewType view;

        public PostViewChangeEvent(final AppViewType view) {
            this.view = view;
        }

        public AppViewType getView() {
            return view;
        }
    }
    
    public static final class PacienteEditEvent {
        private final Paciente paciente;
        private final String bnd;
        private final PacienteLogic logic; 

        public PacienteEditEvent(final PacienteLogic pacienteLogic, Paciente paciente, String bnd) {
            this.paciente = paciente;
            this.bnd = bnd;
            this.logic = pacienteLogic;
        }
        
        public PacienteEditEvent(final Paciente paciente) {
            this.paciente = paciente;
            this.bnd = null;
            this.logic = null;
        }
        
        public Paciente getPaciente() {
            return paciente;
        }
        public PacienteLogic getLogic() {
            return logic;
        }
        public String getBnd() {
            return bnd;
        }

    }
    
    public static final class ConsultorioEditEvent {
        private final Consultorio consultorio;

        public ConsultorioEditEvent(final Consultorio consultorio) {
            this.consultorio = consultorio;
        }
        
        public Consultorio getConsultorio() {
            return consultorio;
        }
    }
   
    public static class CloseOpenWindowsEvent {
    }

    public static class ProfileUpdatedEvent {
    }

}
