package com.example.shane.MAV.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shane on 24/03/2017.
 */

public class Profiles_DB extends SQLiteOpenHelper {

    public Profiles_DB(Context context){super(context,"Campaigns", null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
