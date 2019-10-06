package com.android.location.controller.back;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.android.location.pojo.TbUserinfo;
import com.android.location.response.ResponseCode;
import com.android.location.response.ServiceResponse;
import com.android.location.service.UserService;
import com.android.location.utils.HttpServletRequestUtil;
import com.android.location.utils.MD5Util;
import com.android.location.utils.NameAndPasswdUtil;
import com.android.location.utils.fileUtils.FileUploadUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	private ServiceResponse<String> userLogin(HttpServletRequest request) {
			
		String username = HttpServletRequestUtil.getString(request, "username");
		String password = HttpServletRequestUtil.getString(request, "password");
		if(username == null || password == null) {
			return ServiceResponse.createByErrorMessage("用户名或密码不能为空");
		}
		//加密处理
		password = MD5Util.getMD5Code(password);

		return userService.login(username, password);
	}
	
	@RequestMapping("/register")
	private ServiceResponse<String> userRegister(HttpServletRequest request) {
		
		String username = HttpServletRequestUtil.getString(request, "username");
		String password = HttpServletRequestUtil.getString(request, "password");
		if(username == null || password == null) {
			return ServiceResponse.createByErrorMessage("用户名或密码不能为空");
		}
		//验证密码格式
		ServiceResponse<String> response = NameAndPasswdUtil.checkNameAndPasswd(username, password);
		if(response.getStatus() == ResponseCode.ERROR.getCode()) {
			return response;
		}
		//加密密码
		password = MD5Util.getMD5Code(password);
		
		return userService.register(username, password);
		
	}
	
	@RequestMapping("/editinfo")
	private ServiceResponse<String> editUserInfo(HttpServletRequest request) {
		String token = HttpServletRequestUtil.getHeader(request, "token");
		if(token == null) {
			return ServiceResponse.createBySuccessMessage("token 不能为空");
		}
		String username = HttpServletRequestUtil.getString(request, "username");
		//password要单独修改
//		String password = HttpServletRequestUtil.getString(request, "password");
		String realname = HttpServletRequestUtil.getString(request, "realname");
		boolean isteacher = HttpServletRequestUtil.getBoolean(request, "isteacher");
		String phoneNumber = HttpServletRequestUtil.getString(request, "phoneNumber");
		if(username == null && phoneNumber == null && realname == null) {
			return ServiceResponse.createBySuccessMessage("修改信息不能都为空");
		}
		
		TbUserinfo tbUserinfo = new TbUserinfo();
		tbUserinfo.setUsername(username);
//		tbUserinfo.setPassword(password);
		tbUserinfo.setRealname(realname);
		tbUserinfo.setIsteacher(isteacher);
		tbUserinfo.setPhoneNumber(phoneNumber);
		tbUserinfo.setToken(token);
		
		return userService.editUserInfo(tbUserinfo);
	} 
	
	@RequestMapping("/editheadpic")
	private ServiceResponse<String> editHeadPic(HttpServletRequest request) { 
		
		String token = HttpServletRequestUtil.getHeader(request, "token");
		if(token == null) {
			return ServiceResponse.createBySuccessMessage("token 不能为空");
		}
		//修改头像
		File file = null;
		try {
			file = FileUploadUtil.transformConMultipartFileToFile(request);
		} catch (RuntimeException e) {
			return ServiceResponse.createByErrorMessage("未选择文件");
		}
		if(file == null) {
			return ServiceResponse.createByErrorMessage("解析失败");
		}
		return userService.editUserHeadPic(token, file);
		
	}

}
