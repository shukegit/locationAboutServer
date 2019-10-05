package com.android.location.websocket.utils;


import java.util.Map;
 
import javax.servlet.http.HttpSession;
 
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
 
/**
 * Created by zhangwenchao on 2017/11/20.
 * WebSocket拦截器----握手之前将登陆用户信息从session设置到WebSocketSession
 *
 */
public class SpringWebSocketHandlerInterceptor extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        System.out.println("Before Handshake");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            System.out.println("转换后的session : " + session);
            if (session != null) {
            	System.out.println("session赋值");
                //使用token区分WebSocketHandler，以便定向发送消息
                String token = (String) session.getAttribute("token");
                System.out.println("token:" + token);
//                String username = (String) session.getAttribute("username");
                if (token!=null) {
                    attributes.put("token",token);
//                    attributes.put("username",username);
                }

            }
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);

    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }

}
