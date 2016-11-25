package com.nikop;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.ContextLoaderListener;

import com.vaadin.annotations.Theme;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.*;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme("mytheme")
@SpringUI
public class MyUI extends UI {
	
	@WebListener
    public static class MyContextLoaderListener extends ContextLoaderListener {
    }

    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    public static class MyUIServlet extends SpringVaadinServlet {
    	
    }
	
    @Configuration
    @EnableVaadin
    public static class MyConfiguration {
    }
    
    @Autowired
    private ViewDisplay viewDisplay;

    @Override
    protected void init(VaadinRequest vaadinRequest) {    	
    	getPage().setTitle("Application Form");
    	
    	CssLayout rootLayout = new CssLayout();
    	rootLayout.setStyleName("container");
    	Label header = new Label("Yritys Oy");
    	header.setStyleName(ValoTheme.LABEL_H1);
    	
    	viewDisplay.setStyleName("page");
    	
    	rootLayout.addComponents(header, viewDisplay);
    	
    	setContent(rootLayout);
    }

    
}