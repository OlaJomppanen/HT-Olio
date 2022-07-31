package com.example.olioht;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandlers {

    public void writeFile(String showList) throws IOException {
        File root = new File(Environment.getExternalStorageDirectory(), "Download");
        try {
            if (!root.exists()) {
                root.mkdirs();
            }

            File filepath = new File(root, "tulostettava.csv"); // file path to save
            FileWriter writer = new FileWriter(filepath);
            writer.append(showList);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] readFile() {
        String fileContent;
        String str = "";
        String[] output = new String[2];
        int count = 0;

        File root = new File(Environment.getExternalStorageDirectory(), "Download");

        try {
            if (!root.exists()) {
                root.mkdirs();
            }

            File filepath = new File(root, "tulostettava.csv"); // file path to save
            FileReader FR = new FileReader(filepath);
            BufferedReader BR = new BufferedReader(FR);

            while((fileContent = BR.readLine()).length() > 5) {
                count++;
                str = str + "\n" + fileContent;
            }
            FR.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        output[0] = (str);
        output[1] = Integer.toString(count);

        return output;
    }
}
