package com.example.olioht;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Films> movies = new ArrayList<>();
    // --Commented out by Inspection (31.7.2022 11.50):Context context = null;
    EditText ed;
// --Commented out by Inspection START (31.7.2022 11.50):

    Films film = new Films();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity context = MainActivity.this;

        // allows alongside with user permissions to access outside network
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final LinearLayout lm = (LinearLayout) findViewById(R.id.linearMain);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        List<EditText> allEds = new ArrayList<>();

//  Add your own reviews
        View buttonUserCommand = findViewById(R.id.buttonUserCommand);
        buttonUserCommand.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Activity2.class);
            startActivity(intent);
        });

        xmlparser test = new xmlparser();
        List<String> newList = test.readXML();
        List<String> showList = new ArrayList<>();

        int b = 1;
            String time;
            logBook logged = new logBook();
            time = logBook.getTimestamp();
            System.out.println("User logged in:" + time);

// Creating an arraylist that contains each film as an object
        for(int j=0;j<newList.size();j=j+3) {

            // showList adds only films only once so no duplicates as in uusiList
            if(showList.contains(newList.get(j))) {
                // do nothing
            }
            else {

                Films film = new Films(); // creating an object
                showList.add(newList.get(j));
                film.setId(newList.get(j)); // sets the movie id from the uusilist
                film.setEvent_id(newList.get(j+1)); // sets the event id from uusilist
                film.setTitle(newList.get(j+2));// sets the tit from the list uusiList

                /////

                FileHandlers handla = new FileHandlers(); // a class that writes and read files
                csvparser filter = new csvparser();
                String[] switched = handla.readFile();

                // allowing the user to make a comment about a film
                int count = Integer.parseInt(switched[1]); // initialized at 1 to skip empty first line
                count = count - 1;
                if(b == count) {
                    film.setComment("null");
                }
                // if b is less than a count, meaning it cannot be a data of a fim
                else if (b < count) {
                String comment = filter.getComments(switched, b); // then proceed to add the comment to the film object
                film.setComment(comment);}

                movies.add(film); // every films is in the list as object with three infos each
                b = b + 1; // b parameter works as counting for "for" loop rounds

// Empty space
                TextView text = new TextView(this);
                text.setText(" ");
                lm.addView(text);
// Movie title text
                TextView moviesTitleText = new TextView(this);
                moviesTitleText.setText(newList.get(j+2));
                lm.addView(moviesTitleText);

// Add textview
                ed = new EditText(MainActivity.this);
                allEds.add(ed);
                ed.setId(j);
                ed.setHint("Anna arvostelu:");
                ed.setLayoutParams(params);
                lm.addView(ed);
                }
        }

        // Strings[] receives all the inputs from comment bars and joins them in one list
        String[] strings = new String[allEds.size()];

        View SaveComment = findViewById(R.id.SaveComment);
        // shows user's comment
        SaveComment.setOnClickListener(view -> {
            String str;
            FileHandlers handla = new FileHandlers();
            csvparser filter = new csvparser();

            // saves talllettaa allEdsiin,jossa on kaikki edittextit ja muuntaa
            for(int i=0; i < movies.size(); i++){
                strings[i] = allEds.get(i).getText().toString();
            }

            String[] transfer = handla.readFile();

            // gets from readFile, transfer[0] has entire csv and transfer[1] has data rows count.
            // that number is then transformed into int from string
            int count = Integer.parseInt(transfer[1]);
            System.out.println(count);

            // This function  tests if there is already an existing comment
            if (count > 2) {

                filter.getComments(transfer, 1);
                String comment;
                System.out.println(strings[0] + " <- strings[0]");

                if (strings[0].equals("")) {
                } else {
                    comment = strings[0];
                    System.out.println("Else");
                    movies.get(0).setComment(comment);
                }// if there are no comments, user is allowed to add them

                for (int i = 1; i < movies.size(); i++) {
                    transfer = handla.readFile();
                    int a = i + 1;
                    comment = filter.getComments(transfer, a);
                    if (strings[i].equals("")) {
                    } else {
                        comment = strings[i];
                        movies.get(i).setComment(comment); // sets comment to the respective movie
                    }
                }
            }

            // writes all the movies into a file for future reference
            str = movies.get(0).toString();
            for (int i = 1; i < movies.size(); i++) {
                str = str + "\n" + movies.get(i).toString();
            }

            str = str + "\n" + ":(";

            try {
                handla.writeFile(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
