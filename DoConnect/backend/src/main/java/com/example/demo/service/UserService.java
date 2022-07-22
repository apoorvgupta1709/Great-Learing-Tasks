package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	 @Autowired
	    private UserRepository userRepository;

	    public boolean isExists(String email, String password) {
	        return userRepository.findUserByPassword(password) != null && userRepository.findUserByEmail(email) != null;
	    }

	    public void save(User u) {
	        userRepository.save(u);
	    }


	    public void update(User u) {
	        userRepository.save(u);
	    }

	    public User findByEmail(String email) {
	        return userRepository.findUserByEmail(email);
	    }

	    public List<String> getByDistinct(String name) {
	        return userRepository.getByUniqueUser(name);
	    }
	
	

}
