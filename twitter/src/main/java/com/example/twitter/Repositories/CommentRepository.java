package com.example.twitter.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.twitter.Models.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{
	public List<Comment> findAll();
}
