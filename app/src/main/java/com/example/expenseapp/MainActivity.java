package com.example.expenseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText moneyText;
    String day;

    public void addExpense() {
        if (day != null) {
            updateTodo(moneyText.getText().toString().trim(), day);
            Intent intent = new Intent(MainActivity.this, ShowDataActivity.class);
            intent.putExtra("day", day);
            startActivity(intent);
        } else {
            Toast.makeText(this, "First Select week day.", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.btn_addMoney);
        moneyText = findViewById(R.id.et_money);
        setSpinner();
        submit.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          addExpense();
                                      }
                                  }
        );

    }

    void setSpinner() {
        Spinner weekSpinner;
        weekSpinner = (Spinner) findViewById(R.id.weekSpinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.weeks));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weekSpinner.setAdapter(adapter);
        weekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {
                day = adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                day = null;
            }
        });
    }


    public void updateTodo(String expense, String week) {
        DBManager dbManager = new DBManager(this);
        ContentValues contentValues = new ContentValues();
        contentValues.put("expense", expense.trim());
        contentValues.put("week", week.trim());
        String[] selectionArgs = {week + ""};

        long id = dbManager.Update(contentValues, "week=?", selectionArgs);

        if (id > 0) {
            Toast.makeText(this, "Your expense has been updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Unable to update!!!", Toast.LENGTH_SHORT).show();
        }

    }


}
