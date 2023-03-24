package com.example.src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileMaker {

    public void output(HashMap<String, List<UserRent>> userRentHashMap) throws IOException {


        Set<String> setOfKeys = userRentHashMap.keySet();
        Iterator<String> iterator = setOfKeys.iterator();
        List<UserRent> userRentList;
        while (iterator.hasNext()) {
            String currentNumber = iterator.next();
            userRentList = userRentHashMap.get(currentNumber);
            userRentList.sort(Comparator.comparing(UserRent::getStartTime));
            Double summaryCost = 0.0;
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
                writer.write("|     " + userRentList.get(i).getCallType() + "    | " + userRentList.get(i).getStartTimeFormatted() + " | " + userRentList.get(i).getEndTimeFormatted() + " | " + userRentList.get(i).getDurationString() + " |  " + userRentList.get(i).getCost() + "\n");
                if ((i == userRentList.size() - 1)&&(Objects.equals(userRentList.get(i).getTariffType(), "06")))
                    summaryCost += userRentList.get(i).getCost() + 100;
                else
                    summaryCost += userRentList.get(i).getCost();
            }
            writer.write("----------------------------------------------------------------------------" + "\n");

            writer.write("|                                           Total Cost: |     " + summaryCost + " |" + "\n");
            writer.write("----------------------------------------------------------------------------" + "\n");
            writer.write("   " + "\n");
            writer.close();
        }


    }

}
