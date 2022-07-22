package com.store.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.book.entity.Admin;
import com.store.book.entity.User;

public interface AdminRepository  extends JpaRepository<Admin,Integer> {

}
