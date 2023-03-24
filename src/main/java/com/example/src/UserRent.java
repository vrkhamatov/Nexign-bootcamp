package com.example.src;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class UserRent {

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


    public String getDurationString() {
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.toSeconds() % 60;
        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return time;
    }

    public String getEndTimeFormatted() {
        String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        String formatDateTime = endTime.format(formatter);
        return formatDateTime;
    }

    public String getStartTimeFormatted() {
        String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        String formatDateTime = startTime.format(formatter);
        return formatDateTime;
    }

    public String getNumber() {
        return number;
    }



    private String callType;
    private String number;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration duration;
    private String tariffType;
    private Double cost;

    public UserRent(CDRLine cdrLine) {
        this.callType = cdrLine.callType;
        this.number = cdrLine.number;

        this.startTime = cdrLine.startTime;
        this.endTime = cdrLine.endTime;
        this.tariffType = cdrLine.tariffType;
        this.duration = makeDurationFromTime(cdrLine);
        this.cost = makeCost(tariffType,duration,callType);
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

    public double makeCost(String tariffType, Duration duration, String callType){
        double cost;
        long sec = duration.getSeconds();;
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


    public LocalDateTime getStartTime() {
        return startTime;
    }
}
