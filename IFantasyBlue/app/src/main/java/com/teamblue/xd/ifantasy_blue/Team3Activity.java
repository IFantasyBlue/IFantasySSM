package com.teamblue.xd.ifantasy_blue;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entity.MembersVO;
import entity.PlayerDao2;
import entity.PlayerDao3;
import entity.TeamMembers;

public class Team3Activity extends AppCompatActivity {

    private Intent intent;
    private Button bt1;
    private Button bt2;
    private TextView tv0;
    private TextView tv4;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private TextView tv10;
    private TextView tv11;
    private TextView tv12;
    private TextView tv13;
    private TextView tv14;
    private TextView tv15;
    private TextView tv16;
    private TextView tv17;
    private TextView tv18;
    private TextView tv19;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team3);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        tv0 = findViewById(R.id.tv0);
        tv4 = findViewById(R.id.tv4);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);
        tv9 = findViewById(R.id.tv9);
        tv10 = findViewById(R.id.tv10);
        tv11 = findViewById(R.id.tv11);
        tv12 = findViewById(R.id.tv12);
        tv13 = findViewById(R.id.tv13);
        tv14 = findViewById(R.id.tv14);
        tv15 = findViewById(R.id.tv15);
        tv16 = findViewById(R.id.tv16);
        tv17 = findViewById(R.id.tv17);
        tv18 = findViewById(R.id.tv18);
        tv19 = findViewById(R.id.tv19);
        intent = getIntent();
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();
        tv6.setText(bundle.getInt("time")+"");
        tv7.setText(bundle.getDouble("point")+"");
        tv8.setText(bundle.getDouble("rebound")+"");
        tv9.setText(bundle.getDouble("assist")+"");
        tv10.setText(bundle.getDouble("steal")+"");
        tv11.setText(bundle.getDouble("block")+"");
        tv12.setText(bundle.getDouble("turnover")+"");
        tv13.setText(bundle.getDouble("shot")+"");

        tv14.setText(bundle.getDouble("threeshot")+"");
        tv15.setText(bundle.getDouble("threerate")+"");
        tv16.setText(bundle.getDouble("freeshot")+"");
        tv17.setText(bundle.getDouble("freerate")+"");
        tv18.setText(bundle.getDouble("offrtg")+"");
        tv19.setText(bundle.getDouble("defrtg")+"");
        final int user_id = bundle.getInt("user_id");
        final int player_id = bundle.getInt("player_id");
        bt1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                fire(user_id,player_id);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                replace(user_id,player_id);
            }
        });
        tv0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                back(user_id,player_id);
            }
        });
        tv4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                toStats(user_id,player_id);
            }
        });
    }

    public void toStats(final int user_id, final int player_id){
        new Thread(){
            public void run (){
                PlayerDao playerDao = new PlayerDao();
                JSONObject json = new JSONObject();


                try {
                    //使用通信类PlayerDao传数据给后端获得返回的json对象
                    json = playerDao.teamInit(user_id, player_id,"ssm/team/playerShow.json");
                    //进行页面跳转
                    Intent intent = new Intent(Team3Activity.this,Team2Activity.class);
                    //新建一个bundle对象并往里面放数据传给下一个页面
                    Bundle bundle = new Bundle();
                    bundle.putString("name",json.getString("name"));
                    bundle.putString("team",json.getString("team"));
                    bundle.putString("number",json.getString("number"));
                    bundle.putString("position",json.getString("position"));
                    bundle.putInt("salary",json.getInt("salary"));
                    bundle.putString("birth",json.getString("birth"));
                    bundle.putString("nation",json.getString("nation"));
                    bundle.putDouble("height",json.getDouble("height"));
                    bundle.putDouble("weight",json.getDouble("weight"));
                    //bundle.putDouble("standTall",json.getDouble("standTall"));
                    //bundle.putDouble("arms",json.getDouble("arms"));
                    bundle.putString("draft",json.getString("draft"));
                    bundle.putString("contract",json.getString("contract"));
                    bundle.putInt("player_id",player_id);
                    bundle.putInt("user_id",user_id);
                    Log.i("tag",json.toString());
                    Log.i("tag","abc");
                    //把bundle放入intent
                    intent.putExtras(bundle);
                    //开始页面跳转
                    Team3Activity.this.startActivity(intent);

                }catch(Exception e){
                    e.toString();
                }
            }
        }.start();
    }
    public void back(final int user_id, final int player_id){
        new Thread(){
            public void run (){
                PlayerDao playerDao = new PlayerDao();
                JSONObject json = new JSONObject();

                try {
                    //使用通信类PlayerDao传数据给后端获得返回的json对象
                    json = playerDao.teamInit(user_id, player_id,"ssm/team/teamShow.json");
                    intent = new Intent(Team3Activity.this,TeamActivity.class);
                    JSONArray jsonArray = json.getJSONArray("list");
                    Log.i("tag","aaa"+jsonArray.getJSONObject(0));
                    List<MembersVO> list = new ArrayList<MembersVO>();
                    intent = new Intent(Team3Activity.this,TeamActivity.class);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        MembersVO membersVO = new MembersVO();
                        membersVO.setName(jsonObject.getString("name"));

                        JSONObject json2 = (JSONObject) jsonObject.get("teamMembers");

                        TeamMembers teamMembers = new TeamMembers();
                        teamMembers.setPlayerId(json2.getInt("playerId"));
                        teamMembers.setPosition(json2.getString("position"));
                        teamMembers.setStatus(json2.getBoolean("status"));
                        teamMembers.setUserId(json2.getInt("userId"));
                        membersVO.setTeamMembers(teamMembers);
                        list.add(membersVO);

                        Log.i("qqq",i+"");
                    }

                    Bundle bundle = new Bundle();
                    bundle.putInt("money",json.getInt("money"));
                    bundle.putInt("power",json.getInt("power"));
                    bundle.putString("username",json.getString("username"));
                    intent.putExtra("list", (Serializable) list);
                    intent.putExtras(bundle);
                    Log.i("tag",json.toString());
                    Log.i("tag","abc");
                    //把bundle放入intent
                    startActivity(intent);

                }catch(Exception e){
                    e.toString();
                }
            }
        }.start();
    }
    public void fire(final int user_id, final int player_id){

        AlertDialog dialog = new AlertDialog.Builder(Team3Activity.this)
                .setIcon(R.mipmap.ic_launcher)//设置标题的图片
                .setTitle("球员解雇")//设置对话框的标题
                .setMessage("确定解雇该球员吗")//设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread() {
                            public void run() {
                                PlayerDao playerDao = new PlayerDao();
                                Log.i("tag", "sure");
                                JSONObject json = new JSONObject();
                                try {
                                    json = playerDao.teamInit(user_id, player_id, "ssm/team/playerFire.json");
                                    int status = json.getInt("status");
                                    Log.i("tag", status + "");
                                    if (status == 1) {
                                        Intent intent = new Intent(Team3Activity.this, TeamActivity.class);

                                        Toast.makeText(Team3Activity.this, "解雇成功", Toast.LENGTH_SHORT).show();
                                        json = playerDao.teamInit(user_id, player_id,"ssm/team/teamShow.json");
                                        intent = new Intent(Team3Activity.this,TeamActivity.class);
                                        JSONArray jsonArray = json.getJSONArray("list");
                                        Log.i("tag","aaa"+jsonArray.getJSONObject(0));
                                        List<MembersVO> list = new ArrayList<MembersVO>();
                                        intent = new Intent(Team3Activity.this,TeamActivity.class);
                                        for(int i=0;i<jsonArray.length();i++){
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            MembersVO membersVO = new MembersVO();
                                            membersVO.setName(jsonObject.getString("name"));

                                            JSONObject json2 = (JSONObject) jsonObject.get("teamMembers");

                                            TeamMembers teamMembers = new TeamMembers();
                                            teamMembers.setPlayerId(json2.getInt("playerId"));
                                            teamMembers.setPosition(json2.getString("position"));
                                            teamMembers.setStatus(json2.getBoolean("status"));
                                            teamMembers.setUserId(json2.getInt("userId"));
                                            membersVO.setTeamMembers(teamMembers);
                                            list.add(membersVO);

                                            Log.i("qqq",i+"");
                                        }
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("money",json.getInt("money"));
                                        bundle.putInt("power",json.getInt("power"));
                                        bundle.putString("username",json.getString("username"));
                                        intent.putExtra("list", (Serializable) list);
                                        intent.putExtras(bundle);


                                        startActivity(intent);
                                    } else {
                                        Log.i("tag", "succ");
                                        Toast.makeText(Team3Activity.this, "球员正在首发位置上，不能解雇", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (Exception e) {
                                    e.toString();
                                }
                            }
                        }.start();
                    }
                }).create();
        dialog.show();
    }

    public void replace(final int user_id, final int player_in_id){
        final String items[] = {"C", "PF", "SF", "SG","PG"};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)//设置标题的图片
                .setTitle("单选列表对话框")//设置对话框的标题
                .setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Team3Activity.this, items[which], Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        new Thread() {
                            public void run() {
                                if(items[which].equals("C")){
                                    PlayerDao2 playerDao2 = new PlayerDao2();
                                    PlayerDao3 playerDao3 = new PlayerDao3();
                                    JSONObject json = new JSONObject();
                                    try{
                                        json = playerDao2.teamInit(user_id,"C","ssm/team/findPlayer.json");
                                        int player_id = json.getInt("id");
                                        json = playerDao3.teamInit(user_id,player_in_id,player_id,"ssm/team/findPlayer.json");
                                        if(json.getInt("ststus") == 1){
                                            Toast.makeText(Team3Activity.this, "替换成功", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(Team3Activity.this, "替换失败", Toast.LENGTH_SHORT).show();
                                        }
                                    }catch (Exception e){
                                        e.toString();
                                    }
                                }else if(items[which].equals("PF")){
                                    PlayerDao2 playerDao2 = new PlayerDao2();
                                    PlayerDao3 playerDao3 = new PlayerDao3();
                                    JSONObject json = new JSONObject();
                                    try{
                                        json = playerDao2.teamInit(user_id,"PF","ssm/team/findPlayer.json");
                                        int player_id = json.getInt("id");
                                        json = playerDao3.teamInit(user_id,player_in_id,player_id,"ssm/team/findPlayer.json");
                                        if(json.getInt("ststus") == 1){
                                            Toast.makeText(Team3Activity.this, "替换成功", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(Team3Activity.this, "替换失败", Toast.LENGTH_SHORT).show();
                                        }
                                    }catch (Exception e){
                                        e.toString();
                                    }
                                }else if(items[which].equals("SF")){
                                    PlayerDao2 playerDao2 = new PlayerDao2();
                                    PlayerDao3 playerDao3 = new PlayerDao3();
                                    JSONObject json = new JSONObject();
                                    try{
                                        json = playerDao2.teamInit(user_id,"SF","ssm/team/findPlayer.json");
                                        int player_id = json.getInt("id");
                                        json = playerDao3.teamInit(user_id,player_in_id,player_id,"ssm/team/findPlayer.json");
                                        if(json.getInt("ststus") == 1){
                                            Toast.makeText(Team3Activity.this, "替换成功", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(Team3Activity.this, "替换失败", Toast.LENGTH_SHORT).show();
                                        }
                                    }catch (Exception e){
                                        e.toString();
                                    }
                                }else if(items[which].equals("SG")){
                                    PlayerDao2 playerDao2 = new PlayerDao2();
                                    PlayerDao3 playerDao3 = new PlayerDao3();
                                    JSONObject json = new JSONObject();
                                    try{
                                        json = playerDao2.teamInit(user_id,"SG","ssm/team/findPlayer.json");
                                        int player_id = json.getInt("id");
                                        json = playerDao3.teamInit(user_id,player_in_id,player_id,"ssm/team/findPlayer.json");
                                        if(json.getInt("ststus") == 1){
                                            Toast.makeText(Team3Activity.this, "替换成功", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(Team3Activity.this, "替换失败", Toast.LENGTH_SHORT).show();
                                        }
                                    }catch (Exception e){
                                        e.toString();
                                    }
                                }else{
                                    PlayerDao2 playerDao2 = new PlayerDao2();
                                    PlayerDao3 playerDao3 = new PlayerDao3();
                                    JSONObject json = new JSONObject();
                                    try{
                                        json = playerDao2.teamInit(user_id,"PG","ssm/team/findPlayer.json");
                                        int player_id = json.getInt("id");
                                        json = playerDao3.teamInit(user_id,player_in_id,player_id,"ssm/team/findPlayer.json");
                                        if(json.getInt("ststus") == 1){
                                            Toast.makeText(Team3Activity.this, "替换成功", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(Team3Activity.this, "替换失败", Toast.LENGTH_SHORT).show();
                                        }
                                    }catch (Exception e){
                                        e.toString();
                                    }
                                }
                            }
                        }.start();
                    }
                }).create();
        dialog.show();
    }
}
