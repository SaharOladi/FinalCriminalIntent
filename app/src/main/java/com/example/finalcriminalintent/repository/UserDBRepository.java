package com.example.finalcriminalintent.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.finalcriminalintent.database.CrimeDBHelper;
import com.example.finalcriminalintent.database.CrimeDBSchema;
import com.example.finalcriminalintent.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDBRepository  {

    private static UserDBRepository sInstance;

    private SQLiteDatabase mDatabase;
    private Context mContext;


    public static UserDBRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new UserDBRepository(context);

        return sInstance;
    }

    private UserDBRepository(Context context) {
        mContext = context.getApplicationContext();
        CrimeDBHelper userDBHelper = new CrimeDBHelper(mContext);

        //all 4 checks happens on getDataBase
        mDatabase = userDBHelper.getWritableDatabase();
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        Cursor cursor = mDatabase.query(
                CrimeDBSchema.UserTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        if (cursor == null || cursor.getCount() == 0)
            return users;

        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                User user = extractCrimeFromCursor(cursor);
                users.add(user);

                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return users;
    }

    public User getUser(UUID userId) {
        String where = CrimeDBSchema.UserTable.Cols.UUID + " = ?";
        String[] whereArgs = new String[]{userId.toString()};

        Cursor cursor = mDatabase.query(
                CrimeDBSchema.UserTable.NAME,
                null,
                where,
                whereArgs,
                null,
                null,
                null);

        if (cursor == null || cursor.getCount() == 0)
            return null;

        try {
            cursor.moveToFirst();
            User user = extractCrimeFromCursor(cursor);

            return user;
        } finally {
            cursor.close();
        }
    }

    public boolean checkUserLogin(String username,String password){

        String whereclause = CrimeDBSchema.UserTable.Cols.NAME + "=?"  + " AND " + CrimeDBSchema.UserTable.Cols.PASSWORD + "=?"; //<<<<<<<<<< ?'s will be replaced according to whereargs on a 1 by 1 basis
        String[] whereargs = new String[]{username,password};
        Cursor cursor = mDatabase.query(
                CrimeDBSchema.UserTable.NAME,
                new String[]{CrimeDBSchema.UserTable.Cols.NAME,CrimeDBSchema.UserTable.Cols.PASSWORD},
                whereclause,
                whereargs,
                null,null,null
        );
        int count = cursor.getCount();
        cursor.close();
        return count > 0;

    }

    public void insertUser(User user) {
        ContentValues values = getContentValues(user);
        mDatabase.insert(CrimeDBSchema.UserTable.NAME, null, values);
    }

    public void updateUser(User user) {
        ContentValues values = getContentValues(user);
        String whereClause = CrimeDBSchema.UserTable.Cols.UUID + " = ?";
        String[] whereArgs = new String[]{user.getUserID().toString()};
        mDatabase.update(CrimeDBSchema.UserTable.NAME, values, whereClause, whereArgs);
    }

    public void deleteUser(User user) {
        String whereClause = CrimeDBSchema.UserTable.Cols.UUID + " = ?";
        String[] whereArgs = new String[]{user.getUserID().toString()};
        mDatabase.delete(CrimeDBSchema.UserTable.NAME, whereClause, whereArgs);
    }


    private ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(CrimeDBSchema.UserTable.Cols.UUID, user.getUserID().toString());
        values.put(CrimeDBSchema.UserTable.Cols.NAME, user.getUserName());
        values.put(CrimeDBSchema.UserTable.Cols.PASSWORD, user.getUserPassword());
        return values;
    }

    private User extractCrimeFromCursor(Cursor cursor) {
        UUID uuid = UUID.fromString(cursor.getString(cursor.getColumnIndex(CrimeDBSchema.UserTable.Cols.UUID)));
        String name = cursor.getString(cursor.getColumnIndex(CrimeDBSchema.UserTable.Cols.NAME));
        String password = cursor.getString(cursor.getColumnIndex(CrimeDBSchema.UserTable.Cols.PASSWORD));

        return new User(uuid, name, password);
    }

    public int getPosition(UUID userId) {
        List<User> users = getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID().equals(userId))
                return i;
        }
        return -1;
    }
}
