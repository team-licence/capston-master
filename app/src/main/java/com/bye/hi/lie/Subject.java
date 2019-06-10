package com.bye.hi.lie;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bye.hi.lie.TestData.TestView1;
import com.bye.hi.lie.TestData.TestView2;
import com.bye.hi.lie.TestData.TestView3;
import com.bye.hi.lie.TestData.TestView4;
import com.bye.hi.lie.TestData.TestView5;

public class Subject extends AppCompatActivity {

    RadioButton cb1, cb2, cb3, cb4, cb5;
    RadioGroup radioGroup;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        cb1 = (RadioButton) findViewById(R.id.checkBox1);
        cb2 = (RadioButton) findViewById(R.id.checkBox2);
        cb3 = (RadioButton) findViewById(R.id.checkBox3);
        cb4 = (RadioButton) findViewById(R.id.checkBox4);
        cb5 = (RadioButton) findViewById(R.id.checkBox5);

        bt = (Button) findViewById(R.id.button1);

        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = null;

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.checkBox1:
                        Intent intent0 = new Intent(Subject.this,TestView1.class);
                        startActivity(intent0);
                        break;
                    case R.id.checkBox2:
                        Intent intent1 = new Intent(Subject.this,TestView2.class);
                        startActivity(intent1);
                        break;
                    case R.id.checkBox3:
                        Intent intent2 = new Intent(Subject.this,TestView3.class);
                        startActivity(intent2);
                        break;
                    case R.id.checkBox4:
                        Intent intent3 = new Intent(Subject.this,TestView4.class);
                        startActivity(intent3);
                        break;
                    case R.id.checkBox5:
                        Intent intent4 = new Intent(Subject.this,TestView5.class);
                        startActivity(intent4);
                        break;
                    default:
                }
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {                                                 //백버튼 누를시 Question
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                String alertTitle = "학습 종료";
                String buttonMessage = "학습을 종료하고 메인화면으로 가시겠습니까?";
                String buttonYes = "네, 메인화면으로 갈래요";
                String buttonNo = "아니요, 더 공부할래요";

                new AlertDialog.Builder(Subject.this)
                        .setTitle(alertTitle)
                        .setMessage(buttonMessage)
                        .setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                Intent intent = new Intent(Subject.this,MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(buttonNo, null)
                        .show();
        }
        return true;
    }
}