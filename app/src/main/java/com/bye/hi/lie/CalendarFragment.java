package com.bye.hi.lie;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bye.hi.lie.decorators.EventDecorator;
import com.bye.hi.lie.decorators.OneDayDecorator;
import com.bye.hi.lie.decorators.SaturdayDecorator;
import com.bye.hi.lie.decorators.SundayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalendarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 **/

public class CalendarFragment extends android.support.v4.app.Fragment {

    String time,kcal,menu;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    Cursor cursor;

    MaterialCalendarView materialCalendarView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static String userID;
    private ListView calendarListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CalendarFragment() {
        // Required empty public constructor
    }


    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarListView = (ListView)v.findViewById(R.id.calendarListView);
        noticeList = new ArrayList<Notice>();
        noticeList.add(new Notice("정보처리산업기사필기[시험]","큐넷","2019-03-28"));
        noticeList.add(new Notice("정보처리기사실기[신청]","큐넷","2019-07-01 ~ 2019-07-05"));
        noticeList.add(new Notice("정보처리산업기사실기[시험]","큐넷","2019-08-29"));
        noticeList.add(new Notice("정보보안기사실기[신청]","한국진흥원","2019-09-12 ~ 2019-09-16" ));
        adapter = new NoticeListAdapter(getContext(),noticeList);
        calendarListView.setAdapter(adapter);



        materialCalendarView = (MaterialCalendarView)v.findViewById(R.id.calendarView);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2017, 0, 1)) // 달력의 시작
                .setMaximumDate(CalendarDay.from(2030, 11, 31)) // 달력의 끝
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                oneDayDecorator);

        String[] result = {"2019,03,28","2019,06,29", "2019,07,01","2019,07,02","2019,07,03","2019,07,04","2019,07,05","2019,08,29", "2019,09,12", "2019,09,13", "2019,09,14", "2019,09,15", "2019,09,16","2030,11,31" }; //2030,11,31 가짜 함수

        new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();


                String shot_Day = Year + "." + Month + "." + Day;

                Log.i("shot_Day test", shot_Day + "");
                materialCalendarView.clearSelection();

                Toast.makeText(getActivity(), shot_Day , Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] Time_Result;

        ApiSimulator(String[] Time_Result){
            this.Time_Result = Time_Result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -3);
            ArrayList<CalendarDay> dates = new ArrayList<>();

            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월 년,일은 그대로*/
            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string 을 int 로 변환
            for(int i = 0 ; i < Time_Result.length ; i ++){
                CalendarDay day = CalendarDay.from(calendar);
                String[] time = Time_Result[i].split(",");
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int dayy = Integer.parseInt(time[2]);

                dates.add(day);
                calendar.set(year,month-1,dayy);

            }
            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isRemoving()) {
                return;
            }

         materialCalendarView.addDecorator(new EventDecorator(Color.RED, calendarDays, getActivity()));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

}

