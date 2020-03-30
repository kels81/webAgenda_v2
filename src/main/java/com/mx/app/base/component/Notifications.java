/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.app.base.component;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

/**
 *
 * @author Eduardo
 */
public class Notifications {

    public static final Notification createSuccess(String caption) {
        Notification success = new Notification(caption);
        success.setDelayMsec(2000);
        success.setStyleName("bar success small");
        success.setPosition(Position.TOP_CENTER);
        success.show(Page.getCurrent());
        return success;
    }
    
    public static final Notification createFailure(String caption) {
        Notification success = new Notification(caption);
        success.setDelayMsec(2000);
        success.setStyleName("bar failure small");
        success.setPosition(Position.TOP_CENTER);
        success.show(Page.getCurrent());
        return success;
    }

}
