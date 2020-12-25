package com.example.lovecominghome.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.lovecominghome.Fragment.MyFragment;
import com.example.lovecominghome.R;
import com.example.lovecominghome.Fragment.UserCenterFragment;
import com.example.lovecominghome.Fragment.message;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    //UI Object
    private TextView txt_topbar;
    private TextView txt_home;
    private TextView txt_task;
    private TextView txt_alert;
    private TextView txt_comment;
    private TextView txt_my;
    private FrameLayout ly_content;

    //Fragment Object
    private MyFragment fg1,fg2,fg3;
    private message mg4;
    private UserCenterFragment user_center;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        fManager = getFragmentManager();
        bindViews();
        txt_home.performClick();   //模拟一次点击，既进去后选择第一项
    }

    //UI组件初始化与事件绑定
    private void bindViews() {
        txt_topbar = (TextView) findViewById(R.id.txt_topbar);
        txt_home = (TextView) findViewById(R.id.txt_home);
        txt_task = (TextView) findViewById(R.id.txt_task);
        txt_alert = (TextView) findViewById(R.id.txt_alert);
        txt_comment = (TextView) findViewById(R.id.txt_comment);
        txt_my = (TextView) findViewById(R.id.txt_my);
        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        txt_home.setOnClickListener(this);
        txt_task.setOnClickListener(this);
        txt_alert.setOnClickListener(this);
        txt_comment.setOnClickListener(this);
        txt_my.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    private void setSelected(){
        txt_home.setSelected(false);
        txt_task.setSelected(false);
        txt_alert.setSelected(false);
        txt_comment.setSelected(false);
        txt_my.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
        if(mg4 != null)fragmentTransaction.hide(mg4);
        if(user_center!=null)fragmentTransaction.hide(user_center);
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.txt_home:
                setSelected();
                txt_home.setSelected(true);
                if(fg1 == null){
                    fg1 = new MyFragment("第一个Fragment");
                    fTransaction.add(R.id.ly_content,fg1);
                }else{
                    fTransaction.show(fg1);
                }
                break;
            case R.id.txt_task:
                setSelected();
                txt_task.setSelected(true);
                if(fg2 == null){
                    fg2 = new MyFragment("第二个Fragment");
                    fTransaction.add(R.id.ly_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
                break;
            case R.id.txt_alert:
                setSelected();
                txt_alert.setSelected(true);
                if(fg3 == null){
                    fg3 = new MyFragment("第三个Fragment");
                    fTransaction.add(R.id.ly_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                break;
            case R.id.txt_comment:
                setSelected();
                txt_comment.setSelected(true);
                if(mg4 == null){
                    mg4 = new message();
                    fTransaction.replace(R.id.ly_content,mg4);

                    System.out.println("jhfjf");
                }else{
                    fTransaction.show(mg4);
                }
                break;
            case R.id.txt_my:
                setSelected();
                txt_my.setSelected(true);
                if (user_center==null){
                    user_center=new UserCenterFragment();
                    fTransaction.replace(R.id.ly_content,user_center);
                }else {
                    fTransaction.show(user_center);
                }
                break;

        }
        fTransaction.commit();
    }




}
