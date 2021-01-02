package com.example.lovecominghome.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lovecominghome.Activity.ChangeInfomationActivity;
import com.example.lovecominghome.Entity.ItemView;
import com.example.lovecominghome.R;

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
                        /*Intent intent = new Intent(getActivity(), ChangeInfomationActivity.class);
                        startActivity(intent);*/
                        /*Uri uri = Uri.parse("geo:39.9,116.3");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);*/
                        Uri uri = Uri.parse("http://maps.google.com/maps?f=d&saddr=39.9 116.3&daddr=31.2 121.4");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    default:
                }
            }
        });
        return view;
    }

}
