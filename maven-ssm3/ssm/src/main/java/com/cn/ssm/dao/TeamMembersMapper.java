package com.cn.ssm.dao;

import java.util.List;

import com.cn.ssm.entity.BelongTeam;
import com.cn.ssm.entity.TeamMembers;

public interface TeamMembersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teamMembers
     *
     * @mbggenerated
     */
    int insert(TeamMembers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teamMembers
     *
     * @mbggenerated
     */
    int insertSelective(TeamMembers record);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table belongTeam
     *
     * @mbggenerated
     */
    List<TeamMembers> selectByKey(Integer id);
    TeamMembers selectByKey2(Integer user_id,Integer player_id);
    int updateTeamMembers(Integer user_id,Integer player_id,Boolean status);
    int delTeamMembers(Integer user_id,Integer player_id);
    List<TeamMembers> selectByKey3(int user_id,String position);
}