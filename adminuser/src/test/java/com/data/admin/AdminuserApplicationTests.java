package com.data.admin;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.data.admin.bean.Address;
import com.data.admin.bean.Company;
import com.data.admin.bean.Geo;
import com.data.admin.bean.Post;
import com.data.admin.bean.User;
import com.data.admin.bean.UserDetails;
import com.data.admin.controller.AdminUserController;
import com.data.admin.service.AdminUserService;

@SpringBootTest
class AdminuserApplicationTests {
	@InjectMocks
	AdminUserController adminUserController;
	@Mock
	AdminUserService adminUserService;
	
	@Test
	public void testGetUserDetails() {
		User user=new User(1, "Bret", "Sincere@april.biz", new Address("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874", new Geo("-37.3159", "81.1496")), "1-770-736-8031 x56442", "hildegard.org",
				new Company("Romaguera-Crona", "Multi-layered client-server neural-net",  "harness real-time e-markets"));
		Post post= new Post(1, 1,  "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",  "quia et suscipit\nsuscipit recusandae consequuntur expedita");
		UserDetails userDetails=new UserDetails(user, post);
		List<UserDetails> userdetailsList=new ArrayList<>();
		userdetailsList.add(userDetails);
		when(adminUserService.getUserDetails()).thenReturn(userdetailsList);
	}

}
