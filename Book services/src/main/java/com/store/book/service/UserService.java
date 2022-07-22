package com.store.book.service;

import java.util.List;

import com.store.book.entity.Book;

public interface UserService {

	String loginCustomer(String email, String password);
	boolean save(String email, String name, String pass, String gender, String phone);
	String addReadLater(int bookId, int studentId);
	String addLikeBook(int bookId, int studentId);
	List<Book> getReadLaterBook(int studentId);
	List<Book> getLikeBook(int studentId);


}
