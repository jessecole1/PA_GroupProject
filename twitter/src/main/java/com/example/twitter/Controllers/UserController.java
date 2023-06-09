package com.example.twitter.Controllers;


import java.util.ArrayList;
import java.util.List;

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
import com.example.twitter.Models.FollowRelationship;
import com.example.twitter.Models.LoginUser;
import com.example.twitter.Models.Tweet;
import com.example.twitter.Models.User;
import com.example.twitter.Services.CommentService;
import com.example.twitter.Services.TweetService;
import com.example.twitter.Services.UserService;

@Controller
public class UserController {
	
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private TweetService tweetServ;
	
	@Autowired
	private CommentService commentServ;
	
	@GetMapping("/")
	public String loginOrReg(Model model) {
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
		userServ.register(newUser, result);
		if (result.hasErrors()) {
			return "register.jsp";
		}
			session.setAttribute("userId", newUser.getId());
			return "redirect:/home";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("newLoginUser", new LoginUser());
		return "login.jsp";
	}
	
	@PostMapping("/login/user")
	public String loginUser(@Valid @ModelAttribute("newLoginUser") LoginUser newLogin, 
			BindingResult result, Model model, HttpSession session) {
		User user = userServ.login(newLogin, result);
		if (result.hasErrors()) {
			return "login.jsp";
		}
		session.setAttribute("userId", user.getId());
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String homePage(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "index.jsp";
		}
		Long userId = (Long) session.getAttribute("userId");
		if (userServ.getById(userId) == null) {
			return "redirect:/";
		}
		model.addAttribute("newTweet", new Tweet());
		model.addAttribute("newLike", new Tweet());
		model.addAttribute("newUser", new User());
		
		List<Tweet> tweets = tweetServ.findAll();
		
		model.addAttribute("tweets", tweetServ.findAll());
		
		User user = userServ.getById(userId);
		
		List<Comment> unnotifiedComments = new ArrayList<Comment>();
		List<Tweet> userTweets = user.getTweets();
		int count = 0;
		for (Tweet tweet : userTweets) {
			List<Comment> tweetComments = tweet.getComments();
			for (Comment com : tweetComments) {
				if (com.isNotified() == false) {
					unnotifiedComments.add(com);
					System.out.println("got here");
					count++;
				}
			}
		}
		model.addAttribute("count", count);
		model.addAttribute("user", user);
//		List<FollowRelationship> followedUsers = user.getFollowing();
//		for (FollowRelationship followedUser : followedUsers) {
//			User aFollowedUser = followedUser.getTo();
//			User aFollowedUser = userServ.getById(followedUsersid);]
//		}
		
		
		
		return "homePage.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("userId", null);	
		return "redirect:/";
	}
	
	@GetMapping("/user/notifications")
	public String notifications(Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userServ.getById(userId) == null) {
			return "redirect:/";
		}
		
		List<Tweet> tweets = tweetServ.findAll();
		model.addAttribute("user", userServ.getById(userId));
		model.addAttribute("userTweets", tweets);
		User user = userServ.getById(userId);
		return "notifications.jsp";
	}
	
	@GetMapping("/home/from/notification")
	public String homeFromNotification(Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userServ.getById(userId) == null) {
			return "redirect:/";
		}
		model.addAttribute("newTweet", new Tweet());
		model.addAttribute("tweets", tweetServ.findAll());
		model.addAttribute("newLike", new Tweet());
		model.addAttribute("newUser", new User());
		
		List<Tweet> tweets = tweetServ.findAll();
		User user = userServ.getById(userId);
		
		List<Comment> unnotifiedComments = new ArrayList<Comment>();
		List<Tweet> userTweets = user.getTweets();
		int count = 0;
		for (Tweet tweet : userTweets) {
			List<Comment> tweetComments = tweet.getComments();
			for (Comment com : tweetComments) {
					com.setNotified(true);
					commentServ.save(com);
				}
			}
		model.addAttribute("count", count);
		model.addAttribute("user", user);
		return "homePage.jsp";
	}
	
	@GetMapping("/user/profile/{usersId}")
	public String userProfile(@PathVariable("usersId") Long userId, @ModelAttribute("aUser") User aUser, Model model, HttpSession session) {
		Long theUserId = (Long) session.getAttribute("userId");
		if (userServ.getById(theUserId) == null) {
			return "redirect:/";
		}
		User theUser = userServ.getById(theUserId);
		User nextUser = userServ.getById(userId);
		
		model.addAttribute("profileUser", userServ.getById(userId));
		model.addAttribute("user", theUser);

		
		return "userProfile.jsp";
	}
	
	@GetMapping("/user/profile/update")
	public String updateProfile(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		Long theUserId = (Long) session.getAttribute("userId");
		if (userServ.getById(theUserId) == null) {
			return "redirect:/";
		}
		model.addAttribute("user", userServ.getById(theUserId));
		return "updateProfile.jsp";
	}
	
	@PostMapping("/profile/update")
	public String update(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		Long theUserId = (Long) session.getAttribute("userId");
		if (userServ.getById(theUserId) == null) {
			return "redirect:/";
		}
		
		User aUser = userServ.getById(user.getId());
		aUser.setBio(user.getBio());
		userServ.update(user);
		return "redirect:/home";
	}
	
