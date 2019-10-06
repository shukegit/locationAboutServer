package com.android.location.service.Impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.android.location.dao.TbUserinfoMapper;
import com.android.location.pojo.TbUserinfo;
import com.android.location.response.ServiceResponse;
import com.android.location.service.UserService;
import com.android.location.utils.TokenUtil;
import com.android.location.utils.fileUtils.FileUploadUtil;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private TbUserinfoMapper tbUserinfoMapper;

	public ServiceResponse<String> login(String username, String password) {
		TbUserinfo userinfo = tbUserinfoMapper.selecTbUserInfo(username, password);
		if(userinfo == null) {
			return ServiceResponse.createByErrorMessage("用户名或密码不正确");
		}
		return ServiceResponse.createBySuccessMessage(userinfo.getToken());
	}

	public ServiceResponse<String> register(String username, String password) {
		
		TbUserinfo userinfo = new TbUserinfo();
		userinfo.setUsername(username);
		userinfo.setPassword(password);
		userinfo.setCreatetime(new Date());
		//生成token
		String token = TokenUtil.createToken();
		userinfo.setToken(token);
		int row = tbUserinfoMapper.insert(userinfo);
		if(row == 0) {
			//失败的一种可能是用户名重复，以后有能力要细分
			return ServiceResponse.createBySuccessMessage("注册失败，未知错误");
		}
		return ServiceResponse.createBySuccessMessage(token);
	}

	public ServiceResponse<String> editUserInfo(TbUserinfo tbUserinfo) {
		
		int row = tbUserinfoMapper.updateUserInfo(tbUserinfo);
		if(row < 1) {
			return ServiceResponse.createByErrorMessage("修改用户信息失败");
		}
		return ServiceResponse.createBySuccessMessage("修改信息成功");
	}
	
	/**
	 * 拿到别人的token修改了别人的东西怎么办
	 */
	public ServiceResponse<String> editUserHeadPic(String token, File file) {
		
		TbUserinfo tbUserinfo = tbUserinfoMapper.selecTbUserInfoByToken(token);
		if(tbUserinfo == null) {
			return ServiceResponse.createByErrorMessage("没有该用户");
		}
		
		/**
		 * 保证事务的一致性
		 */
		try {
			String path = null;
			try {
				path = FileUploadUtil.saveFileAndGetDir(file, token);	
			} catch (RuntimeException e) {
				throw new RuntimeException("修改图片失败，未知错误");
			}
			tbUserinfo.setHeadPhotoAddr(path);
			int row = tbUserinfoMapper.updateUserInfo(tbUserinfo);
			if(row < 1) {
				throw new RuntimeException("修改图片失败，数据库错误");
			}
		} catch (RuntimeException e) {
			return ServiceResponse.createByErrorMessage(e.getMessage());
		}
		
		return ServiceResponse.createBySuccessMessage("修改成功");
	}
	
	public ServiceResponse<List<TbUserinfo>> getUserInfo(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public ServiceResponse<List<TbUserinfo>> getUsersInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public ServiceResponse<String> findPasswordByphonenumber(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
