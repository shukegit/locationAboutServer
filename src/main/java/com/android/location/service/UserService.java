package com.android.location.service;

import java.io.File;
import java.util.List;

import com.android.location.pojo.TbUserinfo;
import com.android.location.response.ServiceResponse;

public interface UserService {
	
	ServiceResponse<String> login(String username, String password);
	
	ServiceResponse<String> register(String username, String password);
	
	ServiceResponse<String> editUserInfo(TbUserinfo tbUserinfo);
	
	ServiceResponse<String> editUserHeadPic(String token, File file);
	
	ServiceResponse<List<TbUserinfo>> getUserInfo(String token);
	
	ServiceResponse<List<TbUserinfo>> getUsersInfo();
	
	/**
	 * 通过短信验证码找回密码
	 * @param token
	 * @return
	 */
	ServiceResponse<String> findPasswordByphonenumber(String token);
	


}
