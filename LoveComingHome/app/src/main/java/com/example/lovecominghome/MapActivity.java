package com.example.lovecominghome;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.*;

public class MapActivity extends AppCompatActivity {
    TextView locationInfo;
    LocationClient mLocationClient;

    MapView mMapView;
    BaiduMap mBaiduMap = null;

    boolean isFirstLocate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.map_main);

        locationInfo = findViewById(R.id.locationInfo);

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());

        mMapView = findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);

        mBaiduMap.setMyLocationEnabled(true);

        List<String> permissionList = new ArrayList<String>();

        if(ContextCompat.checkSelfPermission(MapActivity.this, permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(MapActivity.this, permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(MapActivity.this, permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MapActivity.this, permissions, 1);
        }else{
            requestLocation();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length > 0){
                    for(int result :grantResults){
                        if(result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else{
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
            break;
        }
    }

    private void requestLocation(){
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        /**
         * 可选，设置定位模式，默认高精度
         * LocationMode.High_Accuracy: 高精度;
         * LocationMode.Battery_Saving: 低功耗
         * LocationMode.Device_Sensors: 仅使用设备
         */
        option.setCoorType("bd09ll");
        /**
         * 可选，设置返回经纬度坐标类型，默认GCJ02
         * GCJ02：国测局坐标
         * BD09ll：百度经纬度坐标
         * BD09：百度墨卡托坐标
         * 海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标
         */
        option.setScanSpan(1000);
        /**
         * 可选，设置发起定位请求的间隔，int类型，单位ms
         * 如果设置0，则代表单词定位，即仅定位一次，默认为0
         * 如果设为非0，需设置1000ms以上才有效
         */
        option.setOpenGps(true);
        /**
         * 可选，设置是否使用gps，默认false
         * 使用高精度和仅使用设备两种定位模式的，参数必须设为true
         */
        option.setLocationNotify(true);
        /**
         * 可选，设置是否当GPS有效时按照1s/1次频率输出GPS结果，默认false
         */
        option.setIgnoreKillProcess(false);
        /**
         * 可选，定位SDK内部是一个service，并放到了独立进程
         *设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即true
         */
        option.SetIgnoreCacheException(false);
        /**
         * 可选，设置是否收集Crash信息，默认收集，即参数为false
         */
        option.setWifiCacheTimeOut(5 * 60 * 1000);
        /**
         * 可选，V7.2版本新增功能
         * 如果设置了该接口，首次启动定位时，会先判断当前Wi-fi是否超出有效期，若超出有效期，会先重新扫描Wi-fi，然后定位
         */
        option.setEnableSimulateGps(false);
        /**
         * 可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
         */
        option.setIsNeedAddress(true);

        mLocationClient.setLocOption(option);
    }

    private class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            navigateTo(bdLocation);
            /*
            StringBuilder currentPosition = new StringBuilder();
            currentPosition.append("维度：").append(bdLocation.getLatitude()).append("\n");
            currentPosition.append("经度：").append(bdLocation.getLongitude()).append("\n");
            currentPosition.append("国家：").append(bdLocation.getCountry()).append("\n");
            currentPosition.append("省：").append(bdLocation.getProvince()).append("\n");
            currentPosition.append("市：").append(bdLocation.getCity()).append("\n");
            currentPosition.append("区：").append(bdLocation.getDistrict()).append("\n");
            currentPosition.append("村镇：").append(bdLocation.getTown()).append("\n");
            currentPosition.append("街道：").append(bdLocation.getStreet()).append("\n");
            currentPosition.append("地址：").append(bdLocation.getAddrStr()).append("\n");
            currentPosition.append("定位方式");
            if(bdLocation.getLocType() == BDLocation.TypeGpsLocation){
                currentPosition.append("GPS");
            }else if(bdLocation.getLocType() == BDLocation.TypeNetWorkLocation){
                currentPosition.append("网络");
            }
            locationInfo.setText(currentPosition);
            */

        }
    }

    private void navigateTo(BDLocation bdLocation){
        if(isFirstLocate){
            LatLng ll = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            mBaiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            mBaiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.longitude(bdLocation.getLongitude());
        locationBuilder.latitude(bdLocation.getLatitude());
        MyLocationData locationData = locationBuilder.build();
        mBaiduMap.setMyLocationData(locationData);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }
}
