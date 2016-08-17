package com.example.administrator.mylazylist;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private LazyAdapter adapter;
    private Object mString;
    private Button button;

    public static int REQUEST_CODE_SOME_FEATURES_PERMISSIONS = 1;

    private String[] mStrings = PicConstanct.getPicUrl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hasCallPhonePermission = checkSelfPermission(Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS);
            List<String> permissions = new ArrayList<String>();
            if (hasCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.INTERNET);
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            } else {

            }

            if (!permissions.isEmpty()) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), REQUEST_CODE_SOME_FEATURES_PERMISSIONS);
            }
        } else {//小于6.0

        }

        initView(); //初始化控件
        adapter = new LazyAdapter(mStrings, this);
        list.setAdapter(adapter);
        setLintener(); //设置监听器
    }

    /**
     * 6.0添加权限
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.i("lazy", "permissions.length: "+permissions.length);
        switch (requestCode) {
            case 1: {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Log.e("TTT", "Permissions --> " + "Permission Granted: " + permissions[i]);
                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        Log.e("TTT", "Permissions --> " + "Permission Denied: " + permissions[i]);
                    }
                }
            }
            break;
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    private void initView() {
        list = (ListView) findViewById(R.id.list);
        button = (Button) findViewById(R.id.button1);
    }


    @Override
    public void onDestroy() {
        list.setAdapter(null);
        super.onDestroy();
    }

    private void setLintener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("lazy", "onClick: " + this.toString());
                adapter.getImageLoader().clear();
                adapter.notifyDataSetChanged();
            }
        });
    }
}
