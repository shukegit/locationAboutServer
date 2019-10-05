package com.android.location.controller.back;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.android.location.response.ServiceResponse;
import com.android.location.utils.HttpServletRequestUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/login")
	private ServiceResponse<String> userLogin(HttpServletRequest request) {
			
		String username = HttpServletRequestUtil.getString(request, "username");
		String password = HttpServletRequestUtil.getString(request, "password");
		
		return null;
		
	} 

}
