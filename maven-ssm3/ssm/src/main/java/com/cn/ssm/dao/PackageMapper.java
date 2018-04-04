package com.cn.ssm.dao;

import java.util.List;

import com.cn.ssm.entity.Package;

public interface PackageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer packageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    int insert(Package record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    int insertSelective(Package record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    Package selectByPrimaryKey(Integer packageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Package record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Package record);
    
    //自定义功能方法
    
    //注解方式实现--需要mybatis-config.xml文件配置，不知道怎么做
   //@Select("select * from package where package_num= #{packageNum} ") 
   // public List<Package>  select_by_package_num(int packageNum);
    
  //xml配置方式
   public List<Package>  selectByPackage_num(int packageNum);
}