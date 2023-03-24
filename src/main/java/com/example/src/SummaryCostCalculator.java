package com.example.src;

import java.time.Duration;
import java.util.Objects;

public abstract class SummaryCostCalculator {
    public static double makeSummaryCost(String tariffType, Duration duration, String callType, long summaryDuration) {
        double cost;
        long sec = duration.getSeconds();
        long min = (int) Math.ceil(sec / 60);

        if (summaryDuration % 60 > 0) {
            summaryDuration = summaryDuration / 60 + 1;
        } else
            summaryDuration = summaryDuration / 60;

        if (sec % 60 != 0) {
            min += 1;
        }

        if (Objects.equals(tariffType, "03")) {
            cost = min * 1.5;
            return cost;
        } else if (Objects.equals(tariffType, "06")) {
            if (summaryDuration <= 300) {
                cost = 0.0;
            } else {
                cost = 100;
                cost = cost + min;
            }
        } else {
            if (Objects.equals(callType, "02")) {
                cost = 0;
            } else {
                if (summaryDuration <= 100) cost = min * 0.5;
                else
                    cost = 1.5 * (min);
            }
        }

        return cost;
    }
}
