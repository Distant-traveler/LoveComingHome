package com.example.lovecominghome.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import android.app.Fragment;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.lovecominghome.Activity.DetailActivity;
import com.example.lovecominghome.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexFragment extends Fragment {
    private String content;
    private SimpleAdapter adapter;

    private  Banner banner;
    private LocalImageLoader imageLoader;
    private  ArrayList<Integer> list_path;
    private ArrayList<String> list_title;

    public IndexFragment(){}
//    public IndexFragment(String content) {
//        this.content = content;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 调用该方法后才会显示Menu
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
System.out.println("显示目录");
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index,container,false);


//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,R.layout.activity_main);
//        ListView listView =(ListView) view.findViewById(R.id.listview);
//        listView.setAdapter(adapter);

//        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
//        txt_content.setText(content);
        return view;
    }
    private class LocalImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }
    public void initDate() {

        list_path = new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        list_path.add(R.mipmap.banner1);
        list_path.add(R.mipmap.banner2);
        list_path.add(R.mipmap.banner3);
        list_path.add(R.mipmap.tab_better_normal);
        list_title.add("好好学习");
        list_title.add("天天向上");
        list_title.add("热爱劳动");
        list_title.add("不搞对象");
        System.out.println(list_path);
        System.out.println(list_title);
    }

    public void initView(){
        System.out.println("43452525");
        imageLoader = new LocalImageLoader();
        banner = getView().findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(imageLoader);
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getActivity(),"点击了"+(position+1),Toast.LENGTH_LONG);
            }
        });

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        actionBar

        //轮播图
        initDate();
        initView();

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

        adapter = new SimpleAdapter(getActivity(),listitem,R.layout.missing_list,new String[]{"title","image","name","age","birthplace","date","place"},new int[]{R.id.miss_title,R.id.miss_image,R.id.miss_name,R.id.miss_age,R.id.miss_birthplace,R.id.miss_date,R.id.miss_place});
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



//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Map<String,Object> map = (Map<String,Object>)parent.getItemAtPosition(position);
//                Toast.makeText(getActivity(), map.get("name").toString(), Toast.LENGTH_LONG).show();
//            }
//        });

//        Button btn = (Button) getView().findViewById(R.id.detail_Btn);


//        String[] ctype = new String[]{"xx","cc","vv","bb"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,ctype);
//        ListView listView =(ListView) getView().findViewById(R.id.listview);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String object =(String) parent.getItemAtPosition(position);
//                Toast.makeText(getActivity(), object, Toast.LENGTH_SHORT).show();
//            }
//        });
//        getListView().setOnItemClickListener(this);
    }


//    class MyListener implements AdapterView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Map<String, Object> mMap = (Map<String, Object>) adapter.getItem(position);
//            Toast.makeText(getActivity(), mMap.get("name").toString(), Toast.LENGTH_LONG).show();
//            System.out.println(id);
//        }
//    }
}
