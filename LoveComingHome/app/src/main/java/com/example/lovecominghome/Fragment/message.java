package com.example.lovecominghome.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.app.Fragment;

import com.example.lovecominghome.R;

public class message extends Fragment {
    private String content;
    public message() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mg_content,container,false);
//        TextView txt_content = view.findViewById(R.id.txt_content);
//        txt_content.setText(content);
        return view;
    }
}
