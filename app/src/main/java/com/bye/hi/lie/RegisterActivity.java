package com.bye.hi.lie;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bye.hi.lie.Request.ChkidRequest;
import com.bye.hi.lie.Request.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private boolean validate = false;
    private ArrayAdapter adapter;
    private Spinner spinner;
    ArrayAdapter<CharSequence> adspin1, adspin2;
    String choice_do="";
    String choice_se="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = (Spinner) findViewById(R.id.Jobtxt);
        adapter = ArrayAdapter.createFromResource(this, R.array.Jobtxt, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        adspin1 = ArrayAdapter.createFromResource(this, R.array.CgName, android.R.layout.simple_spinner_dropdown_item);

        final EditText IDtxt = (EditText) findViewById(R.id.IDtxt);
        final EditText PWtxt = (EditText) findViewById(R.id.PWtxt);
        final EditText PWtxtchk = (EditText) findViewById(R.id.PWtxtchk);
        final EditText Nametxt = (EditText) findViewById(R.id.Nametxt);
        //final EditText CGNumbur = (EditText) findViewById(R.id.CGNumbur);

        final Spinner cgName = (Spinner)findViewById(R.id.cgName);
        final Spinner certName = (Spinner)findViewById(R.id.certName);

        final Button overlDButton = (Button) findViewById(R.id.overlDButton);

        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cgName.setAdapter(adspin1);
        cgName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adspin1.getItem(i).equals("컴퓨터")) {
                    choice_do = "컴퓨터";
                    adspin2 = ArrayAdapter.createFromResource(RegisterActivity.this, R.array.cert_computer, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    certName.setAdapter(adspin2);
                    certName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("사이버")) {
                    choice_do = "사이버";
                    adspin2 = ArrayAdapter.createFromResource(RegisterActivity.this, R.array.cert_compsecu, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    certName.setAdapter(adspin2);
                    certName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("소방")) {
                    choice_do = "소방";
                    adspin2 = ArrayAdapter.createFromResource(RegisterActivity.this, R.array.cert_firefight, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    certName.setAdapter(adspin2);
                    certName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("전기")) {
                    choice_do = "전기";
                    adspin2 = ArrayAdapter.createFromResource(RegisterActivity.this, R.array.cert_electric, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    certName.setAdapter(adspin2);
                    certName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        overlDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = IDtxt.getText().toString();
                if (validate) {
                    return;
                }

                if (userID.replace(" ", "").equals(""))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("아이디는 빈 칸일 수 없습니다.")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("사용할 수 있는 아이디입니다.")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();
                                IDtxt.setEnabled(false);
                                validate = true;
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("사용할 수 없는 아이디입니다.")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                ChkidRequest ChkidRequest = new ChkidRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(ChkidRequest);
            }
        });

        PWtxtchk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = PWtxt.getText().toString();
                String confirm = PWtxtchk.getText().toString();

                if( password.equals(confirm) ) {
                    PWtxt.setBackgroundColor(Color.GREEN);
                    PWtxtchk.setBackgroundColor(Color.GREEN);
                } else {
                    PWtxt.setBackgroundColor(Color.RED);
                    PWtxtchk.setBackgroundColor(Color.RED);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if( PWtxt.getText().toString().length() < 6 ) {
            Toast.makeText(RegisterActivity.this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
            PWtxtchk.requestFocus();
        }

        // 비밀번호 일치 확인
        if( !PWtxt.getText().toString().equals(PWtxtchk.getText().toString()) ) {
            Toast.makeText(RegisterActivity.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
            PWtxt.setText("");
            PWtxtchk.setText("");
            PWtxt.requestFocus();

        }



        {

            Button registerMemberButton = (Button) findViewById(R.id.registerMemberButton);


            registerMemberButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userID = IDtxt.getText().toString();
                    String userPW = PWtxt.getText().toString();
                    String userName = Nametxt.getText().toString();
                    String userJob = spinner.getSelectedItem().toString();
                    String cgName = spinner.getSelectedItem().toString();
                    String certName = spinner.getSelectedItem().toString();
              //    int cgNum = Integer.parseInt(CGNumbur.getText().toString());

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("회원등록완료")
                                            .setPositiveButton("확인", null)
                                            .create()
                                            .show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    RegisterActivity.this.startActivity(intent);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("회원등록실패")
                                            .setNegativeButton("다시시도", null)
                                            .create()
                                            .show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    };
                    RegisterRequest registerRequest = new RegisterRequest(userID, userPW, userName, userJob, cgName, certName, /* cgNum ,*/ responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);
                }
            });
        }
    }

}
