package com.example.twitter.Models;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Email provided is required")
	@Email(message="Not a valid email")
	private String email;
	
	@NotEmpty(message="Username provided is required")
	@Size(min=3, max=128, message="Username must be at least 3 characters long")
	private String username;
	
	@NotEmpty(message="Password provided is required")
	@Size(min=3, max=128, message="Password must be at least 3 characters long")
	private String password;
	
	@Transient
	@NotEmpty(message="Please confirm your password")
	@Size(min=3, max=128, message="Please confirm your password")
	private String confirm;
	
	@Size(min=3, max=128, message="Bio must be between 3 and 128 characters long")
	private String bio;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Tweet> tweets;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="users_likes",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="tweet_id")
	)
	private List<Tweet> likedTweets;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Comment> comments;
	
//	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
//	private List<Comment> commentNotifications;
	
	@Min(0)
	private Integer notificationNum;
	
//	@ManyToMany(fetch=FetchType.LAZY)
//	@JoinTable(
//			name="user_followed_user",
//			joinColumns=@JoinColumn(name="user_id"),
//			inverseJoinColumns=@JoinColumn(name="followedUser_id")
//	)
//	private List<User> followedUsers;
//	
//	@ManyToMany(fetch=FetchType.LAZY)
//	@JoinTable(
//			name="user_followed_user",
//			joinColumns=@JoinColumn(name="followedUser_id"),
//			inverseJoinColumns=@JoinColumn(name="user_id")
//	)
//	private List<User> followers;
	
	@OneToMany(mappedBy="to", fetch=FetchType.LAZY)
	private List<FollowRelationship> followers;
	
	@OneToMany(mappedBy="from", fetch=FetchType.LAZY)
	private List<FollowRelationship> usersFollowed;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="follower_id")	private List<User> followers;
	
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	public User () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	public List<Tweet> getLikedTweets() {
		return likedTweets;
	}

	public void setLikedTweets(List<Tweet> likedTweets) {
		this.likedTweets = likedTweets;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Integer getNotificationNum() {
		return notificationNum;
	}

	public void setNotificationNum(Integer notificationNum) {
		this.notificationNum = notificationNum;
	}

//	public List<Comment> getCommentNotifications() {
//		return commentNotifications;
//	}
//
//	public void setCommentNotifications(List<Comment> commentNotifications) {
//		this.commentNotifications = commentNotifications;
//	}

//	public List<User> getFollows() {
//		return follows;
//	}
//
//	public void setFollows(List<User> follows) {
//		this.follows = follows;
//	}


	public List<FollowRelationship> getFollowers() {
		return followers;
	}

	public void setFollowers(List<FollowRelationship> followers) {
		this.followers = followers;
	}

	public List<FollowRelationship> getFollowing() {
		return usersFollowed;
	}

	public void setFollowing(List<FollowRelationship> usersFollowed) {
		this.usersFollowed = usersFollowed;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	

}
