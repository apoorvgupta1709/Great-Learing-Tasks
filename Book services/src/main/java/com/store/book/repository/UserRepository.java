package com.store.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.store.book.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	@Modifying
	@Query(value = "update User p set p.email =:email,p.name =:name,p.password =:password,p.gender =:gender"
			+ ",p.phone =:phone,p.LikeBook =:LikeBook,p.ReadLater =:ReadLater where p.id =:id")
	void updateUser(@Param("email") String email,
			@Param("name") String name,@Param("password") String password,
			@Param("gender") String gender,@Param("phone") String phone,
			@Param("LikeBook") String LikeBook,@Param("ReadLater") String ReadLater, int id);
	
	@Modifying
	@Query(value = "update User p set p.ReadLater =:ReadLater where p.id =:id")
	void updateUserReadLater(@Param("ReadLater") String ReadLater, int id);
	@Modifying
	@Query(value = "update User p set p.LikeBook =:LikeBook where p.id =:id")
	void updateLikeBook(@Param("LikeBook") String LikeBook, int id);
	
	

}
