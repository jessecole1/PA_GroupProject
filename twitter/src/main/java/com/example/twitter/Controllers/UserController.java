package com.example.twitter.Controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.twitter.Models.LoginUser;
import com.example.twitter.Models.User;
import com.example.twitter.Services.UserService;

@Controller
public class UserController {
	
	@Autowired
	public UserService userServ;
	
	@GetMapping("/")
	public String loginOrReg(Model model) {
//		model.addAttribute("newUser", new User());
//		model.addAttribute("newLoginUser", new LoginUser());
		return "index.jsp";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("newUser", new User());
		return "register.jsp";
	}
	
	@PostMapping("/register/user")
	public String registerUser(@Valid @ModelAttribute("newUser") User newUser, 
			BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "redirect:/register";
		}
		userServ.register(newUser, result);
		session.setAttribute("userId", newUser.getId());
		return "redirect:/home";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("newLoginUser", new LoginUser());
		return "login.jsp";
	}
	
	@GetMapping("/home")
	public String homePage(Model model, HttpSession session) {
		
		return "homePage.jsp";
	}
}
