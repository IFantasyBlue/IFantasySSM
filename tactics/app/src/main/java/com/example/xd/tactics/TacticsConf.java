package com.example.xd.tactics;

import entity.DTactics;
import entity.OTactics;

/**
 * Created by xd on 18-4-23.
 */

public class TacticsConf {
    public static final String IP = "10.255.1.14";//服务器地址
    public static final int PORT = 8080;//要根据应用服务器tomcat的端口改变
    public static final String TACTICSINIT = "ssm/tactics/initsTactics.json";
    public static final String OTACTICSEQUIP = "ssm/tactics/otacticsEquipped.json";
    public static final String DTACTICSEQUIP = "ssm/tactics/dtacticsEquipped.json";

    public static final OTactics otactics1 = new OTactics(1,"外线投射","借由SF为内外线衔接，倚重PG、SG远投能力的攻击战术");
    public static final OTactics otactics2 = new OTactics(2,"突破分球","以PF突破创造外线空档，倚重SF、SG远投能力的攻击战术");
    public static final OTactics otactics3 = new OTactics(3,"敬请期待","");
    public static final OTactics otactics4 = new OTactics(4,"内线强攻","以SF为策应轴点，攻击重心偏重于PF、C的强硬打法；");
    public static final OTactics otactics5 = new OTactics(5,"双塔战术","依靠两名拥有娴熟的传球能力与篮板意识的大个子球员PF、C来打开进攻局面的战术");
    public static final OTactics otactics6 = new OTactics(6,"敬请期待","");
    public static final OTactics otactics7 = new OTactics(7,"普林斯顿","以C、PF为组织核心，依靠PG对位接球、掩护切入、后门切入等手段创造得分机会的战术");
    public static final OTactics otactics8 = new OTactics(8,"三角进攻","以相互保持15-20英尺的C、PG和SG呈三角顶点，通过快速分球撕开对手防线的打法");
    public static final OTactics otactics9 = new OTactics(9,"敬请期待","");

    public static final OTactics[] otactics = {otactics1, otactics2, otactics3, otactics4, otactics5, otactics6, otactics7, otactics8, otactics9};

    public static final DTactics dtactics1 = new DTactics(1,"外线联防","以针对敌方外线球员SF、SG为主的防守战术，偏向于针对助攻");
    public static final DTactics dtactics2 = new DTactics(2,"外线紧逼","以针对敌方外线球员SF、SG为主的防守战术，偏向于针对得分");
    public static final DTactics dtactics3 = new DTactics(3,"敬请期待","");
    public static final DTactics dtactics4 = new DTactics(4,"内线联防","以针对敌方内线球员PF、PG为主的防守战术，偏向于针对助攻");
    public static final DTactics dtactics5 = new DTactics(5,"内线紧逼","以针对敌方内线球员PF、PG为主的防守战术，偏向于针对得分");
    public static final DTactics dtactics6 = new DTactics(6,"敬请期待","");
    public static final DTactics dtactics7 = new DTactics(7,"全场盯人","对敌方全体球员形成全场压制，但同时也很耗费体力");
    public static final DTactics dtactics8 = new DTactics(8,"敬请期待","");
    public static final DTactics dtactics9 = new DTactics(9,"敬请期待","");

    public static final DTactics[] dtactics = {dtactics1, dtactics2, dtactics3, dtactics4, dtactics5, dtactics6, dtactics7, dtactics8, dtactics9};
}
