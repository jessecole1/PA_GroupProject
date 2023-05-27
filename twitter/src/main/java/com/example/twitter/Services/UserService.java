package com.example.twitter.Services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.twitter.Models.Comment;
import com.example.twitter.Models.LoginUser;
import com.example.twitter.Models.User;
import com.example.twitter.Repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public User register(User newUser, BindingResult result) {
		
		Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
		System.out.println(potentialUser);
		if(potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "Email already taken");
			System.out.println("testing the result");
			return null;
		}
		Optional<User> potentialUserTwo = userRepo.findByUsername(newUser.getUsername());
		if(potentialUserTwo.isPresent()) {
			result.rejectValue("username", "Matches", "Username already taken");
			return null;
		}
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("password", "Matches", "Make sure passwords are matched");
		}
		if(result.hasErrors()) {
			System.out.println("testing here");
			return null;
		}
		
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		return userRepo.save(newUser);
	}
	
	public User login(LoginUser newLogin, BindingResult result) {
		Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
		
		if(!potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "Can't match email or password");
			return null;
		}
		User user = potentialUser.get();
		if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Can't match email or password");
		}
		if(result.hasErrors()) {
			return null;
		}
		return user;
	}
	
	public User getById(Long id) {
		Optional<User> potentialUser = userRepo.findById(id);
		if (potentialUser.isPresent()) {
			User user = potentialUser.get();
			return user;
		}
		return null;
	}
	
	public User save(User user) {
		return userRepo.save(user);
	}
	
	public User update(User user) {
		System.out.println("test 1");
//		theUser.setBio(user.getBio());
		return userRepo.save(user);
//		System.out.println("testing line");
	}
//
//		if(!BCrypt.checkpw(theUser.getPassword(), theUser.getPassword())) {
////			System.out.println("1: " + theUser.getPassword());
////			System.out.println("- - - - -");
////			System.out.println("2: " + user.getPassword());
//			System.out.println("testing bcrypt");
//			result.rejectValue("password", "Matches", "Can't match email or password");
//			return null;
//		}
//		if(!BCrypt.checkpw(theUser.getConfirm(), user.getConfirm())) {
//			result.rejectValue("confirm", "Matches", "Can't match confirm");
//		}
//		System.out.println(result);
//		if (result.hasErrors()) {
//			System.out.println("testing if it gets to errors");
//			return null;
//		}
//		System.out.println(user.getBio());
//		theUser.setBio(user.getBio());
//		System.out.println(theUser.getBio());
//		
//		return userRepo.save(theUser);
//	}
	
	
//	public void deleteComments(List<Comment> comments) {
//		userRepo.deleteAll();
//	}

}
