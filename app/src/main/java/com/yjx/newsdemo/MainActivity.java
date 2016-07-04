package com.yjx.newsdemo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String news_url = "http://localhost:8080/NewsDemo/getNewsJSON.php";
    private ListView listView;
    private NewsAdapter adapter;
    private List<News> list;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonData = (String) msg.obj;
            Log.e("MainActivity", jsonData);
            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    String desc = jsonObject.getString("desc");
                    String time = jsonObject.getString("time");
                    String content_url = jsonObject.getString("content_url");
                    String pic_url = jsonObject.getString("pic_url");
                    list.add(new News(title, desc, time, content_url, pic_url));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar();
        listView = (ListView) this.findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new NewsAdapter(this, list);
        listView.setAdapter(adapter);
        HttpUtils.getNewsJSON(news_url, handler);
        listView.setOnItemClickListener(new ListItemOnClick());
    }

    private void initActionBar() {
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setLogo(R.mipmap.ic_launcher);
    }

    private class ListItemOnClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            News news = list.get(position);
            Intent intent = new Intent(MainActivity.this, NewsActivity.class);
            intent.putExtra("content_url", news.getContent_url());
            startActivity(intent);
        }
    }
}
