package com.bye.hi.lie;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class Learn extends Activity implements View.OnClickListener {

    private Button mCancel, mWrittenbtn, mPracticebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_learn);

        setContent();
    }

    private void setContent() {
        mCancel = (Button) findViewById(R.id.CancelBtn);
        mWrittenbtn = (Button) findViewById(R.id.written);
        mPracticebtn = (Button) findViewById(R.id.practice);

        mCancel.setOnClickListener(this);
        mWrittenbtn.setOnClickListener(this);
        mPracticebtn.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CancelBtn:
                Intent intent = new Intent(Learn.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.written:
                Intent intent0 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ckdaud001.cafe24.com/read.pdf"));
                startActivity(intent0);
                break;
            case R.id.practice:
                Intent intent1 = new Intent(Learn.this,Practice_menu.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}