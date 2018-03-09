package com.code.txtinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;

public class MainActivity extends AppCompatActivity {

    private TextView version_info, check_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check_update = findViewById(R.id.check_update);
        version_info = findViewById(R.id.version_info);
        check_update.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getUpdateInfo();
            }
        });
    }

    void getUpdateInfo() {
        UpgradeInfo upgradeInfo = Beta.getUpgradeInfo();

        if (upgradeInfo == null) {
            version_info.setText("无升级信息");
            return;
        }
        version_info.setText(JSON.toJSONString(upgradeInfo));

    }
}
