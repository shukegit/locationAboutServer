package com.android.location.service;

import java.util.List;

import com.android.location.dto.GroupMember;
import com.android.location.response.ServiceResponse;

public interface SigninService {

	/**
	 * 发起签到(点击群里的签到标签进入签到)
	 * @param token点击人的token
	 * @param tbSignin
	 * @return 会返回其签到状态
	 * 
	 * 需要判断此人是不是属于对应的group
	 */
	ServiceResponse<String> createSigninByToken(String token);
	
	
	
	/**
	 * 获取签到人员的签到信息
	 * @param token
	 * @param groupId
	 * @return
	 */
	ServiceResponse<List<GroupMember>> getSigninInfo(String token, int groupId);
	
	
}
