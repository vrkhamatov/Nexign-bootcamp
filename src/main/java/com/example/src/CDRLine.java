package com.example.src;

import java.time.LocalDateTime;
import java.util.Objects;

public class CDRLine {

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

    private String callType;
    private String number;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String tariffType;

    public CDRLine(String callType, String number, LocalDateTime startTime, LocalDateTime endTime, String tariffType){
        this.callType = callType;
        this.number = number;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tariffType = tariffType;
    }

    public String getNumber(){
        return this.number;
    }





}
