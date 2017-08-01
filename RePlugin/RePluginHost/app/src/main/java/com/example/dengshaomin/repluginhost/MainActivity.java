package com.example.dengshaomin.repluginhost;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissonItem;

public class MainActivity extends AppCompatActivity {

    // 外置插件相关
    private final static String PLUGIN_NAME = "com.example.dengshaomin.replugindemo";
    private final static String PLUGIN_APK = PLUGIN_NAME + ".apk";
    private final static String PLUGIN_PATH = "external" + File.separator + PLUGIN_APK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<PermissonItem> permissonItems = new ArrayList<PermissonItem>();
        permissonItems.add(new PermissonItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读写", R.drawable
                .permission_ic_memory));
        permissonItems.add(new PermissonItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "读写", R.drawable
                .permission_ic_location));
        HiPermission.create(MainActivity.this)
                .permissions(permissonItems)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
                        Log.e("code", "用户关闭权限申请");
                    }

                    @Override
                    public void onFinish() {
                        Log.e("code", "所有权限申请完成");
                    }

                    @Override
                    public void onDeny(String permisson, int position) {
                        Log.e("code", "onDeny");
                    }

                    @Override
                    public void onGuarantee(String permisson, int position) {
                        Log.e("code", "onGuarantee");
                    }
                });
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog pd = ProgressDialog.show(MainActivity.this, "Installing...", "Please wait...", true, true);
                // FIXME: 仅用于安装流程演示 2017/7/24
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        simulateInstallExternalPlugin(PLUGIN_NAME);
                        pd.dismiss();
                    }
                }, 1000);

            }
        });
        findViewById(R.id.text1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, PluginFragmentActivity.class));

            }
        });

//        findViewById(R.id.text1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RePlugin.install("/sdcard/exam.apk");
//            }
//        });

    }

    private void Log(String s) {
        Log.e("code", s);
    }

    private void simulateInstallExternalPlugin(String pluginName) {
        RePlugin.uninstall(pluginName);
        PluginInfo info = RePlugin.getPluginInfo(pluginName);
        if (info == null) {
            String pluginFilePath = getFilesDir().getAbsolutePath() + File.separator + PLUGIN_APK;
            File pluginFile = new File(pluginFilePath);
            if (!pluginFile.exists()) {
                copyAssetsFileToAppFiles(PLUGIN_PATH, PLUGIN_APK);
                if (pluginFile.exists()) {
                    info = RePlugin.install(pluginFilePath);
                }
            }
        }

        if (info != null) {
            RePlugin.startActivity(MainActivity.this, RePlugin.createIntent(info.getName(), "com.example.dengshaomin" +
                    ".replugindemo.MainActivity"));
        } else {
            Toast.makeText(MainActivity.this, "install external plugin failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void copyAssetsFileToAppFiles(String assetFileName, String newFileName) {
        InputStream is = null;
        FileOutputStream fos = null;
        int buffsize = 1024;

        try {
            is = this.getAssets().open(assetFileName);
            fos = this.openFileOutput(newFileName, Context.MODE_PRIVATE);
            int byteCount = 0;
            byte[] buffer = new byte[buffsize];
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
