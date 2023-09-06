package com.vot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vot.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	 //User findByEmail(String email);
	 
	 User findByEmail(String email);
	 
	 User findByPassword(String password);
}