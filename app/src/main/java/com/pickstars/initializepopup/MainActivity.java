package com.pickstars.initializepopup;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lxj.xpopup.XPopup;
import com.pickstars.initializepopup.Popup.ProtocolPopup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 判断是否为第一次启动
        if (!SharedPreferencesHelper.getBoolean("FirstRun", false)) {
            // 第一次启动，显示协议弹窗
            new XPopup.Builder(MainActivity.this)
                    .asCustom(new ProtocolPopup(MainActivity.this))
                    .show();
        } else {
            // 第二次启动，不显示引导页
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}