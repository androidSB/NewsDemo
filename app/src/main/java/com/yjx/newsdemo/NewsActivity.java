package com.yjx.newsdemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Intent intent = getIntent();
        intent.setAction("android.intent.action.VIEW");
        String urlData = intent.getStringExtra("content_url");
        Uri uri = Uri.parse(urlData);
        intent.setData(uri);
        startActivity(intent);
    }
}
