package com.data.admin.bean;

public class UserDetails {
	private User user;
	private Post post;

	public UserDetails() {

	}

	public UserDetails(User user, Post post) {
		super();
		this.user = user;
		this.post = post;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}


}
