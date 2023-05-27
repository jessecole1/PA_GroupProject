package com.example.twitter.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.twitter.Models.Comment;
import com.example.twitter.Models.Tweet;
import com.example.twitter.Repositories.CommentRepository;
import com.example.twitter.Repositories.TweetRepository;

@Service
public class TweetService {
	
	@Autowired
	private TweetRepository tweetRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	public Tweet save(Tweet newTweet, BindingResult result) {
		if (result.hasErrors()) {
			result.rejectValue("content", "Matches", "Tweet must be at least 3 characters");
		}
		if (result.hasErrors()) {
			return null;
		}
		return tweetRepo.save(newTweet);
	}
	
	public Tweet saveLike(Tweet newTweet) {
		return tweetRepo.save(newTweet);
	}
	
	public List<Tweet> findAll() {
		return tweetRepo.findAll();
	}
	
	public Tweet findById(Long id) {
		Optional<Tweet> potentialTweet = tweetRepo.findById(id);
		if (!potentialTweet.isPresent()) {
			return null;
		}
		return potentialTweet.get();
	}
	
	public Comment addComment(Comment comment) {
		return commentRepo.save(comment);
	}
	
	public void destroyById(Long id) {
		tweetRepo.deleteById(id);
	}
	
	public Tweet update(Tweet tweet) {
		return tweetRepo.save(tweet);
	}

}
