package com.example.application.views.login;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

@Route(value = "login")
public class LoginView extends LoginOverlay implements HasUrlParameter<String> {

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        // TODO Auto-generated method stub
        if (attachEvent.isInitialAttach()) {
            setOpened(true);
            setAction("login");
        }
    }

    @Override
    public void setParameter(BeforeEvent arg0, @OptionalParameter String arg1) {
        // TODO Auto-generated method stub
        if (arg0.getLocation().getQueryParameters().getParameters().containsKey("error"))
            setError(true);
    }

}
