package com.store.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.store.book.entity.Book;

public interface BookRepository extends JpaRepository<Book,Integer>  {
	
	@Modifying
	@Query(value = "update Book p set p.name =:name,p.mrpPrice =:mrpPrice,p.image =:image where p.id =:id")
	void updateProduct(@Param("name") String name, @Param("mrpPrice") String mrpPrice, @Param("image") String image, int id);

	
}
