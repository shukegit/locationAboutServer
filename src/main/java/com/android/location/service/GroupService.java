package com.android.location.service;

import java.util.List;

import com.android.location.dto.GroupMember;
import com.android.location.pojo.TbGroup;
import com.android.location.pojo.TbGroupmember;
import com.android.location.response.ServiceResponse;

public interface GroupService {

	/**
	 * 通过token创建一个签到群
	 * @param token
	 * @return
	 */
	ServiceResponse<String> createGroupByToken(String token);
	
	/**
	 * 获得当前签到人创建的签到群
	 * @param token
	 * @return
	 */
	ServiceResponse<List<TbGroup>> getGroupsByToken(String token);
	
	/**
	 * 通过签到群名称获得当前登录人创建的签到群
	 * @param hostToken
	 * @param groupName
	 * @return
	 */
	ServiceResponse<TbGroup> getGroupByGroupName(String hostToken, String groupName);
	
	/**
	 * 通过签到群名称删除签到群
	 * @param token
	 * @return
	 */
	ServiceResponse<String> removeGroupByGroupName(String hostToken, String groupName);
	
	/**
	 * 删除 群创建人创建的所有签到群
	 * @param token
	 * @return
	 */
	ServiceResponse<String> removeGroupsByToken(String token);
	
	/**
	 * 退出加入的签到群
	 * @param masterToken
	 * @param groupId
	 * @return
	 */
	ServiceResponse<String> logOutGroupByToken(String masterToken, int groupId);
	
	/**
	 * 申请加入签到群
	 * 会将groupMember表中的isCheck字段设置为false(该字段默认为false)
	 * @param masterToken
	 * @param groupId
	 * @return
	 */
	ServiceResponse<String> applyForJoinGroup(String masterToken, String groupId);
	
	/**
	 * 得到所有申请人信息
	 * @param hostToken
	 * @param groupId
	 * @return
	 */
	ServiceResponse<List<GroupMember>> getApplyMembersInfo(String hostToken, int groupId);
	
	/**
	 * 通过或拒绝 申请加群的人员
	 * @param hostToken
	 * @param groupId
	 * @param isCheck   为true表示同意加入
	 * @return
	 */
	ServiceResponse<List<TbGroup>> checkApplyMembersInfo(String hostToken, int groupId, boolean isCheck);
	
	/**
	 * 获取群成员签到记录
	 * @param hostToken
	 * @param groupId
	 * @return
	 */
	ServiceResponse<List<GroupMember>> getGroupMemberSignRecord(String hostToken, int groupId);
	
}
