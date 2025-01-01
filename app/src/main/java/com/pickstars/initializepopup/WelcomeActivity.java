package com.pickstars.initializepopup;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;


public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Welcome), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });


        Button ResetRecord = findViewById(R.id.ResetRecord);
        ResetRecord.setOnClickListener(v -> {
            SharedPreferencesHelper.applyBoolean("FirstRun", false);
            finish();
        });
    }
}