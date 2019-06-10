package com.bye.hi.lie;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class Mypage extends AppCompatActivity {


    ArrayAdapter<CharSequence> adspin1, adspin2;
    String choice_do = "";
    String choice_se = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        final Spinner spin1 = (Spinner) findViewById(R.id.cgName);
        final Spinner spin2 = (Spinner) findViewById(R.id.certName);
        Button btn_refresh = (Button) findViewById(R.id.editButton);
        adspin1 = ArrayAdapter.createFromResource(this, R.array.CgName, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adspin1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //툴바 사용여부 결정(기본적으로 사용)
        if(useToolbar()){
            setSupportActionBar(toolbar);
            setTitle("lie");
        } else {
            toolbar.setVisibility(View.GONE);
        }
        


        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adspin1.getItem(i).equals("컴퓨터")) {
                    choice_do = "컴퓨터";
                    adspin2 = ArrayAdapter.createFromResource(Mypage.this, R.array.cert_computer, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    adspin2 = ArrayAdapter.createFromResource(Mypage.this, R.array.cert_compsecu, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    adspin2 = ArrayAdapter.createFromResource(Mypage.this, R.array.cert_firefight, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    adspin2 = ArrayAdapter.createFromResource(Mypage.this, R.array.cert_electric, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Mypage.this, "수정되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Mypage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean useToolbar() {
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_back:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_logout:
                Intent intent2 = new Intent(this, LoginActivity.class);
                startActivity(intent2);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
