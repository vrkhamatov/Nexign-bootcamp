package com.example.src;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            TxtParser txtParser = new TxtParser();
            String path = "src/main/java/com/example/src/cdr.txt";
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            HashMap<String, List<CDRLine>> mapOfNumbersAndReferences = new HashMap<>();
            while (line != null) {
                CDRLine cdrLine = txtParser.makeCDRLineFromTxt(line);
                List<CDRLine> listOfCalls = new ArrayList<>();
                if (mapOfNumbersAndReferences.get(cdrLine.getNumber()) == null) {
                    listOfCalls.add(cdrLine);
                } else {
                    listOfCalls = mapOfNumbersAndReferences.get(cdrLine.getNumber());
                    listOfCalls.add(cdrLine);
                }
                mapOfNumbersAndReferences.put(cdrLine.getNumber(), listOfCalls);
                line = reader.readLine();
            }
            FileMaker fileMaker = new FileMaker();
            fileMaker.output(mapOfNumbersAndReferences);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
