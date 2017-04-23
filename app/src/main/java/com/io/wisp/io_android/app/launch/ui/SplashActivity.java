package com.io.wisp.io_android.app.launch.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.io.wisp.io_android.MainActivity;
import com.io.wisp.io_android.R;
import com.io.wisp.io_android.app.launch.ui.utils.Utils;
import com.io.wisp.io_android.global.Constants;

/**
 * Created by sunever on 2017/4/23.
 */

public class SplashActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean notFirstOpen = Utils.getBoolean(this, Constants.FIRST_OPEN);
        if (!notFirstOpen) {
            Intent intent = new Intent(this, LaunchActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                enterHomeActivity();
            }
        }, 2000);
    }

    private void enterHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
