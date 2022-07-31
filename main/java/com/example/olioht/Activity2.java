package com.example.olioht;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


public class Activity2 extends MainActivity {

    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        goBack = findViewById(R.id.goback2);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this, MainActivity.class);
                startActivity(intent);
            }});

        final LinearLayout lmm = (LinearLayout) findViewById(R.id.linearUser_comment);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        csvparser filter = new csvparser();
        FileHandlers handla = new FileHandlers();

        String[] str = handla.readFile();

        int count = Integer.parseInt(str[1]);

        for (int j=1; j<count;j=j+1) {
            LinearLayout lll = new LinearLayout(this);
            lll.setOrientation(LinearLayout.VERTICAL);

            // Displaying the saved review of films
            TextView Title = new TextView(this);
            Title.setText(filter.getname(str, j)); // display the title
            lll.addView(Title);

            // displays whether user has made a comment or not
            TextView commentTxt = new TextView(this);
            commentTxt.setText("Arvostelusi:"); // display the comment
            lll.addView(commentTxt);

            TextView comment = new TextView(this);
            // displays whether user has made a comment or not
            if (filter.getComments(str, j).equals("null")){
                comment.setText("Et ole vielä antanut arvostelua.");
            }
            else {comment.setText(filter.getComments(str, j));}
            lll.addView(comment);

            TextView Empty = new TextView(this);
            Empty.setText("\n");
            lll.addView(Empty);
            lmm.addView(lll);
        }
    }
}