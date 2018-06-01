package com.example.xd.tactics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button backBtn;
    private Button otactics1;
    private Button otactics2;
    private Button otactics3;
    private Button otactics4;
    private Button otactics5;
    private Button otactics6;
    private Button otactics7;
    private Button otactics8;
    private Button otactics9;
    private Button dtactics1;
    private Button dtactics2;
    private Button dtactics3;
    private Button dtactics4;
    private Button dtactics5;
    private Button dtactics6;
    private Button dtactics7;
    private Button dtactics8;
    private Button dtactics9;
    private Button equippedoTactics;
    private Button equippeddTactics;
    private Button showAllOTactics;
    private Button showAllDTactics;
    private TextView powerText;
    private LinearLayout OTacticsLayout;
    private LinearLayout DTacticsLayout;

    //ceshis
    private int userId = 1;
    private int power;
    private int changePower;
    private Intent intent;
    private int equippedOTacticsNum = 0;
    private int equippedDTacticsNum = 0;

    private Handler progressHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            if(msg.what == 1){
                setEquippedOTactics(equippedOTacticsNum);
                setEquippedDTactics(equippedDTacticsNum);
                powerText.setText(String.valueOf(power));
            }
            else if(msg.what == 2){
                setEquippedOTactics(equippedOTacticsNum);
                powerText.setText(String.valueOf(changePower));
            }
            else if(msg.what == 3){
                setEquippedDTactics(equippedDTacticsNum);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initLisener();
        initData();
    }

    public void initView(){
        backBtn = findViewById(R.id.tactics_backBtn);
        showAllOTactics = findViewById(R.id.showAllOTactics);
        showAllDTactics = findViewById(R.id.showAllDTactics);
        OTacticsLayout = findViewById(R.id.OTacticsLayout);
        DTacticsLayout = findViewById(R.id.DTacticsLayout);
        otactics1 = findViewById(R.id.otactics1);
        otactics1.setText(TacticsConf.otactics[0].getName());
        otactics2 = findViewById(R.id.otactics2);
        otactics2.setText(TacticsConf.otactics[1].getName());
        otactics3 = findViewById(R.id.otactics3);
        otactics3.setText(TacticsConf.otactics[2].getName());
        otactics4 = findViewById(R.id.otactics4);
        otactics4.setText(TacticsConf.otactics[3].getName());
        otactics5 = findViewById(R.id.otactics5);
        otactics5.setText(TacticsConf.otactics[4].getName());
        otactics6 = findViewById(R.id.otactics6);
        otactics6.setText(TacticsConf.otactics[5].getName());
        otactics7 = findViewById(R.id.otactics7);
        otactics7.setText(TacticsConf.otactics[6].getName());
        otactics8 = findViewById(R.id.otactics8);
        otactics8.setText(TacticsConf.otactics[7].getName());
        otactics9 = findViewById(R.id.otactics9);
        otactics9.setText(TacticsConf.otactics[8].getName());
        dtactics1 = findViewById(R.id.dtactics1);
        dtactics1.setText(TacticsConf.dtactics[0].getName());
        dtactics2 = findViewById(R.id.dtactics2);
        dtactics2.setText(TacticsConf.dtactics[1].getName());
        dtactics3 = findViewById(R.id.dtactics3);
        dtactics3.setText(TacticsConf.dtactics[2].getName());
        dtactics4 = findViewById(R.id.dtactics4);
        dtactics4.setText(TacticsConf.dtactics[3].getName());
        dtactics5 = findViewById(R.id.dtactics5);
        dtactics5.setText(TacticsConf.dtactics[4].getName());
        dtactics6 = findViewById(R.id.dtactics6);
        dtactics6.setText(TacticsConf.dtactics[5].getName());
        dtactics7 = findViewById(R.id.dtactics7);
        dtactics7.setText(TacticsConf.dtactics[6].getName());
        dtactics8 = findViewById(R.id.dtactics8);
        dtactics8.setText(TacticsConf.dtactics[7].getName());
        dtactics9 = findViewById(R.id.dtactics9);
        dtactics9.setText(TacticsConf.dtactics[8].getName());

        powerText = findViewById(R.id.tactics_text5);

        equippedoTactics = findViewById(R.id.equippedOTactics);
        equippeddTactics = findViewById(R.id.equippedDTactics);
    }
    public void initLisener(){
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        showAllOTactics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OTacticsLayout.setVisibility(View.VISIBLE);
                DTacticsLayout.setVisibility(View.GONE);
            }
        });

        showAllDTactics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DTacticsLayout.setVisibility(View.VISIBLE);
                OTacticsLayout.setVisibility(View.GONE);
            }
        });

        otactics1.setOnClickListener(this);
        otactics2.setOnClickListener(this);
        otactics3.setOnClickListener(this);
        otactics4.setOnClickListener(this);
        otactics5.setOnClickListener(this);
        otactics6.setOnClickListener(this);
        otactics7.setOnClickListener(this);
        otactics8.setOnClickListener(this);
        otactics9.setOnClickListener(this);

        dtactics1.setOnClickListener(this);
        dtactics2.setOnClickListener(this);
        dtactics3.setOnClickListener(this);
        dtactics4.setOnClickListener(this);
        dtactics5.setOnClickListener(this);
        dtactics6.setOnClickListener(this);
        dtactics7.setOnClickListener(this);
        dtactics8.setOnClickListener(this);
        dtactics9.setOnClickListener(this);

        equippedoTactics.setOnClickListener(this);
        equippeddTactics.setOnClickListener(this);
    }
    public void initData(){
        new Thread(){
            public void run(){
                //intent = getIntent();
                //userId = Integer.parseInt(intent.getStringExtra("user_id"));

                Message msg = new Message();
                msg.what = 0;

                Internet internet = new Internet();
                try {
                    JSONObject object = internet.tacticsInit(1);
                    equippedOTacticsNum = object.getInt("equippedOTactics");
                    equippedDTacticsNum = object.getInt("equippedDTactics");
                    power = object.getInt("power");

                    Log.i("errorlllyf",String.valueOf(equippedOTacticsNum));
                    Log.i("errorlllyf",String.valueOf(equippedDTacticsNum));
                }catch (Exception e){
                    e.toString();
                }

                msg.what = 1;
                progressHandler.sendMessage(msg);
            }
        }.start();
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.otactics1:
                showotacDialog(1);
                break;
            case R.id.otactics2:
                showotacDialog(2);
                break;
            case R.id.otactics3:
                showotacDialog(3);
                break;
            case R.id.otactics4:
                showotacDialog(4);
                break;
            case R.id.otactics5:
                showotacDialog(5);
                break;
            case R.id.otactics6:
                showotacDialog(6);
                break;
            case R.id.otactics7:
                showotacDialog(7);
                break;
            case R.id.otactics8:
                showotacDialog(8);
                break;
            case R.id.otactics9:
                showotacDialog(9);
                break;
            case R.id.dtactics1:
                showdtacDialog(1);
                break;
            case R.id.dtactics2:
                showdtacDialog(2);
                break;
            case R.id.dtactics3:
                showdtacDialog(3);
                break;
            case R.id.dtactics4:
                showdtacDialog(4);
                break;
            case R.id.dtactics5:
                showdtacDialog(5);
                break;
            case R.id.dtactics6:
                showdtacDialog(6);
                break;
            case R.id.dtactics7:
                showdtacDialog(7);
                break;
            case R.id.dtactics8:
                showdtacDialog(8);
                break;
            case R.id.dtactics9:
                showdtacDialog(9);
                break;
            case R.id.equippedOTactics:
                showotacDialog(equippedOTacticsNum);
                break;
            case R.id.equippedDTactics:
                showdtacDialog(equippedDTacticsNum);
        }
    }

    public void showotacDialog(final int id){
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                //.setIcon(R.mipmap.ic_launcher)//设置标题的图片
                .setTitle(TacticsConf.otactics[id-1].getName())//设置对话框的标题
                .setMessage(TacticsConf.otactics[id-1].getContent())//设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("更换", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(){
                            public void run(){
                                Message msg = new Message();
                                msg.what = 0;

                                Internet internet = new Internet();
                                try {
                                    JSONObject object = internet.oTacticsEquip(userId, id);
                                    changePower = object.getInt("changepower");
                                }catch (Exception e){
                                    e.toString();
                                }

                                msg.what = 2;
                                equippedOTacticsNum = id;
                                progressHandler.sendMessage(msg);
                            }
                        }.start();
                        Toast.makeText(MainActivity.this, "更换成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    public void showdtacDialog(final int id){
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                //.setIcon(R.mipmap.ic_launcher)//设置标题的图片
                .setTitle(TacticsConf.dtactics[id-1].getName())//设置对话框的标题
                .setMessage(TacticsConf.dtactics[id-1].getContent())//设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("更换", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(){
                            public void run(){
                                Message msg = new Message();
                                msg.what = 0;

                                Internet internet = new Internet();
                                try {
                                    JSONObject object = internet.dTacticsEquip(userId, id);
                                }catch (Exception e){
                                    e.toString();
                                }

                                msg.what = 3;
                                equippedDTacticsNum = id;
                                progressHandler.sendMessage(msg);
                            }
                        }.start();
                        Toast.makeText(MainActivity.this, "更换成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    public void setEquippedOTactics(int id){
        if(id == 0)
            equippedoTactics.setText("尚未装备");
        else
            equippedoTactics.setText(TacticsConf.otactics[id-1].getName());
    }

    public void setEquippedDTactics(int id){
        if(id == 0)
            equippeddTactics.setText("尚未装备");
        else
            equippeddTactics.setText(TacticsConf.dtactics[id-1].getName());
    }
}
