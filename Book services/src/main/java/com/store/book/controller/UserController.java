package com.store.book.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.book.entity.Book;
import com.store.book.service.BookService;
import com.store.book.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
	
	//Return name to see on every page if I am a login user
	@PostMapping("/login")
	String validateUser(String email, String pass){
		
		return userService.loginCustomer(email,pass);
		
	}
	
	@GetMapping("/logout")
	public String UserLogoutPage(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		session = request.getSession();
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		session.invalidate();
		return "user_Logout";
	}
	
	@PostMapping("/register")
	boolean saveUser(String email, String name, String pass, String gender, String phone){
		
		return userService.save(email,name,pass,gender,phone);
		
	}
	
	
	@GetMapping("/book")
	List<Book> getBooks(String mrpPrice, String name, String image){
		
		return bookService.getAllBook();
		
	}
	
	@PostMapping("/readLater")
	String addReadLaterBook(int bookId,int studentId){
		
		return userService.addReadLater(bookId,studentId);
		
	}
	@GetMapping("/readLater")
	List<Book> getReadLater(int studentId){
		
		return userService.getReadLaterBook(studentId);
		
	}
	
	@PostMapping("/likeBook")
	String addLikedBook(int bookId,int studentId){
		
		return userService.addLikeBook(bookId,studentId);
		
	}
	
	@GetMapping("/likeBook")
	List<Book> getLikeBook(int studentId){
		
		return userService.getLikeBook(studentId);
		
	}
	

}
