package com.teamblue.xd.ifantasy_blue;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import entity.Pickers;
import entity.User;
import entity.User_Info;
import view.PickerScrollView;
import view.PickerScrollView.onSelectListener;

public class SignActivity extends Activity {

    private Intent intent;
    private Button mTeambt;
    private Button mNamebt;
    private PickerScrollView mTeamps;
    private List<Pickers> list;
    private String[] id;
    private String[] teams;

    private TextView mNametv;
    private ImageView mTeamiv;
    private User user;
    private View mTeamv;
    private View mNamev;
    private String team_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        mTeamv = findViewById(R.id.sign_up_team_form);
        mNamev = findViewById(R.id.sign_up_name_form);

        mTeambt = findViewById(R.id.to_name_button);
        mNamebt = findViewById(R.id.sign_button);
        mTeamps = findViewById(R.id.team_name);
        mNametv = findViewById(R.id.user_name);
        mTeamiv = findViewById(R.id.team_img);

        mTeambt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSetName();
            }
        });
        mNamebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSignUp();
            }
        });
        mTeamps.setOnSelectListener(pickerListener);

        intent = getIntent();
        user = new User();
        user.setPhone(intent.getStringExtra("id"));

        this.setTeamId("1");
        list = new ArrayList<Pickers>();
        id = new String[]{"1","2","3","4","5","6","7","8","9","10",
                "11","12","13","14","15","16","17","18","19","20",
                "21","22","23","24","25","26","27","28","29","30"};
        teams = new String[]{"猛龙","凯尔特人","76人","骑士","步行者","热火","雄鹿","奇才","活塞",
                "黄蜂","尼克斯","篮网","公牛","魔术","老鹰","火箭","勇士","开拓者","雷霆","爵士",
                "鹈鹕","马刺","森林狼","掘金","快船","湖人","国王","独行侠","灰熊","太阳"};
        for(int i = 0;i<teams.length;i++){
            list.add(new Pickers(teams[i],id[i]));
        }
        mTeamps.setData(list);
        mTeamps.setSelected(0);

    }

    onSelectListener pickerListener = new onSelectListener() {

        @Override
        public void onSelect(Pickers pickers) {
            System.out.println("选择：" + pickers.getShowId() + "--球队："
                    + pickers.getShowConetnt());
            setTeamId(pickers.getShowId());
        }
    };

    public void setTeamId(String id){
        this.team_id = id;
    }

    public void toSetName(){
        showSetName(true);
    }

    public void toSignUp(){
        new Thread(){

            public void run() {
                UserModel userModel = new UserModel(null);
                String mobile = intent.getStringExtra("id");
                String name = mNametv.getText().toString();
                try{
                    JSONObject o = userModel.userSign(mobile,name,team_id);
                    int status = o.getInt("status");
                    if(status==1){
                        Log.i("tag","注册成功");
                        JSONObject e = o.getJSONObject("user");
                        JSONObject i = o.getJSONObject("userinfo");
                        String token = o.getJSONObject("token").getString("id");
                        SharedPreferences sp = getSharedPreferences("sp_token", Context.MODE_PRIVATE);
                        sp.edit().putString("token",token).commit();
                        intent = new Intent(SignActivity.this,MainActivity.class);
                        User user = new User();
                        user.setId(e.getInt("id"));
                        user.setUserinfo(e.getInt("userinfo"));
                        user.setName(e.getString("name"));
                        user.setPhone(e.getString("phone"));
                        user.setSetting(e.getInt("setting"));
                        user.setPower(e.getInt("power"));
                        user.setMoney(e.getInt("money"));
                        user.setFriendsNum(e.getInt("friendsNum"));
                        user.setPackageNum(e.getInt("packageNum"));
                        user.setoTactics(e.getInt("oTactics"));
                        user.setdTactics(e.getInt("dTactics"));
                        intent.putExtra("user",user);

                        User_Info userinfo = new User_Info();
                        userinfo.setUserId(i.getInt("userId"));
                        userinfo.setLevel(i.getInt("level"));
                        userinfo.setVip(i.getInt("vip"));
                        userinfo.setBelongteam(i.getInt("belongteam"));
                        intent.putExtra("userinfo",userinfo);
                        startActivity(intent);
                        finish();


                    }else if(status==0){
                        Log.e("tag","注册失败");
                    }else{
                        Log.e("tag","网络异常");
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }.start();

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showSetName(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mTeamv.setVisibility(show ? View.GONE : View.VISIBLE);
            mTeamv.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mTeamv.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mNamev.setVisibility(show ? View.VISIBLE : View.GONE);
            mNamev.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mNamev.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mNamev.setVisibility(show ? View.VISIBLE : View.GONE);
            mTeamv.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
