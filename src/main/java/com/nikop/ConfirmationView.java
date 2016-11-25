package com.nikop;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = ConfirmationView.VIEW_NAME)
public class ConfirmationView extends CssLayout implements View {
	public static final String VIEW_NAME = "confirm";

	@PostConstruct
    void init() {
		
		setStyleName("confirmation");	 
		
		Label success = new Label("Your Job Application was succesfully submitted!");
		
		success.setStyleName("success-text");
		
        addComponent(success);    
        
        Button button = new Button("Back");
        button.addClickListener( e -> getUI().getNavigator().navigateTo(FormView.VIEW_NAME) );
        
        addComponent(button);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        
    }
}
