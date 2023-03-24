package com.example.src;

import java.util.*;

public class FileMaker {

    public void output(HashMap<String, List<UserRent>> userRentHashMap) {


        Set<String> setOfKeys = userRentHashMap.keySet();
        Iterator iterator = setOfKeys.iterator();
        List<UserRent> userRentList = new ArrayList<>();
        while (iterator.hasNext()) {
            userRentList = userRentHashMap.get(iterator.next());
            //Collections.sort(userRentList, Comparator.comparing(UserRent::getStartTime));
            Double summaryCost = 0.0;
            for (int i = 0; i < userRentList.size(); i++) {
                if (i == 0) {
                    System.out.println("Tariff index " + userRentList.get(i).getTariffType());
                    System.out.println("----------------------------------------------------------------------------");
                    System.out.println("Report for phone number " + userRentList.get(i).getNumber() + ":");
                    System.out.println("| Call Type |   Start Time        |     End Time        | Duration | Cost  |");
                    System.out.println("----------------------------------------------------------------------------");
                }
                System.out.println("|     " + userRentList.get(i).getCallType() + "    | " + userRentList.get(i).getStartTimeFormatted() + " | " + userRentList.get(i).getEndTimeFormatted() + " | " + userRentList.get(i).getDurationString() + " |  " + userRentList.get(i).getCost());
                if (i == userRentList.size()-1)
                summaryCost += userRentList.get(i).getCost()+100;
                else
                    summaryCost += userRentList.get(i).getCost();
            }
            System.out.println("----------------------------------------------------------------------------");

            System.out.println("|                                           Total Cost: |     " + summaryCost + " |");
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("   ");
        }


    }

}
