package com.teamblue.xd.ifantasy_blue;

import android.app.ActionBar;
import android.app.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import entity.User;
import entity.User_Info;
import view.RankInfoDialog;

public class MainActivity extends Activity implements View.OnClickListener{

    private static final String[] LEVELLIST = {"新秀","职业","全明星","超级明星","名人堂"};

    private ImageView userIconimg;
    private TextView userNametv;
    private TextView userLeveltv;
    private TextView userVIPtv;
    private TextView userPowertv;
    private TextView userMoneytv;
    private Button getMoneybtn;
    private Button settingbtn;
    private Button rank1btn;
    private Button rank2btn;
    private Button rank3btn;
    private Button oprankbtn;
    private Button toChatbtn;
    private Button toBagbtn;
    private Button toLuckybtn;
    private Button toTibtn;
    private Button toPlayerbtn;
    private Button toSigninbtn;
    private Button toActbtn;
    private Button pkbtn;
    private Button pgbtn;
    private Button sgbtn;
    private Button cbtn;
    private Button pfbtn;
    private Button sfbtn;
    private ImageView otimg;
    private ImageView dtimg;

    private Dialog rankDialog;
    private Button rankBackbtn;

    private Intent intent;
    private User user;
    private User_Info userinfo;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case 0:{
                    showUser();
                    break;
                }
                case 1:{}

            }
        }
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
        initListener();
        initData();
    }

    public void initView(){
        userNametv = findViewById(R.id.show_user_name);
        userLeveltv = findViewById(R.id.show_user_level);
        userVIPtv = findViewById(R.id.show_user_vip);
        userPowertv = findViewById(R.id.show_user_power);
        userMoneytv = findViewById(R.id.show_user_money);
        oprankbtn = findViewById(R.id.open_rankbtm);

        toPlayerbtn = findViewById(R.id.to_player_model);

        initDialog();
    }

    public void initFragment(){


    }

    public void initListener(){
        oprankbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     rankDialog.show();
            }
        });

        toPlayerbtn.setOnClickListener(this);

    }
    public void initData(){
        intent = getIntent();
        showUser();
        getRank();
    }

    public void showUser(){
        user = (User)intent.getSerializableExtra("user");

        userNametv.setText(user.getName());
        userPowertv.setText("球队战力："+user.getPower());
        userMoneytv.setText("资金"+user.getMoney());

        userinfo = (User_Info) intent.getSerializableExtra("userinfo");

        userLeveltv.setText("LV:"+LEVELLIST[userinfo.getLevel()]);
        userVIPtv.setText("VIP "+userinfo.getVip());

    }

    public void getRank(){
        new Thread(){
            @Override
            public void run() {
                try {

                    SharedPreferences sp = getSharedPreferences("sp_token", Context.MODE_PRIVATE);
                    String token = sp.getString("token",null);

                    UserModel userModel = new UserModel(token);

                    userModel.getRank(user.getId());
                }catch(Exception e){

                }
            }
        }.start();
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.to_player_model :
                intent = new Intent(MainActivity.this,TeamActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                break;
        }


    }

    public void initDialog(){
        rankDialog = new RankInfoDialog(this);
    }





}
