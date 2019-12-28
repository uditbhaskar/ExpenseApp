package com.example.expenseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Hashtable;

public class ShowDataActivity extends AppCompatActivity {
    Hashtable<String, String> data;
    String week;
    String amount;
    TextView daysData, total_amount;
    Button expenses;
    String[] array;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        daysData = findViewById(R.id.daysdata);
        total_amount = findViewById(R.id.totalAmount);
        expenses = findViewById(R.id.expenses);
        LoadQuery();
        if(getIntent()!=null){
            week = getIntent().getStringExtra("day");
            amount = data.get(week);
            Toast.makeText(this, week +" amount is : "+ amount, Toast.LENGTH_SHORT).show();
        }


        expenses.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                resetDatabase();
            }
        });


    }

    public void LoadQuery() {
        data = new Hashtable<>();
        DBManager dbManager = new DBManager(this);
        String[] projections = {"_id", "expense", "week"};
        String[] selectionArgs = {""};
        Cursor cursor = dbManager.Query(projections, "", null, null);

        if (cursor.getCount() == 0) {
            //No data..
            Toast.makeText(this, "No expense for today!!", Toast.LENGTH_SHORT).show();
        } else {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    String expense = cursor.getString(cursor.getColumnIndex("expense"));
                    String week = cursor.getString(cursor.getColumnIndex("week"));

                    data.put(week, expense);
                } while (cursor.moveToNext());
            }
        }
        if (!data.isEmpty()) {


            daysData.setText("Amount for the days : \n\n\n"+"Monday : "+data.get("Monday")+
                    "\n\n\n"+"Tuesday : "+data.get("Tuesday")+
                    "\n\n\n"+"Wednesday : "+data.get("Wednesday")+
                    "\n\n\n"+"Thursday : "+data.get("Thursday")+
                    "\n\n\n"+"Friday : "+data.get("Friday")+
                    "\n\n\n"+"Saturday : "+data.get("Saturday")+
                    "\n\n\n"+"Sunday : "+data.get("Sunday"));

            int amount = Integer.valueOf(data.get("Monday"))
                    +Integer.valueOf(data.get("Tuesday"))
                    +Integer.valueOf(data.get("Wednesday"))
                    +Integer.valueOf(data.get("Thursday"))
                    +Integer.valueOf(data.get("Friday"))
                    +Integer.valueOf(data.get("Saturday"))
                    +Integer.valueOf(data.get("Sunday"));

            total_amount.setText("Total Amount : "+ amount);
        }
    }

    void resetDatabase(){

        DBManager dbManager = new DBManager(this);
        array = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for(int i=0; i<array.length; i++){
            ContentValues contentValues = new ContentValues();
            contentValues.put("expense", "0" );
            contentValues.put("week", array[i]);
            String[] selectionArgs = {array[i] + ""};

            dbManager.Update(contentValues, "week=?", selectionArgs);
        }
        LoadQuery();

    }

}
