package com.bye.hi.lie;

import android.content.Intent;
import android.os.Bundle;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;


public class Studylist extends Activity implements  OnClickListener {

    private Button mCancel, mNumturnbtn, mSubjectbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_studylist);

        setContent();
    }

    private void setContent() {
        mCancel = (Button) findViewById(R.id.CancelBtn);
        mNumturnbtn = (Button) findViewById(R.id.numturnbtn);
        mSubjectbtn = (Button) findViewById(R.id.subjectbtn);

        mCancel.setOnClickListener(this);
        mNumturnbtn.setOnClickListener(this);
        mSubjectbtn.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CancelBtn:
                Intent intent = new Intent(Studylist.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.numturnbtn:
                Intent intent1 = new Intent(Studylist.this,Choice_Numturn.class);
                startActivity(intent1);
                this.finish();
                break;
            case R.id.subjectbtn:
                Intent intent2 = new Intent(Studylist.this,Subject.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}