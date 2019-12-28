package com.example.expenseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class ShowDataActivity extends AppCompatActivity {
    TextView sunday;
    TextView monday;
    TextView tuesday;
    TextView wednesday;
    TextView thursday;
    TextView friday;
    TextView saturday;
    int amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.expenseapp", MODE_PRIVATE);
        String value = sharedPreferences.getString("dayName","");
        amount=sharedPreferences.getInt("dayName",0);
        switch (value){
            case "1":
                monday=findViewById(R.id.monday);
                monday.setText(amount);
                break;
            case "2":
                tuesday=findViewById(R.id.monday);
                tuesday.setText(amount);
                break;
            case "3":
                wednesday=findViewById(R.id.monday);
                wednesday.setText(amount);
                break;
            case "4":
                thursday=findViewById(R.id.monday);
                thursday.setText(amount);
                break;
            case "5":
                friday=findViewById(R.id.monday);
                friday.setText(amount);
                break;
            case "6":
                saturday=findViewById(R.id.monday);
                saturday.setText(amount);
                break;
            case "7":
                sunday=findViewById(R.id.sunday);
                sunday.setText(amount);
                break;
        }

    }
}
