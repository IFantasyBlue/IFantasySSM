package com.cn.ssm.dao;

import java.util.List;

import com.cn.ssm.entity.DTactics;
import com.cn.ssm.entity.OTactics;

public interface DTacticsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table D_tactics
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table D_tactics
     *
     * @mbggenerated
     */
    int insert(DTactics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table D_tactics
     *
     * @mbggenerated
     */
    int insertSelective(DTactics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table D_tactics
     *
     * @mbggenerated
     */
    DTactics selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table D_tactics
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DTactics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table D_tactics
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DTactics record);
    
    List <DTactics> getAll();
}