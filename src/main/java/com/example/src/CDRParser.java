package com.example.src;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class CDRParser {


    public static void main(String[] args) {
        try {
            TxtParser txtParser = new TxtParser();
            File file = new File("src/main/java/com/example/src/cdr.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            HashMap<String, List<UserRent>> mapOfNumbersAndReferences = new HashMap<>();
            int q = 0;
            while (line != null) {
                q = q +1;
                CDRLine cdrLine = txtParser.makeCDRLineFromTxt(line);
                UserRent userRent = new UserRent(cdrLine);
                List<UserRent> listOfCalls = new ArrayList<>();

                if (mapOfNumbersAndReferences.get(cdrLine.getNumber()) == null) {
                    listOfCalls.add(userRent);
                    mapOfNumbersAndReferences.put(cdrLine.getNumber(), listOfCalls);
                } else {
                    listOfCalls = mapOfNumbersAndReferences.get(cdrLine.getNumber());
                    listOfCalls.add(userRent);
                    mapOfNumbersAndReferences.put(cdrLine.getNumber(), listOfCalls);
                }
                line = reader.readLine();
            }
            FileMaker fileMaker = new FileMaker();
            fileMaker.output(mapOfNumbersAndReferences);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
