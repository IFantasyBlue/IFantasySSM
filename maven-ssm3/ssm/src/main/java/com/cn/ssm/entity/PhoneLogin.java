package com.cn.ssm.entity;

import java.io.Serializable;

public class PhoneLogin implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column phoneLogin.phone
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column phoneLogin.aKey
     *
     * @mbggenerated
     */
    private Integer akey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table phoneLogin
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column phoneLogin.phone
     *
     * @return the value of phoneLogin.phone
     *
     * @mbggenerated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column phoneLogin.phone
     *
     * @param phone the value for phoneLogin.phone
     *
     * @mbggenerated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column phoneLogin.aKey
     *
     * @return the value of phoneLogin.aKey
     *
     * @mbggenerated
     */
    public Integer getAkey() {
        return akey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column phoneLogin.aKey
     *
     * @param akey the value for phoneLogin.aKey
     *
     * @mbggenerated
     */
    public void setAkey(Integer akey) {
        this.akey = akey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table phoneLogin
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
        PhoneLogin other = (PhoneLogin) that;
        return (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getAkey() == null ? other.getAkey() == null : this.getAkey().equals(other.getAkey()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table phoneLogin
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getAkey() == null) ? 0 : getAkey().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table phoneLogin
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", phone=").append(phone);
        sb.append(", akey=").append(akey);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}