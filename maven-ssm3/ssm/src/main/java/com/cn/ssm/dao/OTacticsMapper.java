package com.cn.ssm.dao;

import java.util.List;

import com.cn.ssm.entity.OTactics;

public interface OTacticsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table O_tactics
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table O_tactics
     *
     * @mbggenerated
     */
    int insert(OTactics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table O_tactics
     *
     * @mbggenerated
     */
    int insertSelective(OTactics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table O_tactics
     *
     * @mbggenerated
     */
    OTactics selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table O_tactics
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(OTactics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table O_tactics
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(OTactics record);
    
    /**
     * This method was generated by lyf.
     * This method corresponds to the database table O_tactics
     *
     * @mbggenerated
     */
    List <OTactics> getAll();
}