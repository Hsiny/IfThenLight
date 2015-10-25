package com.legen.ifthenlight.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.legen.ifthenlight.Base.BaseActivity;
import com.legen.ifthenlight.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_splash);
        new Timer().schedule(new TimerTask() {

            public void run() {
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                finish();
            }

        }, 2000);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData() {

    }
}
