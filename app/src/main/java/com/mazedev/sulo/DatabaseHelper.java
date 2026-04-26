package com.mazedev.sulo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String databaseName = "SuloDB.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "SuloDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase SuloDatabase) {
        SuloDatabase.execSQL("create Table users(name TEXT, email TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase SuloDatabase, int oldVersion, int newVersion) {
        SuloDatabase.execSQL("drop Table if exists users");
        onCreate(SuloDatabase);
    }

    public Boolean insertData(String name, String email, String password) {
        SQLiteDatabase SuloDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = SuloDatabase.insert("users", null, ContentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase SuloDatabase = this.getWritableDatabase();
        Cursor cursor = SuloDatabase.rawQuery("Select * from users where email = ?", new String[]{email});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase SuloDatabase = this.getWritableDatabase();
        Cursor cursor = SuloDatabase.rawQuery("Select * from users where email = ?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
