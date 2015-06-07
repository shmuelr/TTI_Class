package com.example.user.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 6/7/2015.
 */
public class MyRecylerAdapter extends RecyclerView.Adapter<MyRecylerAdapter.MyRecViewHolder> {

    private static final List<String> itemList = new ArrayList<>();
    
    static {
        itemList.add("Hello");
        itemList.add("World");
    }


    static class MyRecViewHolder extends RecyclerView.ViewHolder{

        TextView textView1;
        TextView textView2;

        public MyRecViewHolder(View itemView) {
            super(itemView);

            textView1 = (TextView)itemView.findViewById(R.id.textView1);
            textView2 = (TextView)itemView.findViewById(R.id.textView2);

        }
    }


    @Override
    public MyRecViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {

        LayoutInflater inflater = (LayoutInflater) viewGroup.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item, viewGroup, false);

        MyRecViewHolder recViewHolder = new MyRecViewHolder(view);

        return recViewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecViewHolder myRecViewHolder, int position) {

        myRecViewHolder.textView1.setText(itemList.get(position));

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
