package view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.teamblue.xd.ifantasy_blue.R;

/**
 * Created by xd on 18-4-26.
 */

public class RankInfoDialog extends Dialog {

    private LinearLayout layout;
    Context context;
    View localView;

    public RankInfoDialog(Context context){

        super(context);
        this.context = context;
    }

    public RankInfoDialog(Context context, int layout){
        super(context);
        setContentView(layout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        LayoutInflater inflater =  LayoutInflater.from(context);
        localView = inflater.inflate(R.layout.rank_info_dialog, null);

        //localView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.rank_enter_anim));
        setContentView(localView);

        getWindow().setWindowAnimations(R.style.RankDialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        initView();
        initListener();
        initData();
    }
    public void initView(){
        layout = (LinearLayout) findViewById(R.id.rank_Dialog_layout);
    }

    public void initListener(){
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
    }

    public void initData(){

    }

}
