package com.teamblue.xd.ifantasy_blue;

/**
 * Created by xd on 18-4-10.
 */

public class ServerConfiguration {
    public static final String IP = "10.170.32.18";//服务器地址
    public static final int PORT = 8080;//要根据应用服务器tomcat的端口改变
    public static final String USERSENDAKEYSERVICEURI = "maven-ssm/user/sendAKey.json";
    public static final String USERLOGINSERVICEURI = "maven-ssm/user/userLogin.json";
    public static final String USERSIGNSERVICEURI = "maven-ssm/user/userSign.json";
    public static final String RANKPOWERTOP20 = "maven-ssm/activity/getRank.json";

/**
 * team_model
 */

    public static final String TACTICSINIT = "ssm/team/playerStatsShow.json";
    public static final String TACTICSEQUIP = "ssm/team/playerShow.json";
}
