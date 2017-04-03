package com.example.shane.MAV.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shane on 16/03/2017.
 */

public class Countries_DB extends SQLiteOpenHelper {

    public Countries_DB(Context context){super (context, "Countries", null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_countries_table = "create table `countries` (`country_id` int(4) NOT NULL,`country_name` varchar(128) not null,primary key (`country_id`));";
        db.execSQL(create_countries_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void InsertRootInfo(){//this is a method that can be used to insert data or just run SQL manually during development
        SQLiteDatabase db = this.getWritableDatabase();
    }
}
