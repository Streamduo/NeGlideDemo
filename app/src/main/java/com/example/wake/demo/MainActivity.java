package com.example.wake.demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wake.demo.bean.Avatar;
import com.example.wake.demo.bean.AvatarDecorator;
import com.example.wake.demo.bean.BluePants;
import com.example.wake.demo.bean.RedHair;
import com.example.wake.demo.bean.Warrior;
import com.example.wake.demo.bean.WhiteShirt;
import com.example.wake.demo.neglide.NeGlide;
import com.example.wake.demo.neglide.PermissionUtils;
import com.example.wake.demo.neglide.RequestListener;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    /**
     * 权限申请
     */
    String[] permission = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private ScrollView scroAll;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Avatar avatar = new RedHair(new WhiteShirt(new BluePants(new AvatarDecorator(new Warrior()))));
        String describe = avatar.describe();
        TextView teDescribe = findViewById(R.id.te_describe);
        teDescribe.setText(describe);
        boolean checkResult = PermissionUtils.checkPermissionsGroup(this, permission);
        if (!checkResult) {
            PermissionUtils.requestPermissions(this, permission, PERMISSION_REQUEST_CODE);
        }
        scroAll = findViewById(R.id.scro_all);
        linearLayout = findViewById(R.id.line1);
        final ImageView imgSingle = findViewById(R.id.img_single);
        findViewById(R.id.btn_single).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NeGlide.with(MainActivity.this)
                        .loading(R.mipmap.ic_launcher)
                        .load("http://pic9.nipic.com/20100923/2531170_140325352643_2.jpg")
                        .listener(new RequestListener() {
                            @Override
                            public boolean onSuccess(Bitmap bitmap) {
                                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_LONG);
                                return false;
                            }

                            @Override
                            public boolean onFailure() {
                                return false;
                            }
                        })
                        .into(imgSingle);

            }
        });
        findViewById(R.id.btn_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMoreImg();
            }
        });
    }

    private void loadMoreImg() {
        for (int i = 1; i < 5; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            linearLayout.addView(imageView);
            String url = null;
            switch (i) {
                case 1:
                    url = "http://pic25.nipic.com/20121205/10197997_003647426000_2.jpg";
                    break;
                case 2:
                    url = "http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg";
                    break;
                case 3:
                    url = "http://pic40.nipic.com/20140331/9469669_142840860000_2.jpg";
                    break;
                case 4:
                    url = "http://pic25.nipic.com/20121112/9252150_150552938000_2.jpg";
                    break;
            }
            NeGlide.with(MainActivity.this)
                    .loading(R.mipmap.ic_launcher)
                    .load(url)
                    .into(imageView);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean isAllGranted = true;

            // 判断是否所有的权限都已经授予了
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }

            if (isAllGranted) {

            } else {
                // 弹出对话框告诉用户需要权限的原因, 并引导用户去应用权限管理中手动打开权限按钮

            }
        } else {

        }
    }

}
