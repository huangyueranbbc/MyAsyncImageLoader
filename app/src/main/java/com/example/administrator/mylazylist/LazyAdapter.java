package com.example.administrator.mylazylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/8/16.
 */
public class LazyAdapter extends BaseAdapter {

    private Context context;
    private String[] data;
    private static LayoutInflater inflater = null;
    private ImageLoader imageLoader;

    public LazyAdapter(String[] data, Context context) {
        this.data = data;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(context.getApplicationContext());
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.item, null);

        TextView text = (TextView) vi.findViewById(R.id.text);
        ;
        ImageView image = (ImageView) vi.findViewById(R.id.image);
        text.setText("item " + position);
        imageLoader.DisplayImage(data[position], image);
        return vi;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
