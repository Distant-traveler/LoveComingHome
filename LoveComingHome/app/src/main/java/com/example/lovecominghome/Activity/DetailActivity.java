package com.example.lovecominghome.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lovecominghome.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        TextView tv_name = findViewById(R.id.name);
        tv_name.setText(name);

        //判断父activitiyshifou 是否为空
        if (NavUtils.getParentActivityName(DetailActivity.this)!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示向左的导航图标
        }
    }
}
