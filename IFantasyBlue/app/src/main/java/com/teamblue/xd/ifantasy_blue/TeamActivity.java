package com.teamblue.xd.ifantasy_blue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import entity.MembersVO;
import entity.TeamMembers;
import entity.User;

public class TeamActivity extends Activity {

    private ImageView[] iv = new ImageView[10];
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv20;
    private TextView tv21;
    private TextView tv24;

    private TextView[] tv = new TextView[10];
    String[] name = new String[10];
//    private TextView tv7;
//    private TextView tv8;
//    private TextView tv9;
//    private TextView tv10;
//    private TextView tv11;
//    private TextView tv12;
//    private TextView tv13;
//    private TextView tv14;
//    private TextView tv15;
//    private TextView tv16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        initView();
        //绑定按钮bt1的点击事件
        Intent intent = getIntent();
        final User user = (User)intent.getSerializableExtra("user");
        tv1.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirectToAll(user.getId());
            }
        });
        tv2.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirectToC(user.getId());
            }
        });
        tv3.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirectToPF(user.getId());
            }
        });
        tv4.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirectToSF(user.getId());
            }
        });
        tv5.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirectToSG(user.getId());
            }
        });
        tv6.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirectToPG(user.getId());
            }
        });
        tv[0].setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View view){
                    redirect(tv[0].getText().toString(),user.getId());
                }
            });
        tv[1].setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirect(tv[1].getText().toString(),user.getId());
            }
        });
        tv[2].setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirect(tv[2].getText().toString(),user.getId());
            }
        });
        tv[3].setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirect(tv[3].getText().toString(),user.getId());
            }
        });
        tv[4].setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirect(tv[4].getText().toString(),user.getId());
            }
        });
        tv[5].setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirect(tv[5].getText().toString(),user.getId());
            }
        });
        tv[6].setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirect(tv[6].getText().toString(),user.getId());
            }
        });
        tv[7].setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirect(tv[7].getText().toString(),user.getId());
            }
        });
        tv[8].setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirect(tv[8].getText().toString(),user.getId());
            }
        });
        tv[9].setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirect(tv[9].getText().toString(),user.getId());
            }
        });




    }
    //初始化页面的函数
    public void initView(){
        iv[0] = findViewById(R.id.iv1);
        iv[1] = findViewById(R.id.iv2);
        iv[2] = findViewById(R.id.iv3);
        iv[3] = findViewById(R.id.iv4);
        iv[4] = findViewById(R.id.iv5);
        iv[5] = findViewById(R.id.iv6);
        iv[6] = findViewById(R.id.iv7);
        iv[7] = findViewById(R.id.iv8);
        iv[8] = findViewById(R.id.iv9);
        iv[9] = findViewById(R.id.iv10);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv20 = findViewById(R.id.tv20);
        tv21 = findViewById(R.id.tv21);
        tv24 = findViewById(R.id.tv24);
        tv[0] = findViewById(R.id.tv7);
        tv[1] = findViewById(R.id.tv8);
        tv[2] = findViewById(R.id.tv9);
        tv[3] = findViewById(R.id.tv10);
        tv[4] = findViewById(R.id.tv11);
        tv[5] = findViewById(R.id.tv12);
        tv[6] = findViewById(R.id.tv13);
        tv[7] = findViewById(R.id.tv14);
        tv[8] = findViewById(R.id.tv15);
        tv[9] = findViewById(R.id.tv16);
        Intent intent = getIntent();


            Bundle bundle = intent.getExtras();
            if(getIntent().getSerializableExtra("list") == null){

            }else{

                List<MembersVO> list = (ArrayList<MembersVO>) getIntent().getSerializableExtra("list");
                for(int i=0;i<list.size();i++){
                    tv[i].setText(list.get(i).getName());
                    iv[i].setImageResource(getImageFromResourceByReflect("player"+list.get(i).getTeamMembers().getPlayerId()));
                }
                tv20.setText("球队战力:"+bundle.getInt("power"));
                tv21.setText("资金:"+bundle.getInt("money")/10000+"万");
                tv24.setText(bundle.getString("username"));
            }



    }

    /**
     * 利用反射方法
     * 已知图片名称获取图片的资源id的方法
     *
     * @param imageName
     * @return resourceID
     */
    public int getImageFromResourceByReflect(String imageName) {
        Class drawable = R.mipmap.class;
        Field field = null;
        int r_id;
        try {
            field = drawable.getField(imageName);
            r_id = field.getInt(field.getName());
        } catch (Exception e) {
            r_id = R.mipmap.ic_launcher;
            Log.e("ERROR", "PICTURE NOT　FOUND！");
        }
        return r_id;
    }

    public void redirect(final String name, final int user_id){
        new Thread(){
            public void run (){
                PlayerDao2 playerDao2 = new PlayerDao2();
                JSONObject json = new JSONObject();
                if(name.equals("")){

                }else{
                    try {
                        //使用通信类PlayerDao传数据给后端获得返回的json对象
                        json = playerDao2.teamInit(user_id, name,"ssm/team/playerShow2.json");
                        //进行页面跳转
                        Intent intent = new Intent(TeamActivity.this,Team2Activity.class);
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
                        bundle.putDouble("perEv",json.getDouble("perEv"));
                        bundle.putInt("player_id",json.getInt("player_id"));
                        bundle.putInt("user_id",user_id);
                        Log.i("tag",json.toString());
                        Log.i("tag","abc");
                        //把bundle放入intent
                        intent.putExtras(bundle);
                        //开始页面跳转
                        TeamActivity.this.startActivity(intent);

                    }catch(Exception e){
                        e.toString();
                    }
                }

            }
        }.start();
    }
    public void redirectToAll(final int user_id){
        new Thread(){
            public void run (){
                PlayerDao playerDao = new PlayerDao();
                JSONObject json = new JSONObject();


                try {
                    //使用通信类PlayerDao传数据给后端获得返回的json对象
                    json = playerDao.teamInit(user_id, 1,"ssm/team/teamShow.json");

                    JSONArray jsonArray = json.getJSONArray("list");
                    Log.i("tag","aaa"+jsonArray.getJSONObject(0));
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
                        tv[i].setText(membersVO.getName()+"");
                        Log.i("qqq",i+"");
                    }

                    Log.i("tag",json.toString());
                    Log.i("tag","abc");
                    //把bundle放入intent

                }catch(Exception e){
                    e.toString();
                }
            }
        }.start();
    }
    public void redirectToC(final int user_id){
        new Thread(){
            public void run (){
                PlayerDao playerDao = new PlayerDao();
                JSONObject json = new JSONObject();


                try {
                    //使用通信类PlayerDao传数据给后端获得返回的json对象
                    json = playerDao.teamInit(user_id, 1,"ssm/team/teamShowC.json");

                    JSONArray jsonArray = json.getJSONArray("list");
                    Log.i("tag","aaa"+jsonArray.getJSONObject(0));
                    int i = 0;
                    for(i=0;i<jsonArray.length();i++){
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
                        tv[i].setText(membersVO.getName()+"");
                        Log.i("qqq",i+"");
                    }
                    for(;i<10;i++){
                        tv[i].setText("");
                    }
                    Log.i("tag",json.toString());
                    Log.i("tag","abc");
                    //把bundle放入intent


                }catch(Exception e){
                    e.toString();
                }
            }
        }.start();
    }

    public void redirectToPF(final int user_id){
        new Thread(){
            public void run (){
                PlayerDao playerDao = new PlayerDao();
                JSONObject json = new JSONObject();


                try {
                    //使用通信类PlayerDao传数据给后端获得返回的json对象
                    json = playerDao.teamInit(user_id, 1,"ssm/team/teamShowPF.json");

                    JSONArray jsonArray = json.getJSONArray("list");
                    Log.i("tag","aaa"+jsonArray.getJSONObject(0));
                    int i = 0;
                    for(i=0;i<jsonArray.length();i++){
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
                        tv[i].setText(membersVO.getName()+"");
                        Log.i("qqq",i+"");
                    }
                    for(;i<10;i++){
                        tv[i].setText("");
                    }
                    Log.i("tag",json.toString());
                    Log.i("tag","abc");
                    //把bundle放入intent


                }catch(Exception e){
                    e.toString();
                }
            }
        }.start();
    }
    public void redirectToSF(final int user_id){
        new Thread(){
            public void run (){
                PlayerDao playerDao = new PlayerDao();
                JSONObject json = new JSONObject();


                try {
                    //使用通信类PlayerDao传数据给后端获得返回的json对象
                    json = playerDao.teamInit(user_id, 1,"ssm/team/teamShowSF.json");

                    JSONArray jsonArray = json.getJSONArray("list");
                    Log.i("tag","aaa"+jsonArray.getJSONObject(0));
                    int i = 0;
                    for(i=0;i<jsonArray.length();i++){
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
                        tv[i].setText(membersVO.getName()+"");
                        Log.i("qqq",i+"");
                    }

                    for(;i<10;i++){
                        tv[i].setText("");
                    }
                    Log.i("tag",json.toString());
                    Log.i("tag","abc");
                    //把bundle放入intent


                }catch(Exception e){
                    e.toString();
                }
            }
        }.start();
    }
    public void redirectToSG(final int user_id){
        new Thread(){
            public void run (){
                PlayerDao playerDao = new PlayerDao();
                JSONObject json = new JSONObject();


                try {
                    //使用通信类PlayerDao传数据给后端获得返回的json对象
                    json = playerDao.teamInit(user_id, 1,"ssm/team/teamShowSG.json");

                    JSONArray jsonArray = json.getJSONArray("list");
                    Log.i("tag","aaa"+jsonArray.getJSONObject(0));
                    int i = 0;
                    for(i=0;i<jsonArray.length();i++){
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
                        tv[i].setText(membersVO.getName()+"");
                        Log.i("qqq",i+"");
                    }
                    for(;i<10;i++){
                        tv[i].setText("");
                    }

                    Log.i("tag",json.toString());
                    Log.i("tag","abc");
                    //把bundle放入intent


                }catch(Exception e){
                    e.toString();
                }
            }
        }.start();
    }
    public void redirectToPG(final int user_id){
        new Thread(){
            public void run (){
                PlayerDao playerDao = new PlayerDao();
                JSONObject json = new JSONObject();


                try {
                    //使用通信类PlayerDao传数据给后端获得返回的json对象
                    json = playerDao.teamInit(user_id, 1,"ssm/team/teamShowPG.json");

                    JSONArray jsonArray = json.getJSONArray("list");
                    Log.i("tag","aaa"+jsonArray.getJSONObject(0));
                    int i = 0;
                    for(i=0;i<jsonArray.length();i++){
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
                        tv[i].setText(membersVO.getName()+"");
                        Log.i("qqq",i+"");
                    }
                    for(;i<10;i++){
                        tv[i].setText("");
                    }
                    Log.i("tag",json.toString());
                    Log.i("tag","abc");
                    //把bundle放入intent


                }catch(Exception e){
                    e.toString();
                }
            }
        }.start();
    }
    public void initListener(){

    }
}
