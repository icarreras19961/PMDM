package com.example.asteriods;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private final Activity activity;
    private final List<String> list;

    public MyAdapter(Activity activity, List<String> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    public View getView(int position, View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater =
                activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_element,
                null, true);
        TextView textView
                = (TextView) view.findViewById(R.id.title);
        textView.setText(list.get(position));
        ImageView
                imageView = (ImageView) view.findViewById(R.id.icon);
        switch (Math.round((float) Math.random() * 3)) {
            case 0:
                imageView.setImageResource(R.drawable.asteroide1);

                break;
            case 1:
                imageView.setImageResource(R.drawable.asteroide2);
                break;
            default:
                imageView.setImageResource(R.drawable.asteroide3);
                break;
        }
        return view;
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int arg0) {
        return list.get(arg0);
    }

    public long getItemId(int position) {
        return position;
    }
}