package com.cspl.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonInfo {
	    private String name;
	    private String pan;
	    private String dob;
	    private String emailId;
	    
	    public PersonInfo setName(String name) {
	        this.name = name;
	        return this;
	    }

	    public PersonInfo setPan(String pan) {
	        this.pan = pan;
	        return this;
	    }

	    public PersonInfo setDob(String dob) {
	        this.dob = dob;
	        return this;
	    }

	    public PersonInfo setEmailId(String emailId) {
	        this.emailId = emailId;
	        return this;
	    }

}
