package com.example.xd.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import entity.User;

public class Main6Activity extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        User user = (User)bundle.getSerializable("user");
        User user2 = (User)bundle.getSerializable("user2");
        tv1.setText(user.getUsername());
        tv2.setText(user.getPower()+"");
        tv3.setText(user2.getUsername());
        tv4.setText(user2.getPower()+"");
        tv5.setText(user.getoTactics()+"");
        tv6.setText(user.getdTactics()+"");
        tv7.setText(user2.getoTactics()+"");
        tv8.setText(user2.getdTactics()+"");
        new Thread(){
            public void run (){

                try {

                    Bundle bundle = intent.getExtras();
                    User user = (User)bundle.getSerializable("user");
                    User user2 = (User)bundle.getSerializable("user2");
                    Thread.sleep(2000);
                    PlayerDao2 playerDao2 = new PlayerDao2();
                    JSONObject jsonObject = playerDao2.teamInit(user.getId(),user2.getId(),"ssm/matching/FinghtingResult.json");
                    if(jsonObject.getString("mesg").equals("win")){
                        bundle.putString("mesg",jsonObject.getString("mesg"));
                        bundle.putInt("money",jsonObject.getInt("money"));
                        intent = new Intent(Main6Activity.this, Main7Activity.class);
                        intent.putExtras(bundle);
                        Main6Activity.this.startActivity(intent);
                    }else{
                        bundle.putString("mesg",jsonObject.getString("mesg"));

                        intent = new Intent(Main6Activity.this, Main8Activity.class);
                        intent.putExtras(bundle);
                        Main6Activity.this.startActivity(intent);
                    }

                }catch(Exception e){
                    e.toString();
                }
            }
        }.start();
    }
}
