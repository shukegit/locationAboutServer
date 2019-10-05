package com.android.location.service;

import java.util.List;

import com.android.location.dto.GroupMember;
import com.android.location.response.ServiceResponse;

public interface SigninService {

	/**
	 * ����ǩ��(���Ⱥ���ǩ����ǩ����ǩ��)
	 * @param token����˵�token
	 * @param tbSignin
	 * @return �᷵����ǩ��״̬
	 * 
	 * ��Ҫ�жϴ����ǲ������ڶ�Ӧ��group
	 */
	ServiceResponse<String> createSigninByToken(String token);
	
	
	
	/**
	 * ��ȡǩ����Ա��ǩ����Ϣ
	 * @param token
	 * @param groupId
	 * @return
	 */
	ServiceResponse<List<GroupMember>> getSigninInfo(String token, int groupId);
	
	
}
