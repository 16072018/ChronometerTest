package com.example.auclo.chronometertest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

public class FirstFragment extends Fragment {

    private Activity activity;

    public static FirstFragment newInstance(Boolean flag) {

        Bundle args = new Bundle();
        args.putBoolean("flag", flag);

        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        if (context instanceof Activity) {
            this.activity = (Activity) context;
        }
    }

    Chronometer chronometer;
    Button testButton;
    StopWatchHelper stopWatchHelper;


    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        chronometer = view.findViewById(R.id.stop_watch);
        testButton = view.findViewById(R.id.btn_test);

        // 크로노미터 설정
        chronometer.setFormat("%s");
        chronometer.setBase(SystemClock.elapsedRealtime());

        stopWatchHelper = new StopWatchHelper();

        if( getArguments() == null ){
            startStopWatch();
        }

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stopStopWatch();
                ((BottomNavigationView) activity.findViewById(R.id.bottom_navigation)).setSelectedItemId(R.id.nav_second_fragment);

            }
        });

        view.findViewById(R.id.btnStart)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startStopWatch();
                    }
                });

        return view;
    }
// ========================================= 메서드들 ===============================================
    // 스탑워치 시작
    private void startStopWatch() {

        // 스탑워치 헬퍼 클라스 객체 생성


        // 만약 Helper 클라스의 getStartTime()이 null이라면
        if (stopWatchHelper.getStartTime() == null) {

            // 시스템 시간 (00:00)을 chronometer의 베이스 시간으로 설정
            long startTime = SystemClock.elapsedRealtime();

            // 방금 설정한 startTime을 setStart() 메서드에 적용
            stopWatchHelper.setStartTime(startTime);
            chronometer.setBase(startTime);

            // null이 아닌 다른 값이 있다면
        } else {

            // chronometer의 베이스 시간을 Helper 클라스의 getStartTime()을 이용하여 설정
            chronometer.setBase(stopWatchHelper.getStartTime());

        }

        chronometer.start();

    }

    // 스탑워치 정지
    private void stopStopWatch() {

        stopWatchHelper = new StopWatchHelper();
        long startTime = SystemClock.elapsedRealtime();
        stopWatchHelper.setStartTime(startTime);
        chronometer.setBase(startTime);
        chronometer.stop();

    }

}


