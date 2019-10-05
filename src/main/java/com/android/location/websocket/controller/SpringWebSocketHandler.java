package com.android.location.websocket.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alibaba.fastjson.JSON;

public class SpringWebSocketHandler extends TextWebSocketHandler {
    

    /**
     * 以用户的姓名为key，WebSocket为对象保存起来
     */
    private static final Map<String, WebSocketSession> users;  //Map来存储WebSocketSession，key用USER_ID 即在线用户列表
 
    /**
     * 存储用户token信息返回给前端
     */
    private static List<String> tokenList;
    
    /**
     * 用户token
     */
    private static String token;
    
    /**
     * 用户name
     */
    @SuppressWarnings("unused")
	private static String username;
 
 
    static {
        users =  new ConcurrentHashMap<String, WebSocketSession>();
        tokenList = new ArrayList<String>();
    }
 
    public SpringWebSocketHandler() {}
 
    /**
     * 连接成功时候，会触发页面上onopen方法
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
 
        System.out.println("成功建立websocket连接!");
        token = (String) session.getAttributes().get("token");
        System.out.println("token-->" + token);
        users.put(token,session);
        tokenList.add(token);
        System.out.println("当前线上用户数量:" + users.size());
 
        //这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户
        sendUserMsgToClient(session);
    }
    
    private void sendUserMsgToClient(WebSocketSession session) throws IOException {
    	 String json = JSON.toJSON(tokenList).toString();
         TextMessage returnMessage = new TextMessage(json);
         session.sendMessage(returnMessage);
    }
    
    /**
     * 关闭连接时触发
     */
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String token= (String) session.getAttributes().get("token");
        System.out.println("用户"+token+"已退出！");
        users.remove(token);
        tokenList.remove(token);
        System.out.println("剩余在线用户" + users.size());
//        sendUserMsgToClient(session);
    }
 
    /**
     * js调用websocket.send时候，会调用该方法
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
 
        super.handleTextMessage(session, message);
 
        /**
                * 收到消息，自定义处理机制，实现业务
         */
        System.out.println("服务器收到消息："+message + " " + message.getPayload());
 
        if(message.getPayload().startsWith("#anyone#")){ //单发某人
        	System.out.println(1);
        	sendMessageToUser(token, new TextMessage("服务器单发：" +message.getPayload()));
        }else if(message.getPayload().startsWith("#everyone#")){
        	System.out.println(2);
            sendMessageToUsers(new TextMessage("服务器群发：" +message.getPayload()));
        }else {
        	System.out.println(3);
        }
 
    }
 
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        System.out.println("传输出现异常，关闭websocket连接... ");
        String token= (String) session.getAttributes().get("token");
        users.remove(token);
        tokenList.remove(token);
        sendUserMsgToClient(session);
    }
 
    public boolean supportsPartialMessages() {
 
        return false;
    }
 
 
    /**
     * 给某个用户发送消息
     *
     * @param userId
     * @param message
     */
    public void sendMessageToUser(String token, TextMessage message) {
    	System.out.println("服务器单发：" + token);
        for (String id : users.keySet()) {
            if (id.equals(token)) {
                try {
                    if (users.get(id).isOpen()) {
                        users.get(id).sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
 
    /**
        * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
    	System.out.println("服务器群发：");
        for (String token : users.keySet()) {
            try {
                if (users.get(token).isOpen()) {
                    users.get(token).sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
