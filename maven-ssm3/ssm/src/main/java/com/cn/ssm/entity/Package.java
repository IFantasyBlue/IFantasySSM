package com.cn.ssm.entity;

import java.io.Serializable;

public class Package implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package.package_ID
     *
     * @mbggenerated
     */
    private Integer packageId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package.goods_ID
     *
     * @mbggenerated
     */
    private Integer goodsId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package.package_num
     *
     * @mbggenerated
     */
    private Integer packageNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table package
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package.package_ID
     *
     * @return the value of package.package_ID
     *
     * @mbggenerated
     */
    public Integer getPackageId() {
        return packageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package.package_ID
     *
     * @param packageId the value for package.package_ID
     *
     * @mbggenerated
     */
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package.goods_ID
     *
     * @return the value of package.goods_ID
     *
     * @mbggenerated
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package.goods_ID
     *
     * @param goodsId the value for package.goods_ID
     *
     * @mbggenerated
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package.package_num
     *
     * @return the value of package.package_num
     *
     * @mbggenerated
     */
    public Integer getPackageNum() {
        return packageNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package.package_num
     *
     * @param packageNum the value for package.package_num
     *
     * @mbggenerated
     */
    public void setPackageNum(Integer packageNum) {
        this.packageNum = packageNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Package other = (Package) that;
        return (this.getPackageId() == null ? other.getPackageId() == null : this.getPackageId().equals(other.getPackageId()))
            && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getPackageNum() == null ? other.getPackageNum() == null : this.getPackageNum().equals(other.getPackageNum()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPackageId() == null) ? 0 : getPackageId().hashCode());
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getPackageNum() == null) ? 0 : getPackageNum().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", packageId=").append(packageId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", packageNum=").append(packageNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}