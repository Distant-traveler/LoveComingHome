package com.example.lovecominghome.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.lovecominghome.Activity.ChangeInfomationActivity;
import com.example.lovecominghome.Entity.ItemView;
import com.example.lovecominghome.R;

public class UserCenterFragment extends Fragment{
    public UserCenterFragment() {

    }
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // 调用该方法后才会显示Menu
//        getActivity().setTitle("助爱回家");
//        setHasOptionsMenu(true);
//    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_center,container,false);
        ItemView change_information = view.findViewById(R.id.change_information);
//        txt_content.setText(content);
        change_information.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.change_information:
                        Intent intent = new Intent(getActivity(), ChangeInfomationActivity.class);
                        startActivity(intent);
                        break;
                    default:
                }
            }
        });
        return view;
    }

}
