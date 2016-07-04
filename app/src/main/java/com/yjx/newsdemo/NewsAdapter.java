package com.yjx.newsdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class NewsAdapter extends BaseAdapter {
    private Context context;
    private List<News> list;

    public NewsAdapter(Context context, List<News> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.news_item, null);
        }
        ViewHolder holder = new ViewHolder();
        holder.news_title = (TextView) convertView.findViewById(R.id.news_title);
        holder.news_desc = (TextView) convertView.findViewById(R.id.news_desc);
        holder.news_time = (TextView) convertView.findViewById(R.id.news_time);
        holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
        holder.news_title.setText(list.get(position).getTitle());
        holder.news_desc.setText(list.get(position).getDesc());
        holder.news_time.setText(list.get(position).getTime());

        String imageUrl = list.get(position).getPic_url();
        HttpUtils.setImageBitmap(holder.imageView, imageUrl);
        return convertView;
    }

    class ViewHolder {
        TextView news_title;
        TextView news_desc;
        TextView news_time;
        ImageView imageView;
    }
}
