package com.nikop;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
public class JobApplication {
	
	

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min = 3, max = 50, message = "First Name must be between 3 and 50 characters")
    private String firstName = "";
    
    @Size(min = 3, max = 50, message = "Last Name must be between 3 and 50 characters")
    private String lastName = "";
    
    @Size(min = 1, message = "Please Answer the Question")
    private String openText = "";
    
    @NotNull(message = "Please Select Gender")
    private Gender gender;
    
    private java.sql.Timestamp submitTime;

    public long getId() {
		return id;
	}
    
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOpenText() {
		return openText;
	}

	public void setOpenText(String openText) {
		this.openText = openText;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public java.sql.Timestamp getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(java.sql.Timestamp submitTime) {
		this.submitTime = submitTime;
	}  	
	
}
