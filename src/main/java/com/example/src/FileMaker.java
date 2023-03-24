package com.example.src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class FileMaker {

    public void output(HashMap<String, List<CDRLine>> userRentHashMap) throws IOException {


        Set<String> setOfKeys = userRentHashMap.keySet();
        Iterator<String> iterator = setOfKeys.iterator();
        List<CDRLine> userRentList;
        while (iterator.hasNext()) {
            String currentNumber = iterator.next();
            userRentList = userRentHashMap.get(currentNumber);
            userRentList.sort(Comparator.comparing(CDRLine::getStartTime));
            double summaryCost = 0.0;
            long summaryDuration = 0;
            File file = new File("reports\\" + "Report for phone " + currentNumber + ".txt");
            FileWriter writer;
            writer = new FileWriter(file);
            for (int i = 0; i < userRentList.size(); i++) {
                if (i == 0) {
                    writer.write("Tariff index " + userRentList.get(i).getTariffType() + "\n");
                    writer.write("----------------------------------------------------------------------------" + "\n");
                    writer.write("Report for phone number " + userRentList.get(i).getNumber() + ":" + "\n");
                    writer.write("----------------------------------------------------------------------------" + "\n");
                    writer.write("| Call Type |   Start Time        |     End Time        | Duration | Cost  |" + "\n");
                    writer.write("----------------------------------------------------------------------------" + "\n");
                }

                summaryCost = summaryCost + makeSummaryCost(userRentList.get(i).getTariffType(),userRentList.get(i).getDuration(),userRentList.get(i).getCallType(),summaryDuration);
                if ((userRentList.size() -1 == i)&&(Objects.equals(userRentList.get(i).getTariffType(), "06"))&&(summaryCost == 0.0))
                    summaryCost = 100.0;
                summaryDuration = summaryDuration + userRentList.get(i).getDuration().getSeconds();
                writer.write("|     " + userRentList.get(i).getCallType() + "    | " + userRentList.get(i).getStartTimeFormatted() + " | " + userRentList.get(i).getEndTimeFormatted() + " | " + userRentList.get(i).getDurationString() + " |  " + makeSummaryCost(userRentList.get(i).getTariffType(),userRentList.get(i).getDuration(),userRentList.get(i).getCallType(),summaryDuration) + "\n");
            }

            writer.write("----------------------------------------------------------------------------" + "\n");

            writer.write("|                                           Total Cost: |     " + summaryCost + " |" + "\n");
            writer.write("----------------------------------------------------------------------------" + "\n");
            writer.write("   " + "\n");
            writer.close();
        }


    }

    public static double makeSummaryCost(String tariffType, Duration duration, String callType,long summaryDuration){
        double cost;
        long sec = duration.getSeconds();
        long min = (int)Math. ceil(sec/60);

        if (summaryDuration%60 > 0){
            summaryDuration = summaryDuration/60 + 1;
        }
        else
            summaryDuration = summaryDuration/60;

        if (sec%60 != 0){
            min +=1;
        }

        if (Objects.equals(tariffType, "03")){
            cost = min * 1.5;
            return cost;
        }
        else if (Objects.equals(tariffType, "06")){
            if (summaryDuration <= 300){
                cost = 0.0;
            }
                else {
                cost = 100;
                cost = cost + min;
            }
        }
        else
        {
            if (Objects.equals(callType, "02")){
                cost = 0;
            }
            else
            {
                if (summaryDuration<=100) cost = min*0.5;
                else
                    cost = min*0.5 + 1.5*(min - 100);
            }
        }

        return cost;

    }



}
