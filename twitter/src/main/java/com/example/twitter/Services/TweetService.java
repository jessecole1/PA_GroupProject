package com.example.twitter.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.twitter.Models.Tweet;
import com.example.twitter.Repositories.TweetRepository;

@Service
public class TweetService {
	
	@Autowired
	private TweetRepository tweetRepo;
	
	public Tweet save(Tweet newTweet) {
		return tweetRepo.save(newTweet);
	}
	
	public List<Tweet> findAll() {
		return tweetRepo.findAll();
	}

}
