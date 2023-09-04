package com.vot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vot.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	 User findByEmail(String email);
	 User findByPassword(String password);
}