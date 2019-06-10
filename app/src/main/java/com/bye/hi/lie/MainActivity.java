package com.bye.hi.lie;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            noticeListView = (ListView) findViewById(R.id.noticeListView);
            noticeList = new ArrayList<Notice>();
            noticeList.add(new Notice("정보처리기사필기", "큐넷", "2019-03-28"));
            noticeList.add(new Notice("정보처리산업기사필기", "큐넷", "2019-03-28"));
            noticeList.add(new Notice("정보처리기사실기", "큐넷", "2019-06-29"));
            noticeList.add(new Notice("정보처리산업기사실기", "큐넷", "2019-06-29"));
            noticeList.add(new Notice("정보보안기사실기", "한국진흥원", "2019-09-12"));
            adapter = new NoticeListAdapter(getApplicationContext(), noticeList);
            noticeListView.setAdapter(adapter);



        final Button caledarButton = (Button) findViewById(R.id.caledarButton);
        final Button scheduleButton = (Button) findViewById(R.id.ScheduleButton);
        final Button statisicsButton = (Button) findViewById(R.id.statisticsButton);
        final LinearLayout notice = (LinearLayout) findViewById(R.id.notice);


        caledarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                caledarButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                statisicsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new CalendarFragment());
                fragmentTransaction.commit();
            }
        });

        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                caledarButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                statisicsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                Intent intent2 = new Intent(MainActivity.this,Studylist.class);
                startActivity(intent2);

            }
        });

        statisicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                caledarButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                statisicsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                Intent intent3 = new Intent(MainActivity.this,Learn.class);
                startActivity(intent3);
            }
        });
     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                Intent intent3 = new Intent(this, LoginActivity.class);
                startActivity(intent3);
                return true;

            case R.id.action_mypage:
               Intent intent4 = new Intent(this, Mypage.class);
               startActivity(intent4);
               return true;

            case R.id.action_back:
                Intent intent2 = new Intent(this, LoginActivity.class);
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "http://ckdaud001.cafe24.com/NoticeList.php";
        }


        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        public void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String noticeContent, noticeName, noticeDate;
                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    noticeContent = object.getString("noticeContent");
                    noticeName = object.getString("noticeContent");
                    noticeDate = object.getString("noticeContent");
                    Notice notice = new Notice(noticeContent, noticeName, noticeDate);
                    noticeList.add(notice);
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

   public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                String alertTitle = "종료";
                String buttonMessage = "어플을 종료하시겠습니까?";
                String buttonYes = "네";
                String buttonNo = "아니요";

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(alertTitle)
                        .setMessage(buttonMessage)
                        .setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                moveTaskToBack(true);
                                finish();
                            }
                        })
                        .setNegativeButton(buttonNo, null)
                        .show();
        }
        return true;
    }
}
