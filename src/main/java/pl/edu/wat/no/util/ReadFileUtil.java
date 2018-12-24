package main.java.pl.edu.wat.no.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFileUtil {
    public static List<Integer> read(String fileName) {
        List<Integer> list = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            String line;

            while ((line = br.readLine()) != null) {

                String[] splitted = line.split("\\s+");

                for (String s : splitted) {
                    list.add(Integer.parseInt(s));
                }
            }

            br.close();

        } catch (IOException e) {
            System.out.println("ERROR: unable to read file " + fileName);
            e.printStackTrace();
        }

        return list;
    }
}
