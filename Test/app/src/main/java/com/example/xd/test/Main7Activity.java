package com.example.xd.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import entity.User;

public class Main7Activity extends AppCompatActivity {

    private TextView tv1;
    private Intent intent;
    private TextView tv2;
    private TextView tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        final int user_id = ((User)bundle.getSerializable("user")).getId();
        tv3.setText("+"+bundle.getInt("money")/10000+"万资金");
        tv1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                returnTo();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                matching(user_id);
            }
        });
    }
    public void returnTo(){
        intent = new Intent(Main7Activity.this,MainActivity.class);
        this.startActivity(intent);
    }
    public void matching(final int user_id){
        new Thread(){
            public void run (){
                PlayerDao playerDao = new PlayerDao();
                JSONObject json = new JSONObject();
                JSONObject json2 = new JSONObject();
                Log.i("tag", "abc");
                try {

                    //使用通信类PlayerDao传数据给后端获得返回的json对象
                    json = playerDao.teamInit(user_id, 1, "ssm/matching/matchingIn.json");

                    intent = new Intent(Main7Activity.this,Main2Activity.class);
                    Main7Activity.this.startActivity(intent);

                }catch(Exception e){
                    e.toString();
                }
            }
        }.start();
    }
}
