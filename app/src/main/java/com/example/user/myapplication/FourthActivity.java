package com.example.user.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FourthActivity extends AppCompatActivity {

    private EditText editText;
    private ListView listView;
    private ImageView imageView;

    private static final String FILENAME = "data_file";


    private MyCustomAdapter<Student> myCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        editText = (EditText)findViewById(R.id.editText);
        listView = (ListView)findViewById(R.id.listView);
        imageView = (ImageView)findViewById(R.id.imageView);

        imageView.setImageResource(R.drawable.adobe);


        myCustomAdapter = new MyCustomAdapter<>(this);
        listView.setAdapter(myCustomAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Log.d("Tag", ((String) adapterView.getItemAtPosition(position)) + " was clicked!");

            }
        });

        listView.addHeaderView(this.getLayoutInflater().inflate(R.layout.list_header, null));
    }



    public void onClickClear(View view){
        myCustomAdapter.clearList();
    }


    public void onClickAddToList(View view){

        Student student = new Student(editText.getText().toString());

        myCustomAdapter.addItem(student);

        editText.setText("");
    }


    @Override
    protected void onResume() {
        super.onResume();

        loadData();
    }


    @Override
    protected void onPause() {
        super.onPause();

        saveData();
    }


    public void saveData() {

        Set<String> stringSet = new HashSet<>();

        for(MyCustomAdapter.AdapterInterface item : myCustomAdapter.getItemList()){
            stringSet.add(item.getText1());
        }

        SharedPreferences settings = this.getSharedPreferences(FILENAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet("saved_set", stringSet);

        // Commit the edits!
        editor.commit();



    }

    public void loadData() {

        SharedPreferences settings = this.getSharedPreferences(FILENAME, 0);
        Set<String> stringSet = settings.getStringSet("saved_set", new HashSet<String>());

        for (String string : stringSet){

            Student student = new Student(string);

            myCustomAdapter.addItem(student);
        }
    }

    @Override
     public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fourth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
