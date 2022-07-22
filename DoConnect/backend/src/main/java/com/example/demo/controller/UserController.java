package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	    @Autowired
	    private UserService userservice;

	    @PostMapping("/login")//
	    private String loginUserAndAdmin(@RequestBody HashMap<String, String> body) {
	        String email = body.get("email");
	        String password = body.get("password");
	        if (email.equals("admin@gmail.com") && password.equals("123456")) {
	            return "admin";
	        } else if (userservice.isExists(email, password)) {
	            return "ok";
	        } else {
	            return "no";
	        }
	    }


	    @GetMapping("/useremail/{email}")//
	    private User getUserByEmail(@PathVariable String email) {
	        return userservice.findByEmail(email);
	    }

	    @PostMapping("/register")//
	    private String registerUser(@RequestBody User u) {
	        if (userservice.isExists(u.getEmail(), u.getPassword())) {
	            return "no";
	        } else {
	            userservice.save(u);
	            return "ok";
	        }
	    }


	    @GetMapping("/users/unique/{name}")//
	    private List<String> getUniqueUsersByNAme(@PathVariable String name) {
	        return userservice.getByDistinct(name);
	    }

	
	

}
