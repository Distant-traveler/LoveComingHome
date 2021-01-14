package com.example.lovecominghome.Activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lovecominghome.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TakePhotoActivity extends AppCompatActivity {
    private Camera camera;
    private boolean isPreview = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if(!android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){ //判断是否安装SD卡
            Toast.makeText(this,"请安装SD卡",Toast.LENGTH_SHORT).show();
            System.out.println("没有SD");
        }
        System.out.println("fsfsdaf");
        SurfaceView surfaceView = (SurfaceView)findViewById(R.id.sufaceView);
        final SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        ImageButton preview = (ImageButton) findViewById(R.id.preview);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPreview){
                    camera =Camera.open();
                    isPreview = true;
                }
                try {
                    camera.setPreviewDisplay(surfaceHolder);
                    Camera.Parameters parameters = camera.getParameters();

                    parameters.setPictureFormat(PixelFormat.JPEG);
                    parameters.set("jpeg-quality",80);

                    camera.setParameters(parameters);
                    camera.startPreview();
                    camera.autoFocus(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        ImageButton takePicture = (ImageButton) findViewById(R.id.takephoto);
        //实现拍照功能
        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(camera!=null){
                    Toast.makeText(TakePhotoActivity.this,"xxx",Toast.LENGTH_LONG).show();
                    camera.takePicture(null,null,jpeg);
                }
            }
        });

    }
    final Camera.PictureCallback jpeg = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);//根据拍照创建位图
            camera.stopPreview();
            isPreview=false;

            File appDir = new File(Environment.getExternalStorageDirectory(),"/DCIM/Camera");
            if(!appDir.exists()){
                appDir.mkdir();
            }
            String fileName = System.currentTimeMillis()+".jpg";
            File file = new File(appDir,fileName);

            try{
                FileOutputStream fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                MediaStore.Images.Media.insertImage(TakePhotoActivity.this.getContentResolver(),
                        file.getAbsolutePath(),fileName,null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            TakePhotoActivity.this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.parse("file://"+"")));
            Toast.makeText(TakePhotoActivity.this,"保存至"+file,Toast.LENGTH_LONG).show();

            resetCamera();
        }
    };

    private void resetCamera() {
        if(!isPreview){
            camera.startPreview();
            isPreview=true;
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        if(camera!=null){
            camera.stopPreview();
            camera.release();
        }
    }
}
