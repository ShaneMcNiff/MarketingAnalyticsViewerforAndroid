package com.example.shane.MAV.Database;

/**
 * Created by Shane on 20/02/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;

import com.example.shane.MAV.Objects.User;

public class Users_DB extends SQLiteOpenHelper {

    private User user = new User("shanemcniff@gmail.com","Shane Mc Niff","password", 1);

    public Users_DB(Context context) {
        super(context, "Users", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_user_table = "create table `users`( `username` varchar(128) NOT NULL, \n" +
                "`email` varchar(128) NOT NULL, \n" +
                "`password` varchar(128) NOT NULL, \n" +
                 "`is_Admin` bool null, \n" +
                "PRIMARY KEY (`email`));";
        db.execSQL(create_user_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void InsertRootInfo(){//this is a method that can be used to insert data or just run SQL manually during development
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //Returns a boolean after checking to see if supplied email and password exist in DB
    public boolean userExists(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        String replace = "";
        for(int i = 0; i < email.length(); i++){
            if(email.substring(i,i+1).equals("@")){
                replace += "AT";
            }else{
                replace += email.substring(i,i+1);
            }
        }

        Cursor resultSet = db.rawQuery("Select * from users where email = ? and password = ?",new String[]{replace,password});
        Log.d("test", "userExists: " + resultSet.getCount());

        if(resultSet.getCount() == 0)
            return false;
        else
            return true;

    }

    //Returns a user object after being supplied a correct email and password
    public User getUserDetails(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        //This section is used to parse the '@' symbol, as SQLite cannot store that particular symbol
        String replace = "";
        for(int i = 0; i < email.length(); i++){
            if(email.substring(i,i+1).equals("@")){
                replace += "AT";
            }else{
                replace += email.substring(i,i+1);
            }
        }

        Cursor resultSet = db.rawQuery("Select * from users where email = ? and password = ?",new String[]{replace,password});
        resultSet.moveToFirst();

        //Replaces the AT which is a stand in for '@' with @
        String fix = "";
        for(int i = 0; i < resultSet.getString(1).length(); i++){
            if(i + 2 < resultSet.getString(1).length()) {
                if (resultSet.getString(1).substring(i, i + 2).equals("AT")) {
                    fix += "@";
                    i++;
                } else {
                    fix += resultSet.getString(1).substring(i, i + 1);
                }
            }
        }

        User user = new User(resultSet.getString(0), fix += "om", resultSet.getString(2), resultSet.getInt(3));

        return user;
    }
}
