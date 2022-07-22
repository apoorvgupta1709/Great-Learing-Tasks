package com.store.book.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id ;
	
	String email;
	String name;
	String password;
	String gender;
	String phone;


}
