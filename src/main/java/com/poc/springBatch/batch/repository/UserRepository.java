package com.poc.springBatch.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.springBatch.batch.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	
}
