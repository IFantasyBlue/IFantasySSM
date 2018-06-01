package com.example.xd.test;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.User;

public class MainActivity extends Activity {

    private TextView tv1;


    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //final int user_id = ((User)intent.getSerializableExtra("user")).getId();
        tv1 = findViewById(R.id.tv1);
        tv1.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                matching(1);
            }
        });

    }

    public void matching(final int user_id){
        new Thread(){
            public void run (){
                PlayerDao playerDao = new PlayerDao();
                JSONObject json = new JSONObject();
                JSONObject json2 = new JSONObject();
                Log.i("tag", "abc");
                try {
                    intent = new Intent(MainActivity.this,Main2Activity.class);
                    //使用通信类PlayerDao传数据给后端获得返回的json对象
                    json = playerDao.teamInit(user_id, 1, "ssm/matching/matchingIn.json");
                    Bundle bundle = new Bundle();
                    bundle.putInt("user_id",user_id);
                    intent.putExtras(bundle);

                    MainActivity.this.startActivity(intent);

                }catch(Exception e){
                    e.toString();
                }
            }
        }.start();

    }
}