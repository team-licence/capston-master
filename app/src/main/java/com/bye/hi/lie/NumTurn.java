package com.bye.hi.lie;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class NumTurn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_turn);

        final CheckBox cb1 = (CheckBox)findViewById(R.id.checkBox1);
        final CheckBox cb2 = (CheckBox)findViewById(R.id.checkBox2);
        final CheckBox cb3 = (CheckBox)findViewById(R.id.checkBox3);
        final CheckBox cb4 = (CheckBox)findViewById(R.id.checkBox4);


        Button b = (Button)findViewById(R.id.button1);

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String result = "";  // 결과를 출력할 문자열  ,  항상 스트링은 빈문자열로 초기화 하는 습관을 가지자
                if(cb1.isChecked() == true) result += cb1.getText().toString();
                if(cb2.isChecked() == true) result += cb2.getText().toString();
                if(cb3.isChecked() == true) result += cb3.getText().toString();
                if(cb4.isChecked() == true) result += cb4.getText().toString();

            } // end onClick
        }); // end setOnClickListener

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {                                                 //백버튼 누를시 Question
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                String alertTitle = "학습 종료";
                String buttonMessage = "학습을 종료하고 메인화면으로 가시겠습니까?";
                String buttonYes = "네, 메인화면으로 갈래요";
                String buttonNo = "아니요, 더 공부할래요";

                new AlertDialog.Builder(NumTurn.this)
                        .setTitle(alertTitle)
                        .setMessage(buttonMessage)
                        .setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                Intent intent = new Intent(NumTurn.this,MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(buttonNo, null)
                        .show();
        }
        return true;
    }
}
