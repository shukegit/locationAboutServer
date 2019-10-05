package com.android.location.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.android.location.websocket.controller.SpringWebSocketHandler;
import com.android.location.websocket.utils.SpringWebSocketHandlerInterceptor;



@Configuration
@EnableWebSocket
public class SpringWebSocketConfig implements WebSocketConfigurer {
    
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(),"/websocket/socketServer")
                .addInterceptors(new SpringWebSocketHandlerInterceptor()).setAllowedOrigins("*");
        
        registry.addHandler(webSocketHandler(), "/sockjs/socketServer").setAllowedOrigins("http://localhost:8080")
               .addInterceptors(new SpringWebSocketHandlerInterceptor()).withSockJS();
    }
 
    @Bean
    public TextWebSocketHandler webSocketHandler(){
 
        return new SpringWebSocketHandler();
    }

}
