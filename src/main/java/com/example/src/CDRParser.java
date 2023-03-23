package com.example.src;

import java.io.*;

public class CDRParser {

    public static void main(String[] args)  {
        try{
            File file = new File("src/main/java/com/example/src/cdr.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
