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

    public void InsertRootInfo(){
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("insert into users values ('Shane Mc Niff','shanemcniffATgmail.com','Cartroneast1',1)");
    }

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

    public User getUserDetails(String email, String password) {

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
        resultSet.moveToFirst();

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
