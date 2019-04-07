package com.bye.hi.lie;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText IDtxt = (EditText) findViewById(R.id.IDtxt);
        final EditText PWtxt = (EditText) findViewById(R.id.PWtxt);
        final EditText Nametxt = (EditText) findViewById(R.id.Nametxt);
        final EditText Jobtxt = (EditText) findViewById(R.id.Jobtxt);
        final EditText CGNumbur = (EditText) findViewById(R.id.CGNumbur);

        Button registerMemberButton = (Button) findViewById(R.id.registerMemberButton);

        registerMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = IDtxt.getText().toString();
                String userPW = PWtxt.getText().toString();
                String userName = Nametxt.getText().toString();
                String userJob = Jobtxt.getText().toString();
                int cgNum = Integer.parseInt(CGNumbur.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("회원등록완료")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();
                                 Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                 RegisterActivity.this.startActivity(intent);
                            }
                            else
                                {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("회원등록실패")
                                        .setNegativeButton("다시시도", null)
                                        .create()
                                        .show();
                            }

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }

                };
                RegisterRequest registerRequest = new RegisterRequest(userID, userPW, userName, userJob, cgNum, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}
