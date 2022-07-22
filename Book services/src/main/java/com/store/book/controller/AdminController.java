package com.store.book.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.book.entity.Book;
import com.store.book.entity.User;
import com.store.book.service.AdminService;
import com.store.book.service.BookService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminServie;
	@Autowired
	private BookService bookService;
	
	@PostMapping("/login")
	boolean validateAdmin(String email, String pass){
		
		return adminServie.loginAdmin(email,pass);
		
	}
	
	@GetMapping("/logout")
	public String AdminLogoutPage(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		session = request.getSession();
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		session.invalidate();
		return "admin_logout";
	}
	
	
	@PostMapping("/register")
	boolean saveAdmin(String email, String name, String pass, String gender, String phone){
		
		return adminServie.saveAdmin(email,name,pass,gender,phone);
		
	}
		
	// Admin CRUD on USER
	
	@PostMapping("/user")
	boolean createUser(String email, String name, String pass, String gender, String phone){
		
		return adminServie.createUser(email,name,pass,gender,phone);
		
	}
	
	@GetMapping("/user")
	List<User> getUser(String mrpPrice, String name, String image){
		
		return adminServie.getAllUser();
		
	}

	@PutMapping("/user")
	boolean updateUser(String email, String name, String pass, String gender, String phone){
		
		return adminServie.updateUser(email,name,pass,gender,phone);
		
	}
	
	@DeleteMapping("/user")
	boolean DeleteUser( String email){
		
		return adminServie.deleteUser(email);
		
	}
	
	//Admin CRUD on Book
	
	@PostMapping("/book")
	boolean createBook(String mrpPrice, String name, String image){
		
		return bookService.saveBook(mrpPrice, name, image);
		
	}
	
	@GetMapping("/book")
	List<Book> getBooks(String mrpPrice, String name, String image){
		
		return bookService.getAllBook();
		
	}
	
	@PutMapping("/book")
	boolean updateBooks(String mrpPrice, String name, String image){
		
		return bookService.updateBook(mrpPrice, name, image);
		
	}
	
	@DeleteMapping("/book")
	boolean deleteBooks( String name){
		
		return bookService.deleteBook(name);
		
	}
	
	

}
