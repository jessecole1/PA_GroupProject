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
import org.springframework.web.bind.annotation.PutMapping;

import com.example.twitter.Models.Comment;
import com.example.twitter.Models.Tweet;
import com.example.twitter.Models.User;
import com.example.twitter.Services.CommentService;
import com.example.twitter.Services.TweetService;
import com.example.twitter.Services.UserService;

@Controller
public class TweetController {
	
	@Autowired
	private TweetService tweetServ;
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private CommentService commentServ;
	
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
		tweetServ.save(newTweet, result);
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
			tweet.getLikes().remove(user);
			tweet.setLikeNum(tweet.getLikeNum()-1);
			tweetServ.saveLike(tweet);
			return "redirect:/home";
		}
		tweet.getLikes().add(user);
		tweet.setLikeNum(tweet.getLikeNum()+1);
		tweetServ.saveLike(tweet);
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
		
		if (newComment.getTweet().getUser().getId() == newComment.getUser().getId()) {
			newComment.setNotified(true);			
		} else {
			newComment.setNotified(false);
		}
		
		tweetServ.addComment(newComment);
		Integer theComment = tweetServ.findById(tweetId).getCommentNum();
		theComment++;
		tweetServ.findById(tweetId).setCommentNum(theComment);
		tweetServ.save(tweetServ.findById(tweetId), result);
		
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
	
	@PostMapping("/tweet/delete/{tweetId}")
	public String deleteTweet(@PathVariable("tweetId") Long tweetId, 
		Model model, HttpSession session) {
		
		Long userId = (Long) session.getAttribute("userId");
		if (userServ.getById(userId) == null) {
			return "redirect:/home";
		}
		Tweet tweet = tweetServ.findById(tweetId);
		for (Comment comm : tweet.getComments()) {
			commentServ.destroyById(comm.getId());
		}
		
		tweetServ.destroyById(tweetId);
		
		return "redirect:/home";
	}
	
	@GetMapping("/tweet/edit/{tweetId}")
	public String editTweet(@ModelAttribute("newTweet") Tweet tweet, @PathVariable("tweetId") Long tweetId, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userServ.getById(userId) == null) {
			return "redirect:/home";
		}
		model.addAttribute("tweet", tweetServ.findById(tweetId));
		model.addAttribute("user", userServ.getById(userId));
		return "editTweet.jsp";
	}
	
	@PutMapping("/update/{id}/tweet")
	public String updateTweet(@Valid @ModelAttribute("newTweet") Tweet newTweet, 
		BindingResult result, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userServ.getById(userId) == null) {
			return "redirect:/home";
		}
		if (result.hasErrors()) {
			return "editTweet.jsp";
		}
		tweetServ.update(newTweet);
		return "redirect:/home";
	}
	
}
