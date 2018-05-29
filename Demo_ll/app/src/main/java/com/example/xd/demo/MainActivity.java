package com.example.xd.demo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import entity.Players;
import entity.PlayersInfo;

public class MainActivity extends Activity {
    //幸运招募
    private Button btnOnce;
    private Button btnFive;
    private TextView money;

    private  int flag=0;

    private TextView timeOK;
    private  double recruitTime;
    private long time;
    private long finalTime = 172800000;   //48小时
    //private long finalTime = 120000;   //2分钟  测试
    private double diffTime;
    private double remainTime;
    private int userMoney;
    private int userId = 1;
    private  List<JSONObject> goodslist = new ArrayList();
    private String goodsInfoName;
    private String goodsName;
    private String goodsType;
    private int goodsAttr;

    //定向招募
    private String res;
    private String player;
    private int playerId;
    private String playerName;
    private PlayersInfo playersInfo = new PlayersInfo();
    private  List<JSONObject> playerslist = new ArrayList();
    private TextView all;
    private TextView c;
    private TextView pf;
    private TextView sf;
    private TextView sg;
    private TextView pg;
    private Spinner spinner;
    private ImageView[] iv = new ImageView[8];
    private TextView[] tv = new TextView[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initLisener();
    }

    //初始化页面的函数
    public void initView(){
        //免费招募
        btnOnce=findViewById(R.id.btnOnce);
        btnFive=findViewById(R.id.btnFive);
        timeOK=findViewById(R.id.timeOK);
        money =findViewById(R.id.money);


        //定向招募
        spinner =findViewById(R.id.spinner);
        iv[0] = findViewById(R.id.iv1);
        iv[1] = findViewById(R.id.iv2);
        iv[2] = findViewById(R.id.iv3);
        iv[3] = findViewById(R.id.iv4);
        iv[4] = findViewById(R.id.iv5);
        iv[5] = findViewById(R.id.iv6);
        iv[6] = findViewById(R.id.iv7);
        iv[7] = findViewById(R.id.iv8);

        all = findViewById(R.id.all);
        c = findViewById(R.id.c);
        pf = findViewById(R.id.pf);
        sf = findViewById(R.id.sf);
        sg = findViewById(R.id.sg);
        pg = findViewById(R.id.pg);

        tv[0] = findViewById(R.id.tv1);
        tv[1] = findViewById(R.id.tv2);
        tv[2] = findViewById(R.id.tv3);
        tv[3] = findViewById(R.id.tv4);
        tv[4] = findViewById(R.id.tv5);
        tv[5] = findViewById(R.id.tv6);
        tv[6] = findViewById(R.id.tv7);
        tv[7] = findViewById(R.id.tv8);

        }


