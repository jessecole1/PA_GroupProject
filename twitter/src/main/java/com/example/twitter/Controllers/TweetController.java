package com.example.twitter.Controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.twitter.Models.Tweet;
import com.example.twitter.Services.TweetService;

@Controller
public class TweetController {
	
	@Autowired
	private TweetService tweetServ;
	
	@PostMapping("/tweet/new")
	public String createTweet(@Valid @ModelAttribute("newTweet") Tweet newTweet, 
			BindingResult result, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		if (result.hasErrors()) {
//			model.addAttribute("newTweet", new Tweet());
			return "homePage.jsp";
		}
		tweetServ.save(newTweet);
		return "redirect:/home";
	}

}
