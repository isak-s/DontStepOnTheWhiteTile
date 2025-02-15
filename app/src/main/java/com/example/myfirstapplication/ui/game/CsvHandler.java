package com.example.myfirstapplication.ui.game;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;

public class CsvHandler {
    final static String fileName = "playthroughs.csv";
    public static void saveToFile(Context context, Playthrough play) {
        File file = new File(context.getFilesDir(), fileName);
        try (FileWriter writer = new FileWriter(file, true)) { // 'true' appends data
            writer.append(play.getPlayer()).append(",")
                    .append(String.valueOf(play.getPoints())).append(",")
                    .append(String.valueOf(play.getStartTime()))
                    .append("\n");
            System.out.println("CSV file written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Playthrough> readFromFile(Context context) {
        File file = new File(context.getFilesDir(), fileName);
        List<Playthrough> playthroughs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(","); // Split CSV
                if (values.length == 3) { // Ensure valid format
                    String player = values[0];
                    int points = Integer.parseInt(values[1]);
                    Long startTime = Long.valueOf(values[2]);

                    playthroughs.add(new Playthrough(player, points, startTime));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return playthroughs;
    }
}
