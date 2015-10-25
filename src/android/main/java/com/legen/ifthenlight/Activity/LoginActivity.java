package com.legen.ifthenlight.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.legen.ifthenlight.Base.BaseActivity;
import com.legen.ifthenlight.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends BaseActivity {

    private Button btn_login;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initViews() {
        btn_login= (Button) findViewById(R.id.login_btn);
    }

    @Override
    public void initListeners() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                //sendMessage("b");

            }
        });
    }

    @Override
    public void initData() {

    }



}
