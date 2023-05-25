package com.example.twitter.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.twitter.Models.Comment;
import com.example.twitter.Repositories.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	
	public Comment save(Comment comment) {
		return commentRepo.save(comment);
	}
	
	public void destroyById(Long id) {
		commentRepo.deleteById(id);
	}

}
