package com.example.sobiscanner.forms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sobiscanner.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private CardView cvIn, cvOut;
    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d'" + getDayOfMonthSuffix(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) + "'", Locale.getDefault());
        return dateFormat.format(new Date());
    }

    private static String getDayOfMonthSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        switch (day % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cvIn = (CardView) findViewById(R.id.cv_in);
        cvOut = (CardView) findViewById(R.id.cv_out);

        cvIn.setOnClickListener(v -> {
            Intent incoming = new Intent(MainActivity.this, IncomingActivity.class);
            startActivity(incoming);
            finish();
        });

        cvOut.setOnClickListener(v -> {
            Intent outgoing = new Intent(MainActivity.this, OutgoingActivity.class);
            startActivity(outgoing);
            finish();
        });
    }


}