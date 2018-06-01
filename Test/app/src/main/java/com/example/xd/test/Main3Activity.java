package com.example.xd.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import entity.User;

public class Main3Activity extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        User user = (User)bundle.getSerializable("user");
        User user2 = (User)bundle.getSerializable("user2");
        tv1.setText(user.getUsername());
        tv2.setText(user.getPower()+"");
        tv3.setText(user2.getUsername());
        tv4.setText(user2.getPower()+"");
        new Thread(){
            public void run (){

                try {
                    Bundle bundle = intent.getExtras();
                    Thread.sleep(2000);
                    Log.i("tag","iii");

                    Log.i("tag","jjj");
                    intent = new Intent(Main3Activity.this, Main4Activity.class);
                    intent.putExtras(bundle);
                    Main3Activity.this.startActivity(intent);
                }catch(Exception e){
                    e.toString();
                }
            }
        }.start();


        //开始页面跳转
       //
    }
}
