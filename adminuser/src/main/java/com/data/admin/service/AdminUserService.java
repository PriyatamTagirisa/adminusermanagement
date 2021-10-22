package com.data.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import com.data.admin.bean.Post;
import com.data.admin.bean.User;
import com.data.admin.bean.UserDetails;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminUserService {
	@Autowired
	private RestTemplate restTemplate;

	@Value("${USER_API}")
	private String userApi;

	@Value("${POST_API}")
	private String postApi;

	public List<UserDetails> getUserDetails() {

		log.info("AdminUserService---Entered method{} ---","getUserDetails()");
		log.info("---Retrieving User details from User_API--- ");
		List<User> userList =new ArrayList<>();
		List<Post> postList =new ArrayList<>();
		try {
			ResponseEntity<List<User>> userResponse = restTemplate.exchange(userApi, HttpMethod.GET,null, new ParameterizedTypeReference<List<User>>() {
			});
			userList = userResponse.getBody();
		}
		catch (HttpClientErrorException e) {
			log.warn("An HTTP Error Occured connecting to User_API.", e);
		}
		log.info("---Retrieving Post details from POST_API--- ");
		try {
			ResponseEntity<List<Post>> postResponse = restTemplate.exchange(postApi, HttpMethod.GET,null, new ParameterizedTypeReference<List<Post>>() {
			});
			postList = postResponse.getBody();

		}
		catch (HttpClientErrorException e) {
			log.warn("An HTTP Error Occured connecting to POST_API.", e);
		}
		log.info("---Validating API Data---");
		List<UserDetails> userDetailsList=new ArrayList<>();
		if(!userList.isEmpty()) {
			for(User user:userList) 
				if(!postList.isEmpty()) {
					for(Post post:postList) 
						if(post.getId()==user.getId()) {
							UserDetails userDetails=new UserDetails();
							userDetails.setPost(post);
							userDetails.setUser(user);
							userDetailsList.add(userDetails);
							break;        			
						}
				}
				else {
					UserDetails userDetails=new UserDetails();
					userDetails.setUser(user);
					userDetailsList.add(userDetails);
				}
		}
		else {
			if(!postList.isEmpty()) {
				postList.forEach(post->{
					UserDetails userDetails=new UserDetails();
					userDetails.setPost(post);
					userDetailsList.add(userDetails);
				});

			}
		}
		return userDetailsList;
	}

}
