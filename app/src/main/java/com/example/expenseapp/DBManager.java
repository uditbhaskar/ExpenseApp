package com.example.expenseapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import androidx.annotation.Nullable;

public class DBManager {
    String dbname = "ExpenseHolder";
    String dbTable = "List";
    String colId = "_id";
    String colPrice = "expense";
    String colWeek = "week";
    int dbVersion = 1;
    String[] array;

    //Creste table if not exists
    String sqlCreateTable = "CREATE TABLE IF NOT EXISTS "+dbTable+" ("+ colId +" INTEGER PRIMARY KEY, "+
            colPrice + " TEXT, "+ colWeek +" TEXT);";

    SQLiteDatabase sqlDb =null;

    public DBManager(Context context){
        DatabaseHelperClass db = new DatabaseHelperClass(context);
        sqlDb = db.getWritableDatabase();
    }

    public class DatabaseHelperClass extends SQLiteOpenHelper {

        public DatabaseHelperClass(@Nullable Context context) {
            super(context, dbname, null, dbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(sqlCreateTable);
            array = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            for(int i=0; i<array.length; i++){
                ContentValues contentValues = new ContentValues();
                contentValues.put("expense", "0" );
                contentValues.put("week", array[i]);
                db.insert(dbTable, "", contentValues);
            }


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query = "DROP TABLE IF EXISTS";
            db.execSQL(query+ dbTable);
        }
    }

    public long Insert(ContentValues contentValues){
        long id = sqlDb.insert(dbTable, "", contentValues);
        return id;
    }
    public Cursor Query(String[] projection, String selection, String[] selectionArgs, String sorOrder){
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(dbTable);
        Cursor cursor = qb.query(sqlDb, projection, selection, selectionArgs, null, null, sorOrder);
        return cursor;
    }

    public int Update(ContentValues values, String selection, String[] selectionArgs){
        int count = sqlDb.update(dbTable, values, selection, selectionArgs);
        return count;
    }



}
