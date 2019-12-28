package com.example.expenseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText dayText;
    EditText moneyText;
    int dayNum;
    String dayName;
    String money;
    int moneyAmount;
    SharedPreferences sharedPreferences;

    public void addExpense(View view){
        submit=findViewById(R.id.addMoneyDay);
        sharedPreferences=getApplicationContext().getSharedPreferences("com.example.expenseapp", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(dayName,dayNum);
        editor.putInt(dayName, moneyAmount);
        editor.apply();
        Intent intent = new Intent(MainActivity.this, ShowDataActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dayText=findViewById(R.id.day);
        moneyText=findViewById(R.id.money);

        dayName=dayText.getText().toString();
        dayNum=Integer.parseInt(dayName);
        money=moneyText.getText().toString();
        moneyAmount=Integer.parseInt(money);
    }

}
