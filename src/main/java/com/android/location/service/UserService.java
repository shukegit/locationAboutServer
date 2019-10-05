package com.android.location.service;

import java.util.List;

import com.android.location.pojo.TbUserinfo;
import com.android.location.response.ServiceResponse;

public interface UserService {
	
	ServiceResponse<String> login(String username, String password);
	
	ServiceResponse<String> register(String username, String password);
	
	ServiceResponse<String> editUserInfo(String token);
	
	ServiceResponse<List<TbUserinfo>> getUSerInfo(String token);
	
	ServiceResponse<List<TbUserinfo>> getUSersInfo();
	
	/**
	 * 通过短信验证码找回密码
	 * @param token
	 * @return
	 */
	ServiceResponse<String> findPasswordByphonenumber(String token);
	


}
