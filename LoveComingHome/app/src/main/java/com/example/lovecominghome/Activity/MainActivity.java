package com.example.lovecominghome.Activity;

import android.Manifest;
import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;

import com.example.lovecominghome.Fragment.FindFragment;
import com.example.lovecominghome.Fragment.UserCenterFragment;
import com.example.lovecominghome.Fragment.IndexFragment;
import com.example.lovecominghome.R;


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
    private IndexFragment indexFragment;
    private FindFragment findFragment;
    private FindFragment mg3,mg4;
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main,menu);
//        return super.onCreateOptionsMenu(menu);
//    }

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
        if(indexFragment != null)fragmentTransaction.hide(indexFragment);
        if(findFragment != null)fragmentTransaction.hide(findFragment);
        if(mg3 != null)fragmentTransaction.hide(mg3);
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
                if(indexFragment == null){
                    indexFragment = new IndexFragment();
                    fTransaction.add(R.id.ly_content,indexFragment);

                }else{
                    fTransaction.show(indexFragment);
                }
                break;
            case R.id.txt_task:
                setSelected();
                txt_task.setSelected(true);
                if(findFragment == null){
                    findFragment = new FindFragment();
                    fTransaction.add(R.id.ly_content,findFragment);

                }else{
                    fTransaction.show(findFragment);
                }

                break;
            case R.id.txt_alert:
                setSelected();
                txt_alert.setSelected(true);
                if(mg3 == null){
                    mg3 = new FindFragment();
                    fTransaction.add(R.id.ly_content,mg3);

                }else{
                    fTransaction.show(mg3);

                }
                break;
            case R.id.txt_comment:
                setSelected();
                txt_comment.setSelected(true);
                if(mg4 == null){
                  mg4 = new FindFragment();
                    fTransaction.add(R.id.ly_content,mg4);

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
                    fTransaction.add(R.id.ly_content,user_center);
                }else {
                    fTransaction.show(user_center);
                }
                break;

        }
        fTransaction.commit();
    }




}
