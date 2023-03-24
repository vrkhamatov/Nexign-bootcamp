package com.example.src;

import java.time.LocalDateTime;

public class TxtParser {

    public CDRLine makeCDRLineFromTxt(String line) {
        String callType = line.substring(0, 2);
        String number = line.substring(4, 15);
        int year = Integer.parseInt(line.substring(17, 21));
        int month = Integer.parseInt(line.substring(21, 23));
        int day = Integer.parseInt(line.substring(23, 25));
        int hour = Integer.parseInt(line.substring(25, 27));
        int min = Integer.parseInt(line.substring(27, 29));
        int sec = Integer.parseInt(line.substring(29, 31));
        LocalDateTime startTime = LocalDateTime.of(year, month, day, hour, min, sec);
        year = Integer.parseInt(line.substring(33, 37));
        month = Integer.parseInt(line.substring(37, 39));
        day = Integer.parseInt(line.substring(39, 41));
        hour = Integer.parseInt(line.substring(41, 43));
        min = Integer.parseInt(line.substring(43, 45));
        sec = Integer.parseInt(line.substring(45, 47));
        LocalDateTime endTime = LocalDateTime.of(year, month, day, hour, min, sec);
        String tariffType = line.substring(49,51);

        return new CDRLine(callType,number,startTime,endTime,tariffType);
    }



}
