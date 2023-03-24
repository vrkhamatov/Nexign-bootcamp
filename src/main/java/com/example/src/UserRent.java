package com.example.src;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class UserRent {

    private final String callType;
    private final String number;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Duration duration;
    private final String tariffType;
    private final Double cost;

    public UserRent(CDRLine cdrLine) {
        this.callType = cdrLine.getCallType();
        this.number = cdrLine.getNumber();
        this.startTime = cdrLine.getStartTime();
        this.endTime = cdrLine.getEndTime();
        this.tariffType = cdrLine.getTariffType();
        this.duration = makeDurationFromTime(cdrLine);
        this.cost = makeCost(tariffType,duration,callType);
    }

    @Override
    public String toString() {

        return callType + '\'' +
                ", number='" + number + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", tariffType='" + tariffType + '\'' +
                ", cost=" + cost +
                '}';
    }

    public String getCallType() {
        return callType;
    }

    public Double getCost() {
        return cost;
    }

    public String getTariffType() {
        return tariffType;
    }

    public Duration getDuration() {
        return duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getDurationString() {
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.toSeconds() % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public String getEndTimeFormatted() {
        String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return endTime.format(formatter);
    }

    public String getStartTimeFormatted() {
        String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return startTime.format(formatter);
    }

    public String getNumber() {
        return number;
    }



    private Duration makeDurationFromTime(CDRLine cdrLine) {

        int hoursStart = cdrLine.getStartTime().getHour();
        int minutesStart = cdrLine.getStartTime().getMinute();
        int secondsStart = cdrLine.getStartTime().getSecond();

        int hoursEnd = cdrLine.getEndTime().getHour();
        int minutesEnd = cdrLine.getEndTime().getMinute();
        int secondsEnd = cdrLine.getEndTime().getSecond();

        LocalTime localTimeStart = LocalTime.of(hoursStart,minutesStart,secondsStart);
        LocalTime localTimeEnd = LocalTime.of(hoursEnd,minutesEnd,secondsEnd);

        return Duration.between(localTimeStart,localTimeEnd);
    }

    public double makeCost(String tariffType, Duration duration, String callType){
        double cost;
        long sec = duration.getSeconds();
        long min = (int)Math. ceil(sec/60);

        if (sec%60 != 0){
            min +=1;
        }

        if (Objects.equals(tariffType, "03")){
            cost = min * 1.5;
            return cost;
        }
        else if (Objects.equals(tariffType, "06")){
            if (min<=300) cost = 0;
            else
                cost = (min-300);
        }
        else
        {
            if (Objects.equals(callType, "02")){
                cost = 0;
            }
            else
            {
                if (min<=100) cost = min*0.5;
                else
                    cost = min*0.5 + 1.5*(min - 100);
            }
        }

        return cost;

    }


}
