package entity;

import java.io.Serializable;

public class TeamMembers implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teamMembers.user_id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teamMembers.player_id
     *
     * @mbggenerated
     */
    private Integer playerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teamMembers.status
     *
     * @mbggenerated
     */
    private Boolean status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teamMembers.position
     *
     * @mbggenerated
     */
    private String position;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table teamMembers
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teamMembers.user_id
     *
     * @return the value of teamMembers.user_id
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teamMembers.user_id
     *
     * @param userId the value for teamMembers.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teamMembers.player_id
     *
     * @return the value of teamMembers.player_id
     *
     * @mbggenerated
     */
    public Integer getPlayerId() {
        return playerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teamMembers.player_id
     *
     * @param playerId the value for teamMembers.player_id
     *
     * @mbggenerated
     */
    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teamMembers.status
     *
     * @return the value of teamMembers.status
     *
     * @mbggenerated
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teamMembers.status
     *
     * @param status the value for teamMembers.status
     *
     * @mbggenerated
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teamMembers.position
     *
     * @return the value of teamMembers.position
     *
     * @mbggenerated
     */
    public String getPosition() {
        return position;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teamMembers.position
     *
     * @param position the value for teamMembers.position
     *
     * @mbggenerated
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teamMembers
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
        TeamMembers other = (TeamMembers) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPlayerId() == null ? other.getPlayerId() == null : this.getPlayerId().equals(other.getPlayerId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teamMembers
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getPlayerId() == null) ? 0 : getPlayerId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teamMembers
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", playerId=").append(playerId);
        sb.append(", status=").append(status);
        sb.append(", position=").append(position);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}