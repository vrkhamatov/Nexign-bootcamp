package com.example.src;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CDRLine {

    private final String callType;
    private final String number;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Duration duration;
    private final String tariffType;

    public Duration getDuration() {
        return duration;
    }

    public CDRLine(String callType, String number, LocalDateTime startTime, LocalDateTime endTime, String tariffType) {
        this.callType = callType;
        this.number = number;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tariffType = tariffType;
        this.duration = makeDurationFromTime(this);
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
        LocalTime forMidNight = LocalTime.of(23,59,59);
        LocalTime afterMidNight = LocalTime.of(0,0,0);
        Duration timeForMidnight;
        Duration timeAfterMidnight;
        Duration oneSec = Duration.ofSeconds(1);
        if (cdrLine.getStartTime().getDayOfMonth() != cdrLine.getEndTime().getDayOfMonth()){
            timeForMidnight = Duration.between(localTimeStart,forMidNight);
            timeForMidnight = timeForMidnight.plus(oneSec);
            timeAfterMidnight = Duration.between(afterMidNight,localTimeEnd);
            return timeForMidnight.plus(timeAfterMidnight);
        }
            else {
            return Duration.between(localTimeStart, localTimeEnd);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CDRLine)) return false;
        CDRLine cdrLine = (CDRLine) o;
        return Objects.equals(callType, cdrLine.callType) && Objects.equals(number, cdrLine.number) && Objects.equals(startTime, cdrLine.startTime) && Objects.equals(endTime, cdrLine.endTime) && Objects.equals(tariffType, cdrLine.tariffType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(callType, number, startTime, endTime, tariffType);
    }

    @Override
    public String toString() {
        return "CDRLine{" +
                "callType='" + callType + '\'' +
                ", number='" + number + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", tariffType='" + tariffType + '\'' +
                '}';
    }

    public String getTariffType() {
        return tariffType;
    }

    public LocalDateTime getEndTime() {
        return endTime;
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
        return this.number;
    }

    public String getCallType() {
        return this.callType;
    }






}
