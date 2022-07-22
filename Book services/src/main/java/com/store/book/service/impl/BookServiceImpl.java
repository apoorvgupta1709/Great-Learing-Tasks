package com.store.book.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.book.entity.Admin;
import com.store.book.entity.Book;
import com.store.book.repository.BookRepository;
import com.store.book.service.BookService;

@Transactional
@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepo;

	@Override
	public boolean saveBook( String mrpPrice, String name, String image) {

		
		Book book=Book.builder().mrpPrice(mrpPrice).name(name).image(image).build();
		
		List<Book> list=bookRepo.findAll();//check if book name  already exist
		for (Book i : list) {
			if(i.getName().equals(name))
			{
				return false;
			}
			 
        }
		
		bookRepo.save(book);
		return true;	
		}

	@Override
	public List<Book> getAllBook() {

		List<Book> list=bookRepo.findAll();

		return list;
	}

	@Override
	public boolean deleteBook(String name) {
		
		List<Book> list=bookRepo.findAll();//check if book name  exist
		
		for (Book i : list) {
			if(i.getName().equals(name))
			{
				bookRepo.deleteById(i.getId());
				return true;

			}
			 
        }	
		return false;
	}

	@Override
	public boolean updateBook(String mrpPrice, String name, String image) {
	List<Book> list=bookRepo.findAll();//check if book name  exist
		
		for (Book i : list) {
			if(i.getName().equals(name))
			{
				bookRepo.updateProduct(name,mrpPrice,image,i.getId());
				return true;

			}
			 
        }		
		return false;
	}

}
