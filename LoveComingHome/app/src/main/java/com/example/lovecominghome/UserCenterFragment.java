package com.example.lovecominghome;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class UserCenterFragment extends Fragment{
    public UserCenterFragment() {

    }

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
