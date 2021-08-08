package com.example.application.views.admin;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("admin")
public class AdminView extends VerticalLayout {

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        // TODO Auto-generated method stub
        if (attachEvent.isInitialAttach()) {
            add(new Label("ADMIN"));
            add(new Anchor("/logout", "Logout"));
        }
    }

}
