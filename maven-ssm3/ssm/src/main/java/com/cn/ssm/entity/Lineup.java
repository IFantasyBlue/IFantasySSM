package com.cn.ssm.entity;

import java.io.Serializable;

public class Lineup implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lineup.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lineup.C
     *
     * @mbggenerated
     */
    private Integer c;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lineup.PF
     *
     * @mbggenerated
     */
    private Integer pf;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lineup.SF
     *
     * @mbggenerated
     */
    private Integer sf;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lineup.SG
     *
     * @mbggenerated
     */
    private Integer sg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lineup.PG
     *
     * @mbggenerated
     */
    private Integer pg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table lineup
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lineup.ID
     *
     * @return the value of lineup.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lineup.ID
     *
     * @param id the value for lineup.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lineup.C
     *
     * @return the value of lineup.C
     *
     * @mbggenerated
     */
    public Integer getC() {
        return c;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lineup.C
     *
     * @param c the value for lineup.C
     *
     * @mbggenerated
     */
    public void setC(Integer c) {
        this.c = c;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lineup.PF
     *
     * @return the value of lineup.PF
     *
     * @mbggenerated
     */
    public Integer getPf() {
        return pf;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lineup.PF
     *
     * @param pf the value for lineup.PF
     *
     * @mbggenerated
     */
    public void setPf(Integer pf) {
        this.pf = pf;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lineup.SF
     *
     * @return the value of lineup.SF
     *
     * @mbggenerated
     */
    public Integer getSf() {
        return sf;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lineup.SF
     *
     * @param sf the value for lineup.SF
     *
     * @mbggenerated
     */
    public void setSf(Integer sf) {
        this.sf = sf;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lineup.SG
     *
     * @return the value of lineup.SG
     *
     * @mbggenerated
     */
    public Integer getSg() {
        return sg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lineup.SG
     *
     * @param sg the value for lineup.SG
     *
     * @mbggenerated
     */
    public void setSg(Integer sg) {
        this.sg = sg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lineup.PG
     *
     * @return the value of lineup.PG
     *
     * @mbggenerated
     */
    public Integer getPg() {
        return pg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lineup.PG
     *
     * @param pg the value for lineup.PG
     *
     * @mbggenerated
     */
    public void setPg(Integer pg) {
        this.pg = pg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lineup
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
        Lineup other = (Lineup) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getC() == null ? other.getC() == null : this.getC().equals(other.getC()))
            && (this.getPf() == null ? other.getPf() == null : this.getPf().equals(other.getPf()))
            && (this.getSf() == null ? other.getSf() == null : this.getSf().equals(other.getSf()))
            && (this.getSg() == null ? other.getSg() == null : this.getSg().equals(other.getSg()))
            && (this.getPg() == null ? other.getPg() == null : this.getPg().equals(other.getPg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lineup
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getC() == null) ? 0 : getC().hashCode());
        result = prime * result + ((getPf() == null) ? 0 : getPf().hashCode());
        result = prime * result + ((getSf() == null) ? 0 : getSf().hashCode());
        result = prime * result + ((getSg() == null) ? 0 : getSg().hashCode());
        result = prime * result + ((getPg() == null) ? 0 : getPg().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lineup
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", c=").append(c);
        sb.append(", pf=").append(pf);
        sb.append(", sf=").append(sf);
        sb.append(", sg=").append(sg);
        sb.append(", pg=").append(pg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}