package com.example.olioht;

public class csvparser {

    // gets the list of strings and returns the titles
    public String getname(String[] transfer, int index) {
        String title = null;

        String str = transfer[0];
        String[] whole =  str.split("\n");

        String row = whole[index];
        String[] row2 = row.split(";");
        title = row2[0];

        return title;
    }
    // gets alls comments as strings and returns them
    public String getComments(String[] str, int index) {
        String comment = null;

        String transfer = str[0];
        String[] whole =  transfer.split("\n");

        String row = whole[index];
        String[] row2 = row.split(";");
        comment = row2[3];

        return comment;
    }
}
