package com.android.location.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/wsad")
@ResponseBody
public class PingController {
	
	@RequestMapping("/ping")
	private String ping() {
		return "pong"; 
	}
}
