package com.example.src;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CDRParser {


    public static void main(String[] args) {
        try {
            TxtParser txtParser = new TxtParser();
            File file = new File("src/main/java/com/example/src/cdr.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            HashMap<String, List<CDRLine>> mapOfNumbersAndReferences = new HashMap<>();

            while (line != null) {
                CDRLine cdrLine = txtParser.makeCDRLineFromTxt(line);
                List<CDRLine> listOfCalls = new ArrayList<>();

                if (mapOfNumbersAndReferences.get(cdrLine.getNumber()) == null) {
                    listOfCalls.add(cdrLine);
                    mapOfNumbersAndReferences.put(cdrLine.getNumber(), listOfCalls);
                } else {
                    listOfCalls = mapOfNumbersAndReferences.get(cdrLine.getNumber());
                    listOfCalls.add(cdrLine);
                    mapOfNumbersAndReferences.put(cdrLine.getNumber(), listOfCalls);
                }
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
