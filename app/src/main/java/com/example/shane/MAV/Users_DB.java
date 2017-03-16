package com.example.shane.MAV;

/**
 * Created by Shane on 20/02/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.graphics.PorterDuff;
import android.util.Log;

public class Users_DB extends SQLiteOpenHelper {

    private User user = new User("shanemcniff@gmail.com","Shane Mc Niff","password", 1);

    public Users_DB(Context context) {
        super(context, "Users", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_user_table = "create table `users`( `username` varchar(128) NOT NULL, \n" +
                "`email` varchar(128) NOT NULL, \n" +
                "password` varchar(128) NOT NULL, \n" +
                 "`is_Admin` bool null, \n" +
                "PRIMARY KEY (`email`)";
        db.execSQL(create_user_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void InsertRootInfo(){
        //db.execSQL("insert into users values ('root','root','password',1)");
    }

    public boolean userExists(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resultSet = db.rawQuery("Select * from users where email = ? and password = ?",new String[]{email,password});
        Log.d("test", "userExists: " + resultSet.getCount());

        if(resultSet.getCount() == 0)
            return false;
        else
            return true;

    }

    public User getUserDetails(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resultSet = db.rawQuery("Select * from users where email = ? and password = ?",new String[]{email,password});
        resultSet.moveToFirst();
        User user = new User(resultSet.getString(0), resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3));

        return user;

    }
}
