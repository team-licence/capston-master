package com.bye.hi.lie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView1 = (TextView) findViewById(R.id.textView1);
        EditText IDtxt = (EditText) findViewById(R.id.IDtxt);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        EditText PWtxt = (EditText) findViewById(R.id.PWtxt);
        TextView textView5 = (TextView) findViewById(R.id.textView5);

        Intent intent =getIntent();
        String userID = intent.getStringExtra("userID");
        String userPW = intent.getStringExtra("userPW");

        IDtxt.setText(userID);
        PWtxt.setText(userPW);


    }
}
