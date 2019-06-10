package com.bye.hi.lie;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.bye.hi.lie.TestData.TestView001;
import com.bye.hi.lie.TestData.TestView002;
import com.bye.hi.lie.TestData.TestView003;
import com.bye.hi.lie.TestData.TestView1;

public class Choice_Numturn extends AppCompatActivity {

    private Spinner choiceNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice__numturn);

        choiceNum = (Spinner) findViewById(R.id.choiceNum);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.numturn, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choiceNum.setAdapter(adapter);

        final TextView txtView = (TextView) findViewById(R.id.txtView);
        final Button choiceBtn = (Button)findViewById(R.id.choiceBtn);
    }
        public void onClick (View view){
            int position = choiceNum.getSelectedItemPosition();
            Intent intent = null;
            switch (position) {
                case 0:
                   // intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
                    break;
                case 1:
                    Intent intent1 = new Intent(Choice_Numturn.this, TestView001.class);
                    startActivity(intent1);
                    break;
                case 2:
                    Intent intent2 = new Intent(Choice_Numturn.this, TestView002.class);
                    startActivity(intent2);
                    break;
                case 3:
                    Intent intent3 = new Intent(Choice_Numturn.this, TestView003.class);
                    startActivity(intent3);
                    break;
                case 4:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
                    break;
                case 5:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
                    break;
            }
            if (intent != null) {
                startActivity(intent);
            }
        }

        public void exitClick(View view) {
            finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {                                                 //백버튼 누를시 Question
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                String alertTitle = "학습 종료";
                String buttonMessage = "학습을 종료하고 메인화면으로 가시겠습니까?";
                String buttonYes = "네, 메인화면으로 갈래요";
                String buttonNo = "아니요, 더 공부할래요";

                new AlertDialog.Builder(Choice_Numturn.this)
                        .setTitle(alertTitle)
                        .setMessage(buttonMessage)
                        .setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                Intent intent = new Intent(Choice_Numturn.this,MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(buttonNo, null)
                        .show();
        }
        return true;
    }
}
