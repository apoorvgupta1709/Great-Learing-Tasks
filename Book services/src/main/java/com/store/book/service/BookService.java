package com.store.book.service;

import java.util.List;

import com.store.book.entity.Book;

public interface BookService {

	boolean saveBook( String mrpPrice, String name, String image);
	
	List<Book> getAllBook();

	boolean deleteBook(String name);

	boolean updateBook(String mrpPrice, String name, String image);
}
