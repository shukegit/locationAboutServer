package com.android.location.websocket.container;

import java.util.ArrayList;
import java.util.List;

import com.android.location.websocket.pojo.UserInfo;

public class OnlineUserContainer {
		
	private static final List<UserInfo> onlineUserList;
	
	static {
		onlineUserList = new ArrayList<UserInfo>();
	}
	
	public static void add(UserInfo userInfo) {
		onlineUserList.add(userInfo);
	}
	
	public static void remove(String token) {
		for(int i = 0; i < onlineUserList.size(); i ++ ) {
			if(token.equals(onlineUserList.get(i).getToken())) {
				onlineUserList.remove(onlineUserList.get(i));
			}
		}
	}

}
