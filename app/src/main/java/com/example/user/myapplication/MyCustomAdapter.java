package com.example.user.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by User on 5/17/2015.
 */
public class MyCustomAdapter<T> extends BaseAdapter{


    private List<T> itemList;
    private Context context;


    public MyCustomAdapter(Context context, List<T> objects) {
        super();
        this.context = context;
        itemList = objects;

    }


    public void addItem(T item){
        itemList.add(item);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);



        return rowView;
    }
}
