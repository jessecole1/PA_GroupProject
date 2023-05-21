package com.example.twitter.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.twitter.Models.Tweet;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, Long> {

	List<Tweet> findAll();
}
