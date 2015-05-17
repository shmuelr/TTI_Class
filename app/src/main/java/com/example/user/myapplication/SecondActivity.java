package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class SecondActivity extends AppCompatActivity {

    public static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        if(intent.hasExtra(Tools.MY_INTENT_KEY)){
            String userInputFromOtherActivity = intent.getStringExtra(Tools.MY_INTENT_KEY);

            setResult(RESULT_OK);

            intent.putExtra(Tools.MY_RETURN_KEY, "Some data");
            finish();
        }


        String myData = intent.getStringExtra(Intent.EXTRA_TEXT);


        if(myData != null){

            Toast.makeText(this,"You gave me:"+ myData, Toast.LENGTH_LONG).show();

        }


    }


    public void onClickGoBack(View buttonThatWasClicked){
        Log.d(TAG, "Go Back Button clicked!");

        Intent data = getIntent();
        data.putExtra("returnKey", "some value that we got from wherever");

        switch (buttonThatWasClicked.getId()){
            case R.id.buttonGoBackOK:

                setResult(RESULT_OK, data);

                break;

            case R.id.buttonGoBackCancel:

                setResult(RESULT_CANCELED, data);

                break;


        }


        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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
