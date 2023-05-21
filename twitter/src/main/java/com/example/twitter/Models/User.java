package com.example.twitter.Models;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
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
