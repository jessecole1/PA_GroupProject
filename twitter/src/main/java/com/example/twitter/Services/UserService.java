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
		if(potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "Email already taken");
		}
		Optional<User> potentialUserTwo = userRepo.findByUsername(newUser.getUsername());
		if(potentialUserTwo.isPresent()) {
			result.rejectValue("username", "Matches", "Username already taken");
		}
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("password", "Matches", "Make sure passwords are matched");
		}
		if(result.hasErrors()) {
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
	
//	public void deleteComments(List<Comment> comments) {
//		userRepo.deleteAll();
//	}

}
