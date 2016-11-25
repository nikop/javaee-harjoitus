package com.nikop;

import javax.annotation.PostConstruct;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = AdminView.VIEW_NAME)
public class AdminView extends CssLayout implements View {
	public static final String VIEW_NAME = "admin";

	JobApplicationRepository repository;

    public AdminView(JobApplicationRepository r)
    {
    	repository = r;
    }
    
	@PostConstruct
    void init() {
		
		setStyleName("confirmation");	 
		
		Label header = new Label("Submitted Job Applications");
    	header.setStyleName(ValoTheme.LABEL_H2);
    	addComponent(header);
    	
    	BeanItemContainer<JobApplication> container =
    		    new BeanItemContainer<JobApplication>(JobApplication.class, repository.findAll());
		
    	Grid grid = new Grid(container);
    	grid.setEditorEnabled(false);
    	grid.setColumns("firstName", "lastName", "submitTime", "gender", "openText");
    	grid.setWidth("80%");
    	addComponent(grid);

        
        Button button = new Button("Back");
        button.addClickListener( e -> getUI().getNavigator().navigateTo(FormView.VIEW_NAME) );
        
        addComponent(button);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        
    }
}
