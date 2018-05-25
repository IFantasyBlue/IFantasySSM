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

public class Team2Activity extends AppCompatActivity {

    private Button bt1;
    private Button bt2;
    private TextView tv0;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private Intent intent;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private TextView tv10;
    private TextView tv11;
    private TextView tv12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team2);
        initView();
        //向前一个页面取出intent
        intent = getIntent();
        Bundle bundle = new Bundle();
        //取出前一个页面传过来的数据
        bundle = intent.getExtras();
        //显示道tv1....组件上
        tv1.setText(bundle.getString("name"));
        tv2.setText(bundle.getString("birth"));
        tv3.setText(bundle.getString("nation"));
        tv4.setText(bundle.getString("contract"));
        tv6.setText("球队/"+bundle.getString("team"));
        tv7.setText(bundle.getInt("number"));
        tv8.setText("位置/"+bundle.getString("position"));
        double perEv = bundle.getDouble("perEv");
        int score = (int)Math.floor(100*perEv/27.9);
        tv9.setText("评分/"+score);
        tv10.setText("薪资/"+bundle.getInt("salary")+"万");
        tv11.setText(bundle.getString("contract"));
        tv12.setText("身高/"+bundle.getDouble("height")+"cm"+"体重/"+bundle.getDouble("weight")+"磅"+"站立摸高/"+bundle.getDouble("standTall")+"cm"+"臂展/"+bundle.getDouble("arms")+"cm");
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
        tv5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                redirect(user_id,player_id);
            }
        });
        tv0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                back(user_id,player_id);
            }
        });
    }
    public void initView(){
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        tv0 = findViewById(R.id.tv0);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);
        tv9 = findViewById(R.id.tv9);
        tv10 = findViewById(R.id.tv10);
        tv11 = findViewById(R.id.tv11);
        tv12 = findViewById(R.id.tv12);

    }
    public void redirect(final int user_id, final int player_id){
        new Thread(){
            public void run (){
                PlayerDao playerDao = new PlayerDao();
                JSONObject json = new JSONObject();

                try {

                    json = playerDao.teamInit(user_id, player_id,"ssm/team/playerStatsShow.json");
                    Intent intent = new Intent(Team2Activity.this,Team3Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("time",json.getInt("gtime"));
                    bundle.putDouble("point",json.getDouble("point"));
                    bundle.putDouble("rebound",json.getDouble("rebound"));
                    bundle.putDouble("assist",json.getDouble("assist"));
                    bundle.putDouble("steal",json.getInt("steal"));
                    bundle.putDouble("block",json.getDouble("block"));
                    bundle.putDouble("turnover",json.getDouble("ato"));
                    bundle.putDouble("shot",json.getDouble("shot"));
                    bundle.putDouble("shotrate",json.getDouble("shotrate"));
                    bundle.putDouble("threeshot",json.getDouble("threeshot"));
                    bundle.putDouble("threerate",json.getDouble("threerate"));
                    bundle.putDouble("freeshot",json.getDouble("freeshot"));
                    bundle.putDouble("freerate",json.getDouble("freerate"));
                    bundle.putDouble("offrtg",json.getDouble("offrtg"));
                    bundle.putDouble("defrtg",json.getDouble("defrtg"));
                    bundle.putInt("player_id",player_id);
                    bundle.putInt("user_id",user_id);
                    Log.i("tag",json.toString());
                    Log.i("tag","abc");
                    intent.putExtras(bundle);
                    Team2Activity.this.startActivity(intent);

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
                    intent = new Intent(Team2Activity.this,TeamActivity.class);
                    JSONArray jsonArray = json.getJSONArray("list");
                    Log.i("tag","aaa"+jsonArray.getJSONObject(0));
                    List<MembersVO> list = new ArrayList<MembersVO>();
                    intent = new Intent(Team2Activity.this,TeamActivity.class);
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

                AlertDialog dialog = new AlertDialog.Builder(Team2Activity.this)
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
                                                Intent intent = new Intent(Team2Activity.this, TeamActivity.class);

                                                Toast.makeText(Team2Activity.this, "解雇成功", Toast.LENGTH_SHORT).show();
                                                json = playerDao.teamInit(user_id, player_id,"ssm/team/teamShow.json");
                                                intent = new Intent(Team2Activity.this,TeamActivity.class);
                                                JSONArray jsonArray = json.getJSONArray("list");
                                                Log.i("tag","aaa"+jsonArray.getJSONObject(0));
                                                List<MembersVO> list = new ArrayList<MembersVO>();
                                                intent = new Intent(Team2Activity.this,TeamActivity.class);
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
                                                Toast.makeText(Team2Activity.this, "球员正在首发位置上，不能解雇", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(Team2Activity.this, items[which], Toast.LENGTH_SHORT).show();
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
                                                    Toast.makeText(Team2Activity.this, "替换成功", Toast.LENGTH_SHORT).show();
                                                }else{
                                                    Toast.makeText(Team2Activity.this, "替换失败", Toast.LENGTH_SHORT).show();
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
                                                    Toast.makeText(Team2Activity.this, "替换成功", Toast.LENGTH_SHORT).show();
                                                }else{
                                                    Toast.makeText(Team2Activity.this, "替换失败", Toast.LENGTH_SHORT).show();
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
                                                    Toast.makeText(Team2Activity.this, "替换成功", Toast.LENGTH_SHORT).show();
                                                }else{
                                                    Toast.makeText(Team2Activity.this, "替换失败", Toast.LENGTH_SHORT).show();
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
                                                    Toast.makeText(Team2Activity.this, "替换成功", Toast.LENGTH_SHORT).show();
                                                }else{
                                                    Toast.makeText(Team2Activity.this, "替换失败", Toast.LENGTH_SHORT).show();
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
                                                    Toast.makeText(Team2Activity.this, "替换成功", Toast.LENGTH_SHORT).show();
                                                }else{
                                                    Toast.makeText(Team2Activity.this, "替换失败", Toast.LENGTH_SHORT).show();
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
