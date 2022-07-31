package com.example.olioht;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class logBook {
    private String timestamp;

    public logBook() {
        this.timestamp = timestamp;
    }


    // getting users logging in time
    public static String getTimestamp() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDate = dateFormat.format(calendar.getTime());
    return formattedDate;
    }
    public static void writeDate(String dateformat2, int switcher) {
        File root = new File(Environment.getExternalStorageDirectory(), "Download");
        try {
            if (!root.exists()) {
                root.mkdirs();
            }
            File filepath = new File(root, "user_log.csv"); // file path to save
            FileWriter writer = new FileWriter(filepath);
            if(switcher == 1) {
                writer.append("user logged in: "+dateformat2);
                writer.flush();
                writer.close();
            }
            else if(switcher == 2) {
                writer.append("user logged out: "+dateformat2);
                writer.flush();
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }}

