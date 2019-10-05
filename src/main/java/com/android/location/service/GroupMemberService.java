package com.android.location.service;

import java.util.List;

import com.android.location.pojo.TbUserinfo;
import com.android.location.response.ServiceResponse;

public interface GroupMemberService {

	
	/**
	 * 邀请签到群成员
	 * @param hostToken
	 * @param masterTokenList 所有被邀请的群成员token保存在tokenlist里面
	 * @return
	 */
	ServiceResponse<String> inviteGroupMembers(String hostToken, int groupId, List<String> masterTokenList);
	
	/**
	 * 删除签到群成员，可以是部分删除，也可以是完全删除，取决于list里包含的群成员token
	 * @param hostToken
	 * @param masterTokenList
	 * @return
	 */
	ServiceResponse<String> removeGroupMembers(String hostToken, int groupId, List<String> masterTokenList);
	
	/**
	 *通过签到群名称获得所有群成员
	 * @param hostToken
	 * @param groupName
	 * @return
	 */
	ServiceResponse<List<TbUserinfo>> getGroupMembersByGroupName(String hostToken, int groupId);
	

}
