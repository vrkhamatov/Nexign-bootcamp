package com.example.src;

import java.time.LocalDateTime;

public class CDRLine {

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





}
