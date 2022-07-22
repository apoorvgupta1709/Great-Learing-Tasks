package com.store.book.service.impl;

import java.awt.desktop.UserSessionListener;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.book.entity.Book;
import com.store.book.entity.User;
import com.store.book.repository.UserRepository;
import com.store.book.service.BookService;
import com.store.book.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookService bookService;


	@Override
	public String loginCustomer(String email, String password) {
		List<User> list=userRepository.findAll();//check if user  exist
		for (User i : list) {
			if(i.getEmail().equals(email) &&i.getPassword().equals(password))
			{
				return i.getName();
			} 
		}
		return "false";
	}

	@Override
	public boolean save(String email, String name, String password, String gender, String phone) {

		User user=User.builder().email(email).name(name).password(password).gender(gender)
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
	public String addReadLater(int bookId, int studentId) {

		List<User> list=userRepository.findAll();//check if user  exist

		for (User i : list) {
			if(i.getId()==studentId)
			{
				if(i.getReadLater()!=null) //adding read later for first time 
				{
					String arr[]=i.getReadLater().split(" ");
					for(int j=0;j<arr.length;j++) 
					{
						if(bookId==Integer.parseInt(arr[j])) 
						{
							return "Book already exist";
						}

					}
				}
				String var= bookId+ " "+(i.getReadLater()==null ?"":i.getReadLater()); // avoid null for first time 
				userRepository.updateUserReadLater(var,studentId);
				return "Added Successful";
			}

		}

		return "invalid User";
	}

	@Override
	public String addLikeBook(int bookId, int studentId) {
		List<User> list=userRepository.findAll();//check if user  exist

		for (User i : list) {
			if(i.getId()==studentId)
			{
				if(i.getLikeBook()!=null) //adding Like for first time 
				{
					String arr[]=i.getLikeBook().split(" ");
					for(int j=0;j<arr.length;j++) 
					{
						if(bookId==Integer.parseInt(arr[j])) 
						{
							return "Book already exist";
						}

					}
				}
				String var= bookId+ " "+(i.getLikeBook()==null ?"":i.getLikeBook()); // avoid null for first time 
				userRepository.updateLikeBook(var,studentId);
				return "Added Successful";
			}

		}

		return "invalid User";
	}

	@Override
	public List<Book> getReadLaterBook(int studentId) {
		List<Book> bookCollection=bookService.getAllBook();//all books collection
		List<Book> list2=new ArrayList<>();// list to return

		List<User> list=userRepository.findAll();//collection of user

		for (User i : list) {
			if(i.getId()==studentId)
			{
				if(i.getReadLater()!=null) ///edge case handling 
				{
					String arr[]=i.getReadLater().split(" ");
					for(int j=0;j<arr.length;j++) 
					{
						for (Book book : bookCollection) 
						{
							if(book.getId()==Integer.parseInt(arr[j]))// adding all matched book in collection to return
							{
								list2.add(book);
							}
						}

					}
				}

			}

		}
		return list2;

	}

	@Override
	public List<Book> getLikeBook(int studentId) {
		List<Book> bookCollection=bookService.getAllBook();//collection of book
		List<Book> list2=new ArrayList<>();// list to return 

		List<User> list=userRepository.findAll();//collection of user

		for (User i : list) {
			if(i.getId()==studentId)
			{
				if(i.getLikeBook()!=null) //edge case handling
				{
					String arr[]=i.getLikeBook().split(" ");
					for(int j=0;j<arr.length;j++) 
					{
						for (Book book : bookCollection) 
						{
							if(book.getId()==Integer.parseInt(arr[j]))// adding all matched book in collection to return
							{
								list2.add(book);
							}
						}

					}
				}

			}

		}
		return list2;
	}

}
