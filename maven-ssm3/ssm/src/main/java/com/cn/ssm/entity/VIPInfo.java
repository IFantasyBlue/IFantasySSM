package com.cn.ssm.entity;

import java.io.Serializable;

public class VIPInfo implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column VIP_info.VIP_level
     *
     * @mbggenerated
     */
    private Integer vipLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column VIP_info.gold_multiple
     *
     * @mbggenerated
     */
    private Double goldMultiple;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column VIP_info.win_multiple
     *
     * @mbggenerated
     */
    private Double winMultiple;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table VIP_info
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column VIP_info.VIP_level
     *
     * @return the value of VIP_info.VIP_level
     *
     * @mbggenerated
     */
    public Integer getVipLevel() {
        return vipLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column VIP_info.VIP_level
     *
     * @param vipLevel the value for VIP_info.VIP_level
     *
     * @mbggenerated
     */
    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column VIP_info.gold_multiple
     *
     * @return the value of VIP_info.gold_multiple
     *
     * @mbggenerated
     */
    public Double getGoldMultiple() {
        return goldMultiple;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column VIP_info.gold_multiple
     *
     * @param goldMultiple the value for VIP_info.gold_multiple
     *
     * @mbggenerated
     */
    public void setGoldMultiple(Double goldMultiple) {
        this.goldMultiple = goldMultiple;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column VIP_info.win_multiple
     *
     * @return the value of VIP_info.win_multiple
     *
     * @mbggenerated
     */
    public Double getWinMultiple() {
        return winMultiple;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column VIP_info.win_multiple
     *
     * @param winMultiple the value for VIP_info.win_multiple
     *
     * @mbggenerated
     */
    public void setWinMultiple(Double winMultiple) {
        this.winMultiple = winMultiple;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VIP_info
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
        VIPInfo other = (VIPInfo) that;
        return (this.getVipLevel() == null ? other.getVipLevel() == null : this.getVipLevel().equals(other.getVipLevel()))
            && (this.getGoldMultiple() == null ? other.getGoldMultiple() == null : this.getGoldMultiple().equals(other.getGoldMultiple()))
            && (this.getWinMultiple() == null ? other.getWinMultiple() == null : this.getWinMultiple().equals(other.getWinMultiple()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VIP_info
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getVipLevel() == null) ? 0 : getVipLevel().hashCode());
        result = prime * result + ((getGoldMultiple() == null) ? 0 : getGoldMultiple().hashCode());
        result = prime * result + ((getWinMultiple() == null) ? 0 : getWinMultiple().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VIP_info
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", vipLevel=").append(vipLevel);
        sb.append(", goldMultiple=").append(goldMultiple);
        sb.append(", winMultiple=").append(winMultiple);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}