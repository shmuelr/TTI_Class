package com.example.user.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 5/17/2015.
 */
public class MyCustomAdapter<T extends MyCustomAdapter.AdapterInterface> extends BaseAdapter{

    private static final String TAG = "MyAdapter";

    static class ViewHolder{
        TextView textView1;
        TextView textView2;
    }



    private List<T> itemList;
    private Context context;


    public MyCustomAdapter(Context context) {
        super();
        this.context = context;
        itemList = new ArrayList<>();

    }



    public void addItem(T item){
        itemList.add(item);
        notifyDataSetChanged();
    }

    public List<T> getItemList(){
        return itemList;
    }

    public void clearList(){
        itemList.clear();
        notifyDataSetChanged();
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
        Log.i(TAG, "convert view is null - " + (convertView==null));



        ViewHolder myViewHolder;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            myViewHolder = new ViewHolder();
            myViewHolder.textView1 =(TextView)convertView.findViewById(R.id.textView1);
            myViewHolder.textView2 =(TextView)convertView.findViewById(R.id.textView2);

            convertView.setTag(myViewHolder);

        }else{

            myViewHolder = (ViewHolder) convertView.getTag();

        }






        T item = itemList.get(position);

        myViewHolder.textView1.setText(item.getText1());
        myViewHolder.textView2.setText(item.getText2());


        return convertView;
    }




    public interface AdapterInterface{
        public String getText1();
        public String getText2();
    }

}
