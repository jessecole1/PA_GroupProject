package com.example.twitter.Controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.twitter.Models.Comment;
import com.example.twitter.Models.Tweet;
import com.example.twitter.Models.User;
import com.example.twitter.Services.TweetService;
import com.example.twitter.Services.UserService;

@Controller
public class TweetController {
	
	@Autowired
	private TweetService tweetServ;
	
	@Autowired
	private UserService userServ;
	
	@PostMapping("/tweet/new")
	public String createTweet(@Valid @ModelAttribute("newTweet") Tweet newTweet, 
			BindingResult result, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		if (result.hasErrors()) {
			model.addAttribute("tweets", tweetServ.findAll());
			return "homePage.jsp";
		}
		newTweet.setLikeNum(0);
		newTweet.setCommentNum(0);
		tweetServ.save(newTweet);
		return "redirect:/home";
	}
	
	@GetMapping("/tweet/like/{id}")
	public String likeTweet(@PathVariable("id") Long tweetId, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userServ.getById(userId) == null) {
			return "index.jsp";
		}
		Tweet tweet = tweetServ.findById(tweetId);
		User user = userServ.getById(userId);
		boolean liked = false;
		for (User usert : tweet.getLikes()) {
			System.out.println("i id: " + user.getId());
			System.out.println("logged in id: " + userId);
			if (usert.getId() == user.getId()) {
				liked = true;
			}
		}
		if (liked == true) {
			return "redirect:/home";
		}
		tweet.getLikes().add(user);
		tweet.setLikeNum(tweet.getLikeNum()+1);
		tweetServ.save(tweet);
		return "redirect:/home";
	}
	
	@PostMapping("/tweet/comment/{tweetId}")
	public String addComment(@PathVariable("tweetId") Long tweetId, Model model, @Valid @ModelAttribute("newComment") Comment newComment, 
			BindingResult result, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userServ.getById(userId) == null) {
			return "index.jsp";
		}
		if (result.hasErrors()) {
			return "redirect:/home";
		}
		tweetServ.addComment(newComment);
		Integer theComment = tweetServ.findById(tweetId).getCommentNum();
		theComment++;
		tweetServ.findById(tweetId).setCommentNum(theComment);
		tweetServ.save(tweetServ.findById(tweetId));
		model.addAttribute("newTweet", new Tweet());
		model.addAttribute("newComment", new Comment());
		return "redirect:/tweet/{tweetId}";
	}
	
	@GetMapping("/tweet/{id}")
	public String oneTweet(@PathVariable("id") Long tweetId, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userServ.getById(userId) == null) {
			return "index.jsp";
		}
		model.addAttribute("tweet", tweetServ.findById(tweetId));
		model.addAttribute("user", userServ.getById(userId));
		model.addAttribute("newComment", new Comment());
		return "oneTweet.jsp";
	}
	
}
