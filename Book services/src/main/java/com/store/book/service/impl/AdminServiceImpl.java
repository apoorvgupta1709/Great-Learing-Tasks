package com.store.book.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.book.entity.Admin;
import com.store.book.entity.Book;
import com.store.book.entity.User;
import com.store.book.repository.AdminRepository;
import com.store.book.repository.UserRepository;
import com.store.book.service.AdminService;


@Service
@Transactional
public class AdminServiceImpl implements AdminService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdminRepository adminRepo;

	@Override
	public boolean loginAdmin(String email, String password) {
		List<Admin> list=adminRepo.findAll();//check if user  exist
		for (Admin i : list) {
			if(i.getEmail().equals(email) &&i.getPassword().equals(password))
			{
				return true;
			} 
		}
		return false;
	}

	@Override
	public boolean saveAdmin(String email, String name, String password, String gender, String phone) {

		Admin admin=Admin.builder().email(email).name(name).password(password).gender(gender)
				.phone(phone).build();

		List<Admin> list=adminRepo.findAll();//check if user already exist
		for (Admin i : list) {
			if(i.getEmail().equals(email))
			{
				return false;
			}

		}

		adminRepo.save(admin);
		return true;
	}

	@Override
	public boolean createUser(String email, String name, String pass, String gender, String phone) {
		User user=User.builder().email(email).name(name).password(pass).gender(gender)
				.phone(phone).build();

		List<User> list=userRepository.findAll();//check if user already exist
		for (User i : list) {
			if(i.getEmail().equals(email))
			{
				return false;
			}

		}

		userRepository.save(user);
		return true;
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public boolean deleteUser(String email) {
		List<User> list=userRepository.findAll();//check if book name  exist

		for (User i : list) {
			if(i.getEmail().equals(email))
			{
				userRepository.deleteById(i.getId());
				return true;

			}

		}	
		return false;
	}

	@Override
	public boolean updateUser(String email, String name, String pass, String gender, String phone) {
		List<User> list=userRepository.findAll();//check if book name  exist

		for (User i : list) {
			if(i.getEmail().equals(email))
			{
				userRepository.updateUser(email,name,pass,gender,phone,i.getLikeBook(),i.getReadLater(),i.getId());
				return true;

			}

		}		
		return false;
	}

}
