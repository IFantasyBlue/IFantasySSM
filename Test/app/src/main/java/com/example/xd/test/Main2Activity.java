package com.example.xd.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.User;

public class Main2Activity extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        final int user_id = getIntent().getExtras().getInt("user_id");
        new Thread(){
            public void run (){

                Log.i("tag", "abc");
                int j = 0;
                boolean flag = true;
                try {
                    while(flag) {
                        Thread.sleep(1000);
                        PlayerDao playerDao = new PlayerDao();
                        JSONObject json = new JSONObject();
                        JSONObject json2 = new JSONObject();
                        //使用通信类PlayerDao传数据给后端获得返回的json对象
                        json = playerDao.teamInit(user_id, 1, "ssm/matching/matchingIn2.json");
                        json2 = playerDao.teamInit(user_id, 1, "ssm/team/teamShow.json");
                        Log.i("tag", "bbb");
                        Log.i("tag", json2.toString());
                        //新建一个bundle对象并往里面放数据传给下一个页面
                        Bundle bundle = new Bundle();
                        if (json.getInt("status") == 0) {
                            bundle.putInt("second", json.getInt("second"));
                            bundle.putInt("user_id", 1);
                            bundle.putInt("power", json2.getInt("power"));
                            Log.i("tag", json.toString());
                            Log.i("tag", "aaa");
                            tv1.setText("正在匹配 " + j++ + "s");
                            tv2.setText("id:" + 1);
                            tv3.setText(json2.getInt("power")+"");
                            continue;
                        } else {
                            Log.i("tag", "uuu" + json.toString());
                            User user = new User();
                            JSONObject json3 = json.getJSONObject("user1");
                            Log.i("tag", "qqq" + json3.toString());
                            user.setPower(json3.getInt("power"));
                            user.setId(json3.getInt("id"));
                            user.setdTactics(json3.getInt("dTactics"));
                            user.setMoney(json3.getInt("money"));
                            user.setoTactics(json3.getInt("oTactics"));
                            user.setUsername(json3.getString("name"));
                            User user2 = new User();

                            JSONObject json4 = json.getJSONObject("user2");
                            Log.i("tag", "nnn" + json4.toString());
                            user2.setPower(json4.getInt("power"));
                            user2.setId(json4.getInt("id"));
                            user2.setdTactics(json4.getInt("dTactics"));
                            user2.setMoney(json4.getInt("money"));
                            user2.setoTactics(json4.getInt("oTactics"));
                            user2.setUsername(json4.getString("name"));

                            bundle.putSerializable("user",user);
                            bundle.putSerializable("user2",user2);


                            //进行页面跳转
                            intent = new Intent(Main2Activity.this, Main3Activity.class);
                            intent.putExtras(bundle);
                            flag = false;
                            //开始页面跳转
                            Main2Activity.this.startActivity(intent);

                        }

                    }
                }catch(Exception e){
                    e.toString();
                }
            }
        }.start();
    }

    public void initView(){
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);


    }
}
