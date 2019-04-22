package main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class Main {


    public static void main(String[] args) throws IOException {
//        ReadFile readFile = new ReadFile();
//        readFile.readFile();

        FileReader reader = new FileReader("src/main/resources/TomAndJerry.txt");
        BufferedReader br = new BufferedReader(reader);
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            if (count == 0) {
                count++;
                continue;
            }
            System.out.println(line);
            String[] splitted = line.split("\\s{2,}");
            if (splitted.length == 4) {
                String day = splitted[0];
                String id = splitted[1];
                String title = splitted[2];
                String view = splitted[3];
                System.out.println("day: " + day);
                System.out.println("id: " + id);
                System.out.println("title: " + title);
                System.out.println("view: " + view);
            }
            count++;
        }

    }
}
