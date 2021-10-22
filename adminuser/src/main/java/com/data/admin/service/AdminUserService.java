package com.data.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import com.data.admin.bean.Post;
import com.data.admin.bean.User;
import com.data.admin.bean.UserDetails;

@Service
public class AdminUserService {
   @Autowired
	private RestTemplate restTemplate;

   @Value("${USER_API}")
   private String userApi;

   @Value("${POST_API}")
   private String postApi;
	public List<UserDetails> getUserDetails() {
		

        ResponseEntity<List<User>> userResponse = restTemplate.exchange(userApi, HttpMethod.GET,null, new ParameterizedTypeReference<List<User>>() {
        });
        List<User> userList = userResponse.getBody();
        
        ResponseEntity<List<Post>> postResponse = restTemplate.exchange(postApi, HttpMethod.GET,null, new ParameterizedTypeReference<List<Post>>() {
        });
        List<Post> postList = postResponse.getBody();
        
        List<UserDetails> userDetailsList=new ArrayList<>();
         for(User user:userList) 
        	 for(Post post:postList) 
        		if(post.getId()==user.getId()) {
        			UserDetails userDetails=new UserDetails();
        			userDetails.setPost(post);
        			userDetails.setUser(user);
        			userDetailsList.add(userDetails);
        			break;        			
        		}
        	 
         
		return userDetailsList;
	}

}
