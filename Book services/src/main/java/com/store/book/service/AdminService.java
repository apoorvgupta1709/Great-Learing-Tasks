package com.store.book.service;

import java.util.List;

import com.store.book.entity.Book;
import com.store.book.entity.User;

public interface AdminService {

	boolean loginAdmin(String email, String password);
	boolean saveAdmin(String email, String name, String pass, String gender, String phone);

	boolean createUser(String email, String name, String pass, String gender, String phone);

	
	List<User> getAllUser();

	boolean deleteUser(String email);

	boolean updateUser(String email, String name, String pass, String gender, String phone);
}
