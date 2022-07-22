package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.User;


public interface UserRepository extends JpaRepository<User,Integer> {

	//	@Modifying
	//	@Query(value = "update User p set p.email =:email,p.name =:name,p.password =:password,p.gender =:gender"
	//			+ ",p.phone =:phone where p.id =:id")
	//	void updateUser(@Param("email") String email,
	//			@Param("name") String name,@Param("password") String password,
	//			@Param("gender") String gender,@Param("phone") String phone,
	//			 int id);
//
	User findUserByEmail(String email);
//
	User findUserByPassword(String password);

	@Query("SELECT u.name FROM User u WHERE u.name!=:name")
	List<String> getByUniqueUser(@Param("name") String name);

}
