package com.android.location.websocket.controller;



import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.android.location.utils.HttpServletRequestUtil;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
/**
 * 
 */
@Controller
@RequestMapping("/websocket")
public class WebSocketController {
 
    @Bean//这个注解会从Spring容器拿出Bean
    public SpringWebSocketHandler infoHandler() {
 
        return new SpringWebSocketHandler();
    }
 
 
    @RequestMapping("/socketlogin")
    public String loginPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "login";
    }
 
 
    @RequestMapping("/socketmain")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "main";
    }
 
    @RequestMapping("/websocket/send")
    @ResponseBody
    public String send(HttpServletRequest request) {
        String username = HttpServletRequestUtil.getString(request, "username");
        infoHandler().sendMessageToUser(username, new TextMessage("你好，测试！！！！"));
        return null;
    }
 
 
    @RequestMapping("/websocket/broad")
    @ResponseBody
    public  String broad() {
        infoHandler().sendMessageToUsers(new TextMessage("发送一条小Broad"));
        System.out.println("群发成功");
        return "broad";
    }
 
}
