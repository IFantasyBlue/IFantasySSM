package com.cn.ssm.dao;

import com.cn.ssm.entity.Players_Stats;

public interface Players_StatsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table players_stats
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table players_stats
     *
     * @mbggenerated
     */
    int insert(Players_Stats record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table players_stats
     *
     * @mbggenerated
     */
    int insertSelective(Players_Stats record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table players_stats
     *
     * @mbggenerated
     */
    Players_Stats selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table players_stats
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Players_Stats record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table players_stats
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Players_Stats record);
}