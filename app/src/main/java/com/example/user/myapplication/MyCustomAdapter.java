package com.example.user.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 5/17/2015.
 */
public class MyCustomAdapter<T extends MyCustomAdapter.AdapterInterface> extends BaseAdapter{

    private static final String TAG = "MyAdapter";

    private List<T> itemList;
    private Context context;


    public MyCustomAdapter(Context context) {
        super();
        this.context = context;
        itemList = new ArrayList<>();

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


        Log.i(TAG, "getView() called at position " + position);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);


        TextView textView1 = (TextView)rowView.findViewById(R.id.textView1);



        TextView textView2 = (TextView)rowView.findViewById(R.id.textView2);


        T item = itemList.get(position);

        textView1.setText(item.getText1());
        textView2.setText(item.getText2());


        return rowView;
    }


    public interface AdapterInterface{
        public String getText1();
        public String getText2();
    }

}
