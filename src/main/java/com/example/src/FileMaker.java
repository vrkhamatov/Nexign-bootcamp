package com.example.src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
                summaryCost = summaryCost + SummaryCostCalculator.makeSummaryCost(userRentList.get(i).getTariffType(), userRentList.get(i).getDuration(), userRentList.get(i).getCallType(), summaryDuration);
                if ((userRentList.size() - 1 == i) && (Objects.equals(userRentList.get(i).getTariffType(), "06")) && (summaryCost == 0.0))
                    summaryCost = 100.0;
                if ((Objects.equals(userRentList.get(i).getTariffType(), "11")) && (Objects.equals(userRentList.get(i).getCallType(), "02"))) {
                } else
                    summaryDuration = summaryDuration + userRentList.get(i).getDuration().getSeconds();
                writer.write("|     " + userRentList.get(i).getCallType() + "    | " + userRentList.get(i).getStartTimeFormatted() + " | " + userRentList.get(i).getEndTimeFormatted() + " | " + userRentList.get(i).getDurationString() + " |  " + SummaryCostCalculator.makeSummaryCost(userRentList.get(i).getTariffType(), userRentList.get(i).getDuration(), userRentList.get(i).getCallType(), summaryDuration) + "\n");
            }

            writer.write("----------------------------------------------------------------------------" + "\n");

            writer.write("|                                           Total Cost: |     " + summaryCost + " |" + "\n");
            writer.write("----------------------------------------------------------------------------" + "\n");
            writer.write("   " + "\n");
            writer.close();
        }


    }


}
