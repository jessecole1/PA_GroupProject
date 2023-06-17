package com.example.twitter.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.twitter.Models.FollowRelationship;

@Repository
public interface FollowRelationshipRepository extends CrudRepository<FollowRelationship, Long> {

	
}
