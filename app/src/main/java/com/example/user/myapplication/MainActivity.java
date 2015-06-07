package com.example.user.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private final static String TAG = "Main Activity";

    private static final int SECOND_ACTIVITY_ID = 87;

    private static final String KEY_NAME = "key_name";

    private Button myButton;
    private Button secondButton;
    private EditText myEditText;
    private TextView myTextView;
    private TextView consoleTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpGUI();


    }

    public void saveString(String key, String value){
        SharedPreferences sharedPreferences = getSharedPreferences("Prefs",0);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, value);

        editor.apply();

    }

    public String getString(String key, String defaultValue){
        SharedPreferences sharedPreferences = getSharedPreferences("Prefs",0);

        return sharedPreferences.getString(key, defaultValue);

    }


    public void showCustomDialog(String title, String text){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title);
        builder.setMessage(text);
        builder.setPositiveButton("Yes!!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "Dialog button clicked!!");
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void setUpGUI() {
        myTextView = (TextView) findViewById(R.id.helloTextView);
        myEditText = (EditText) findViewById(R.id.editText);
        myButton = (Button) findViewById(R.id.button);
        secondButton = (Button) findViewById(R.id.secondButton);
        consoleTextView = (TextView) findViewById(R.id.consoleTextView);


        if(myButton == null){
            Log.e(TAG, "myButton is null!!");
        }


        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, RecyclerActivity.class));
            }
        });


        setUpFirstButton();

        setUpSecondButton();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void setUpFirstButton() {


        myButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String userInput = myEditText.getText().toString();

                        // This is an explicit intent
                        Intent myIntent = new Intent(MainActivity.this, SecondActivity.class);

                        myIntent.putExtra(Tools.MY_INTENT_KEY, userInput);


                        startActivityForResult(myIntent, R.id.activity_2_id);






                        //saveString(KEY_NAME, userInput);

                        //showCustomDialog("Save data", userInput + " was saved successfully!");


                        // This is an implicit intent
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, userInput);
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);

                        // This will be returned to the user via the Toast
                        String message;

                        /*if (userInput.isEmpty()) {
                            message = "Please put an operation";
                        } else {
                            message = "The result = " + operate(userInput);// add 3 4 => 7
                        }


                        myEditText.setText("");

                        consoleTextView.setText(message);*/

                    }
                }
        );
    }

    private void setUpSecondButton() {

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Second button clicked!");


               // Intent intent = new Intent(MainActivity.this, SecondActivity.class);

               // intent.putExtra("key", myEditText.getText().toString());

                //startActivity(intent);
                startActivity(new Intent(MainActivity.this, FourthActivity.class));

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == R.id.activity_2_id && resultCode == RESULT_OK){
            String someStringThatWasReturned = data.getStringExtra(Tools.MY_RETURN_KEY);

        }



        if(requestCode == SECOND_ACTIVITY_ID){

            if(resultCode == RESULT_OK){
                Log.d(TAG, "Second Activity was OK");
            }else if(resultCode == RESULT_CANCELED){
                Log.d(TAG, "Second Activity was Canceled");
            }

            Log.d(TAG,"Data is - "+data.getStringExtra("returnKey"));

        }

    }




    public int operate(String operation) {
        int result = 0;

        int a, b;


        return result;
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

        Log.d(TAG, "Saved String is " + getString(KEY_NAME, "Default String"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            // Do something
            Toast.makeText(this, "Settings pressed!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_menu_item_2){

            Toast.makeText(this, "Item 2 pressed!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
