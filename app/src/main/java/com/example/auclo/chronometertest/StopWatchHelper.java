package com.example.auclo.chronometertest;

import android.support.annotation.Nullable;

public class StopWatchHelper {

    @Nullable
    private static Long startTime;

    @Nullable
    public Long getStartTime() {

        // 위에 선언된 startTime에 할당된 값을 리턴
        return startTime;

    }

    public void setStartTime(final long startTime) {

        // 위에 선언된 startTime을 parameter로 받은 startTime으로 업데이트
        this.startTime = startTime;

    }
}