    public void initLisener(){
        btnOnce.setOnClickListener(new OnClickListener(){
        @Override
        public void onClick(View view){
            if(flag==0){  //可以免费招募
                getData0();
            }
            else{
                getData1();  //招募一次
            }
        }
    });
        btnFive.setOnClickListener(new OnClickListener(){
        @Override
        public void onClick(View view){
            getData5();  //招募五次
        }
    });
        all.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirectToPos("ALL",1);
            }
        });
        c.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirectToPos("C",1);
            }
        });
        pf.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirectToPos("PF",1);
            }
        });
        sf.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirectToPos("SF",1);
            }
        });
        sg.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirectToPos("SG",1);
            }
        });
        pg.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                redirectToPos("SG",1);
            }
        });
        tv[0].setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                getPlayerInfo(tv[0].getText().toString());
            }
        });
        tv[1].setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                getPlayerInfo(tv[1].getText().toString());
            }
        });
        tv[2].setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                getPlayerInfo(tv[2].getText().toString());
            }
        });
        tv[3].setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                getPlayerInfo(tv[3].getText().toString());
            }
        });
        tv[4].setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                getPlayerInfo(tv[4].getText().toString());
            }
        });
        tv[5].setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                getPlayerInfo(tv[5].getText().toString());
            }
        });
        tv[6].setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                getPlayerInfo(tv[6].getText().toString());
            }
        });
        tv[7].setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                getPlayerInfo(tv[7].getText().toString());
            }
        });
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //当选中某一个数据项时触发该方法
            /*
             * parent接收的是被选择的数据项所属的 Spinner对象，
             * view参数接收的是显示被选择的数据项的TextView对象
             * position接收的是被选择的数据项在适配器中的位置
             * id被选择的数据项的行号
             */
                int item =position;
                switch (item){
                    case 0:
                        redirectToPos("ALL",userId);
                        break;
                    case 1:
                        directByScore();
                        break;
                    case 2:
                        directBySalary();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }
    public void showDialog1(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("获得奖励");
        builder.setMessage("获得 "+show(goodslist,1)+"!");
        builder.setPositiveButton("领取",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    public void showDialog5(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("获得奖励");
        builder.setMessage("获得 "+show(goodslist,1)+"\n"+"获得 "+show(goodslist,2)+"\n"+"获得 "+show(goodslist,3)+"\n"+"获得 "+show(goodslist,4)+"\n"+"获得 "+show(goodslist,5));
        builder.setPositiveButton("领取",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    public String show(List<JSONObject> goods,int i) {

        try {
                goodsName = goods.get(i).getString("goodsName");
                goodsType = goods.get(i).getString("goodsType");
                goodsAttr = goods.get(i).getInt("goodsAttr");
            } catch (Exception e) {
                e.toString();
        }

        if (goodsType != null) {
            if (goodsType.equals("Fragment")) {
                return goodsName + "碎片x" + goodsAttr + "张";
            } else if (goodsType.equals("MoneyCard")) {
                if (goodsName.equals("three")) {
                    return "三倍资金卡x" + goodsAttr + "张";
                } else {
                    return "两倍资金卡x" + goodsAttr + "张";
                }
            } else if (goodsType.equals("ExpCard")) {
                return goodsName + "体验卡x" + goodsAttr + "天";
            } else {
                return "球员" + goodsName;
            }
        }
        else{
            return null;
        }
    }

    public void  initData() {
        new Thread() {
            public void run() {

                Message msg = new Message();
                msg.what = 015;
                Message msg2 = new Message();
                msg2.what = 015;
                recruitModel recruit = new recruitModel();
                try {
                    JSONArray jsonArray = recruit.showPost(userId, serverConfiguration.recruitServerURL);
                    JSONObject user = jsonArray.getJSONObject(0);
                    recruitTime = user.getDouble("recruittime");
                    userMoney = user.getInt("money");
                    System.out.println("userMoney:"+userMoney);
                    //初始化页面显示8张球员的图片
                    playerslist.clear();
                    for(int i=0;i<jsonArray.length()-1;i++){

                        JSONObject player = jsonArray.getJSONObject(i+1);
                        playerslist.add(player);
                        Log.i("tag",player.toString());
                    }
                    userHandler.sendMessage(msg);
                    playerInfoHandler.sendMessage(msg2);
                    Log.i("tag", user.toString());
                    Log.i("tag", String.valueOf(userMoney));
                } catch (Exception e) {
                    e.toString();
                    System.out.println(e.toString());
                }
            }
        }.start();
    }
    public void  getData0() {
        new Thread() {
            public void run() {

                Message msg = new Message();
                msg.what = 0;

                recruitModel recruit = new recruitModel();
                try {

                    JSONObject json = recruit.luckilyPost(userId, serverConfiguration.freeServerURL);

                    JSONObject goods = json.getJSONObject("goods");
                    goodslist.add(goods);

                    goodsName = goods.getString("goodsName");
                    goodsType = goods.getString("goodsType");
                    goodsAttr = goods.getInt("goodsAttr");

                    JSONObject user = json.getJSONObject("user");
                    recruitTime = user.getDouble("recruittime");
                    userMoney = user.getInt("money");

                    Log.i("tag", goods.toString());
                    Log.i("tag", String.valueOf(goodsName));
                    Log.i("tag", String.valueOf(goodsType));
                    Log.i("tag", String.valueOf(goodsAttr));
                    Log.i("tag", String.valueOf(recruitTime));
                    Log.i("tag", String.valueOf(userMoney));

                    userHandler.sendMessage(msg);
                    userHandler.sendEmptyMessage(015);
                    timeHandler.sendEmptyMessage(1);
                } catch (Exception e) {
                    e.toString();
                }

            }
        }.start();
    }

    public void  getData1() {
        new Thread() {
            public void run() {

                Message msg = new Message();
                msg.what = 1;

                recruitModel recruit = new recruitModel();
                try {

                    JSONObject json = recruit.luckilyPost(userId, serverConfiguration.onceServerURL);
                    JSONObject goods = json.getJSONObject("goods");
                    goodslist.add(goods);

                    goodsName = goods.getString("goodsName");
                    goodsType = goods.getString("goodsType");
                    goodsAttr = goods.getInt("goodsAttr");

                    JSONObject user = json.getJSONObject("user");
                    userMoney = user.getInt("money");

                    Log.i("tag", goods.toString());
                    Log.i("tag", String.valueOf(goodsName));
                    Log.i("tag", String.valueOf(goodsType));
                    Log.i("tag", String.valueOf(goodsAttr));
                    Log.i("tag", String.valueOf(userMoney));

                    userHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.toString();
                }

            }
        }.start();
    }

    public void  getData5(){
        new Thread(){
            public void run (){

                Message msg = new Message();
                msg.what = 5;
                recruitModel recruit = new recruitModel();
                try {
                    JSONObject json = recruit.luckilyPost(userId, serverConfiguration.fiveServerURL);
                    JSONObject goods1 = json.getJSONObject("goods1");
                    goodslist.add(goods1);
                    JSONObject goods2 = json.getJSONObject("goods2");
                    goodslist.add(goods2);
                    JSONObject goods3 = json.getJSONObject("goods3");
                    goodslist.add(goods3);
                    JSONObject goods4 = json.getJSONObject("goods4");
                    goodslist.add(goods4);
                    JSONObject goods5 = json.getJSONObject("goods5");
                    goodslist.add(goods5);

                    JSONObject user = json.getJSONObject("user");
                    userMoney = user.getInt("money");

                    Log.i("tag", goodslist.toString());
                    Log.i("tag", String.valueOf(userMoney));

                    userHandler.sendMessage(msg);
                }catch(Exception e){
                    e.toString();
                    System.out.println("five :" +e.toString());
                }
            }
        }.start();
    }

    //处理用户资金
    private  Handler userHandler=new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 015:
                    startTime();
                    System.out.println("初始化页面");
                    break;
                case 0:
                    userMoney-=0;
                    showDialog1();
                    break;
                case 1:
                    userMoney-=10000;
                    showDialog1();
                    break;
                case 5:
                    userMoney-=40000;
                    showDialog5();
                    break;
                case 555:   //定向招募添加成员
                    if(res.equals("success!")){
                        userMoney-=20000;
                    }
                    break;
            }
            if(userMoney>=10000){
                userMoney = userMoney/10000;
                money.setText(""+userMoney+"万");
            }else{
                money.setText(""+userMoney);
            }
        }
    };

    private Handler timeHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    timeOK.setText("可以免费招募");
                    break;
                case 1:
                    DateFormat df = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
                    df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
                    timeOK.setText("免费招募倒计时" + df.format(remainTime));
                    break;
            }
        }
    };
    public void startTime() {
        time=System.currentTimeMillis();  //当前时间
        diffTime = time-recruitTime;   //时间差
        remainTime=finalTime-diffTime;  //剩余时间

        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Log.i("tag", "招募时间"+df.format(recruitTime));
        Log.i("tag", "当前时间"+df.format(time));
        Log.i("tag", "时间差"+diffTime);
        Log.i("tag", "剩余时间"+remainTime);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (remainTime < 0) {
                    timeHandler.sendEmptyMessage(0);
                }
                else {
                    remainTime -= 1000;
                    flag = 1;
                    timeHandler.sendEmptyMessage(1);
                    timeHandler.postDelayed(this, 1000);

                    if (remainTime <= 0 ) {
                        flag = 0;
                        timeHandler.sendEmptyMessage(0);
                    }
                }
            }
        };
        new Thread(runnable).start();
    }

    public void directByScore(){
        new Thread(){
            public void run (){
                Message msg = new Message();
                msg.what = 202;
                recruitModel recruit = new recruitModel();

                try {
                    //使用通信类PlayerDao传数据给后端获得返回的json对象
                    JSONArray jsonArray = recruit.Post(userId,serverConfiguration.scoreServerURL);
                    playerslist.clear();
                    for(int i=0;i<jsonArray.length();i++){

                        JSONObject player = jsonArray.getJSONObject(i);

                        playerslist.add(player);
                        Log.i("tag",player.toString());
                    }

                    Log.i("tag","directByScore");
                    playerInfoHandler.sendMessage(msg);
                }catch(Exception e){
                    e.toString();
                }
            }
        }.start();
    }
    public void directBySalary(){
        new Thread(){
            public void run (){
                Message msg = new Message();
                msg.what = 203;
                recruitModel recruit = new recruitModel();

                try {
                    //使用通信类PlayerDao传数据给后端获得返回的json对象
                    JSONArray jsonArray = recruit.Post(userId,serverConfiguration.salaryServerURL);
                    playerslist.clear();
                    for(int i=0;i<jsonArray.length();i++){

                        JSONObject player = jsonArray.getJSONObject(i);

                        playerslist.add(player);
                        Log.i("tag",player.toString());
                    }

                    Log.i("tag","directBySalary");
                    playerInfoHandler.sendMessage(msg);
                }catch(Exception e){
                    e.toString();
                    System.out.println(e.toString());
                }
            }
        }.start();
    }

    /**
     * 已知获取图片名称获取图片的资源id的方法
     * @return resourceID
     */
    public int getImageFromResourceByName(String imageName) {
        Context context = getBaseContext();
        int resId = getResources().getIdentifier(imageName, "mipmap", context.getPackageName());
        return resId;
    }

    public void updatePic(List<JSONObject> playerslist){
        for(int i=0;i<playerslist.size();i++){
            try {
                playerId=playerslist.get(i).getInt("id");
                playerName = playerslist.get(i).getString("name");
                tv[i].setText(playerName);
                iv[i].setImageResource(getImageFromResourceByName("player_"+playerId));
                System.out.println("player_"+playerId);
            } catch (Exception e) {
                e.toString();
                System.out.println(e.toString());
            }
        }
    }

    //按球员位置显示球员
    public void redirectToPos(final String position, final int user_id){
        new Thread(){
            public void run (){
                Message msg = new Message();
                msg.what = 201;
                recruitModel recruit = new recruitModel();

                try {
                    //使用通信类PlayerDao传数据给后端获得返回的json对象
                    JSONArray jsonArray = recruit.directPost(userId,position,serverConfiguration.posServerURL);
                    playerslist.clear();
                    for(int i=0;i<jsonArray.length();i++){

                        JSONObject player = jsonArray.getJSONObject(i);

                        playerslist.add(player);
                        Log.i("tag",player.toString());
                    }

                    Log.i("tag","redirectToPos");
                    playerInfoHandler.sendMessage(msg);
                }catch(Exception e){
                    e.toString();
                }
            }
        }.start();
    }
    //向服务端请求球员的信息
    public void getPlayerInfo(final String player_name){

        new Thread(){
            public void run (){
                Message msg = new Message();
                msg.what = 444;
                recruitModel recruit = new recruitModel();
                try {
                    //使用通信类PlayerDao传数据给后端获得返回的json对象
                    JSONObject json = recruit.playerInfoGet(player_name,serverConfiguration.playerInfoURL);
                    JSONObject playerInfo = json.getJSONObject("playerInfo");
                    goodsInfoName =player_name;
                    playersInfo.setId(playerInfo.getInt("id"));
                    //playersInfo.setBirth(playerInfo.getDate("birth"));
                    playersInfo.setArms(playerInfo.getDouble("arms"));
                    playersInfo.setDraft(playerInfo.getString("draft"));
                    playersInfo.setHeight(playerInfo.getDouble("height"));
                    playersInfo.setNation(playerInfo.getString("nation"));
                    playersInfo.setStandTall(playerInfo.getDouble("standTall"));
                    playersInfo.setWeight(playerInfo.getDouble("weight"));
                    Log.i("tag",json.toString());
                    playerInfoHandler.sendMessage(msg);
                }catch(Exception e){
                    e.toString();
                }
            }
        }.start();
    }
    //展示球员信息，并确认是否添加球员的弹窗
    public void showPlayerDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("基本信息" +
                "");
        builder.setMessage("球员："+goodsInfoName+"\n"+"臂展: "+playersInfo.getArms()+"\n"+"选秀: "+playersInfo.getDraft()+"\n"+"身高: "+playersInfo.getHeight()+"m"+"\n"+"国籍: "+playersInfo.getNation()+"\n"+"站立摸高: "+playersInfo.getStandTall()+"cm"+"\n"+"体重: "+playersInfo.getWeight()+"KG");
        builder.setPositiveButton("招募", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Thread(){
                            public void run (){
                                player=goodsInfoName;
                                Message msg = new Message();
                                Message msg2=new Message();
                                msg.what = 555;
                                msg2.what =555;
                                recruitModel recruit = new recruitModel();
                                try {
                                    JSONObject json= recruit.addPlayer(userId, player,serverConfiguration.addPlayerURL);
                                    res = json.getString("res");
                                    JSONObject user = json.getJSONObject("user");
                                    userMoney=user.getInt("money");
                                    System.out.println("money:"+money);
                                    Log.i("tag",res);
                                    Log.i("tag", String.valueOf(userMoney));
                                    playerInfoHandler.sendMessage(msg);
                                    System.out.println("playerInfoHandler.sendMessage");
                                    userHandler.sendMessage(msg2);
                                    System.out.println("userHandler.sendMessage");
                                }catch(Exception e){
                                    e.toString();
                                    System.out.println(e.toString());
                                }
                            }
                        }.start();
                    }
                });
        builder.setNegativeButton("返回",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    //显示添加球员是否成功的弹窗
    public void showResDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setMessage(res);
        builder.setNegativeButton("返回",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    private  Handler playerInfoHandler=new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 444:
                    showPlayerDialog();
                    break;
                case 555:
                    showResDialog();
                    break;
                case 015:
                case 201:
                case 202:
                case 203:
                    System.out.println("case 015");
                    updatePic(playerslist);
                    break;
            }
        }
    };
}