package com.example.lovecominghome.Fragment;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import android.app.Fragment;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.lovecominghome.Activity.DetailActivity;
import com.example.lovecominghome.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindFragment extends Fragment {
    ActionBar actionBar; //声明ActionBar

    private String content;
    public FindFragment() {

    }
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setHasOptionsMenu(true);
//
//        getActivity().setTitle("寻人");
//    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        menu.clear();
//        super.onCreateOptionsMenu(menu, inflater);
//
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find,container,false);
//        TextView txt_content = view.findViewById(R.id.txt_content);
//        txt_content.setText(content);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        int[] miss_imageid = new int[]{R.mipmap.miss_1,R.mipmap.miss_2,R.mipmap.miss_3};
        String[] miss_title = new String[]{"求助","帮忙","寻人"};
        String[] miss_name = new String[]{"aa","bb","cc"};
        String[] miss_age = new String[]{"11","22","33333"};
        String[] miss_birthplace = new String[]{"北京","上海","成都"};
        String[] miss_date = new String[]{"2020-1-9","2020-7-4","2020-5-3"};
        String[] miss_place = new String[]{"超市","公园","广场"};

        List<Map<String,Object>> listitem = new ArrayList<Map<String,Object>>();
        for(int i =0 ;i<miss_imageid.length;i++){
            Map<String,Object> map= new HashMap<String, Object>();
            map.put("image",miss_imageid[i]);
            map.put("title",miss_title[i]);
            map.put("name","姓名:"+miss_name[i]);
            map.put("age","年龄:"+miss_age[i]);
            map.put("birthplace","籍贯"+miss_birthplace[i]);
            map.put("date","失踪时间"+miss_date[i]);
            map.put("place","失踪地点"+miss_place[i]);
            listitem.add(map);
        }
//
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),listitem,R.layout.missing_list,new String[]{"title","image","name","age","birthplace","date","place"},new int[]{R.id.miss_title,R.id.miss_image,R.id.miss_name,R.id.miss_age,R.id.miss_birthplace,R.id.miss_date,R.id.miss_place});
        ListView listView = getView().findViewById(R.id.listview);
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new MyListener());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object> map = (Map<String,Object>)parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putCharSequence("name",map.get("name").toString());
                intent.putExtras(bundle);
                startActivity(intent);
                System.out.println(id);
                Toast.makeText(getActivity(), map.get("name").toString(), Toast.LENGTH_LONG).show();
            }
        });


        Spinner spinner = (Spinner)getActivity().findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String result = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