//	@PostMapping("/user/follow/{usersId}")
//	public String follow(@PathVariable("usersId") User follow, @Valid @ModelAttribute("aUser") User aUser, Model model, HttpSession session) {
//		Long theUserId = (Long) session.getAttribute("userId");
//		if (userServ.getById(theUserId) == null) {
//			return "redirect:/";
//		}
//		User user = userServ.getById(theUserId);
//		List<User> followers = user.getFollows();
//		followers.add(follow);
//		System.out.println(followers);
//		return "redirect:/home";
//	}
	
	
	
	@PostMapping("/user/follow/{usersId}")
	public String follow(@PathVariable("usersId") Long followedUsersId, 
			@Valid @ModelAttribute("aUser") User aUser, BindingResult result, 
			Model model, HttpSession session) {

		Long theUserId = (Long) session.getAttribute("userId");
		if (userServ.getById(theUserId) == null) {
			return "redirect:/";
		}

		User theUser = userServ.getById(theUserId);
		User followedUser = userServ.getById(followedUsersId);
		
		userServ.follow(theUser, followedUser);
		
//		List<User> theUsersFollowList = theUser.getFollows();
//		theUsersFollowList.add(followedUser);
//		theUser.setFollows(theUsersFollowList);
		
		return "redirect:/user/profile/{usersId}";
	}
//	
//	@PostMapping("/user/unfollow/{usersId}")
//	public String unfollow(@PathVariable("usersId") Long followedUsersId,
//			@Valid @ModelAttribute("aUser") User aUser, BindingResult result,
//			Model model, HttpSession session) {
//		
//		Long theUserId = (Long) session.getAttribute("userId");
//		if (userServ.getById(theUserId) == null) {
//			return "redirect:/";
//		}
//		
//		User theUser = userServ.getById(theUserId);
//		User followedUser = userServ.getById(followedUsersId);
//		
//		userServ.unfollow(theUser, followedUser);
//		
//		return "redirect:/user/profile/{usersId}";
//	}
//	
	@GetMapping("/user/follows/{usersId}")
	public String whoUserFollows(@PathVariable("usersId") Long profileUsersId, 
			Model model, HttpSession session) {
		
		Long theUserId = (Long) session.getAttribute("userId");
		if (userServ.getById(theUserId) == null) {
			return "redirect:/";
		}
		
		User theUser = userServ.getById(theUserId);
		model.addAttribute("profileUser", userServ.getById(profileUsersId));
		
		
		List<FollowRelationship> followedUsers = theUser.getFollowing();
		ArrayList<User> followedUserList = new ArrayList<>();
		for (FollowRelationship followedUser : followedUsers) {
			if (followedUser.getFrom() == userServ.getById(profileUsersId)) {
				User aFollowedUser = followedUser.getTo();
				followedUserList.add(aFollowedUser);				
			}
			
			
		}
		
		model.addAttribute("followedUsers", followedUserList);
		return "followList.jsp";
	}
}
