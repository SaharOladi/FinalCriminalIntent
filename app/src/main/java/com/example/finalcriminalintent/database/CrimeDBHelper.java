package com.example.finalcriminalintent.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.finalcriminalintent.database.CrimeDBSchema.CrimeTable.Cols;



import androidx.annotation.Nullable;

public class CrimeDBHelper extends SQLiteOpenHelper {

    public CrimeDBHelper(@Nullable Context context) {
        super(context, CrimeDBSchema.NAME, null, CrimeDBSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("CREATE TABLE " + CrimeDBSchema.CrimeTable.NAME + " (");
        sbQuery.append(Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,");
        sbQuery.append(Cols.UUID + " TEXT NOT NULL,");
        sbQuery.append(Cols.TITLE + " TEXT,");
        sbQuery.append(Cols.DATE + " TEXT,");
        sbQuery.append(Cols.SOLVED + " INTEGER");
        sbQuery.append(");");

        StringBuilder sbQueryTwo = new StringBuilder();
        sbQueryTwo.append("CREATE TABLE " + CrimeDBSchema.UserTable.NAME + " (");
        sbQueryTwo.append(CrimeDBSchema.UserTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,");
        sbQueryTwo.append(CrimeDBSchema.UserTable.Cols.UUID + " TEXT NOT NULL,");
        sbQueryTwo.append(CrimeDBSchema.UserTable.Cols.NAME + " TEXT,");
        sbQueryTwo.append(CrimeDBSchema.UserTable.Cols.PASSWORD + " TEXT,");
        sbQueryTwo.append(");");

        db.execSQL(sbQuery.toString());
        db.execSQL(sbQueryTwo.toString());


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
