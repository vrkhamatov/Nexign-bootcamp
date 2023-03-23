package com.example.src;

import java.time.Duration;
import java.time.LocalTime;

public class UserRent {

    @Override
    public String toString() {
        return "UserRent{" +
                "callType='" + callType + '\'' +
                ", number='" + number + '\'' +
                ", duration=" + duration +
                ", tariffType='" + tariffType + '\'' +
                ", Cost=" + Cost +
                '}';
    }

    private String callType;
    private String number;
    private Duration duration;
    private String tariffType;
    private float Cost;

    public UserRent(CDRLine cdrLine) {
        this.callType = cdrLine.callType;
        this.number = cdrLine.number;
        this.tariffType = cdrLine.tariffType;
        this.duration = makeDurationFromTime(cdrLine);
        this.tariffType = cdrLine.tariffType;

    }

    private Duration makeDurationFromTime(CDRLine cdrLine) {
        int hoursStart = cdrLine.startTime.getHour();
        int minutesStart = cdrLine.startTime.getMinute();
        int secondsStart = cdrLine.startTime.getSecond();

        int hoursEnd = cdrLine.endTime.getHour();
        int minutesEnd = cdrLine.endTime.getMinute();
        int secondsEnd = cdrLine.endTime.getSecond();

        LocalTime localTimeStart = LocalTime.of(hoursStart,minutesStart,secondsStart);
        LocalTime localTimeEnd = LocalTime.of(hoursEnd,minutesEnd,secondsEnd);

        Duration duration = Duration.between(localTimeStart,localTimeEnd);
        String hms = String.format("%02d:%02d:%02d",
                duration.toHours(),
                duration.toMinutesPart(),
                duration.toSecondsPart());

        return duration;
    }



}
