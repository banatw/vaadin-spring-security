package com.example.application.views.helloworld;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.component.dependency.CssImport;

@Route(value = "user")
@RouteAlias(value = "")
@PageTitle("Hello World")
@CssImport("./views/helloworld/hello-world-view.css")
public class HelloWorldView extends Div {

    public HelloWorldView() {
        addClassName("hello-world-view");
        add(new Text("User"));
        add(new Anchor("logout", "Logout"));
    }

}
