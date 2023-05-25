package com.example.twitter.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.twitter.Models.User;

@Repository 
public interface UserRepository extends CrudRepository<User, Long> {

	public List<User> findAll();
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsername(String username);
	
}
