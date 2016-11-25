package com.nikop;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = FormView.VIEW_NAME)
public class FormView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "";
    
    public static final String CLASSNAME = "job_application";
    
    JobApplicationRepository repository;

    public FormView(JobApplicationRepository r)
    {
    	repository = r;
    }
    
    @PostConstruct
    void init() {
    	
    	JobApplication ja = new JobApplication();
    	
    	Label header = new Label("Apply for Job");
    	header.setStyleName(ValoTheme.LABEL_H2);
    	addComponent(header);
    	
    	BeanFieldGroup<JobApplication> binder = new BeanFieldGroup<>(JobApplication.class);
    	binder.setItemDataSource(ja);
    	
    	Field<?> tfFirstName = binder.buildAndBind("First Name", "firstName");
    	Field<?> tfLastName = binder.buildAndBind("Last Name", "lastName");
    	Field<?> tfGender = binder.buildAndBind("Gender", "gender", ComboBox.class);
    	
    	Field<?> openText = binder.buildAndBind("Why are you applying to this job?", "openText", TextArea.class);
    	
        FormLayout form = new FormLayout();
    	form.setStyleName(CLASSNAME);
        form.setWidth("80%");
    	
        form.addComponent(tfFirstName);
        form.addComponent(tfLastName);
        form.addComponent(tfGender);
        form.addComponent(openText);


        Button button = new Button("Submit");
        
        button.addClickListener( e -> {
        	
        	if (!binder.isValid())
        	{
        		new Notification("Error!",
					    "There are errors in your job application. Please fix errors in form and try again.",
					    Notification.Type.ERROR_MESSAGE, true)
					    .show(Page.getCurrent());
        		
        		return;
        	}
        	
        	try {
        		binder.commit();
        		
        		ja.setSubmitTime(new Timestamp(System.currentTimeMillis()));

            	repository.save(ja);
            	
            	getUI().getNavigator().navigateTo(ConfirmationView.VIEW_NAME);    
			} catch (javax.validation.ConstraintViolationException ex) {

				StringBuilder sb = new StringBuilder();
				
				Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
				
				sb.append("<ul>");
				
				for (ConstraintViolation<?> violation : violations) {
					sb.append("<li>");
					sb.append(violation.getMessage());
					sb.append("</li>");
				}
				
				sb.append("</ul>");
				
				new Notification("Error!",
					    "Form validation failed: " + sb.toString(),
					    Notification.Type.ERROR_MESSAGE, true)
					    .show(Page.getCurrent());
			} catch (CommitException e1) {
				new Notification("Error!",
					    "Unable to save job application!",
					    Notification.Type.ERROR_MESSAGE, true)
					    .show(Page.getCurrent());
			}
        	    	
        });
        
        form.addComponent(button);
        
        addComponent(form);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        
    }
}