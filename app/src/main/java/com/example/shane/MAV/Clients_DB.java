package com.example.shane.MAV;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shane on 16/03/2017.
 */

public class Clients_DB extends SQLiteOpenHelper {

    public Clients_DB (Context context){super(context, "FYP", null, 1);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_clients_table = "create table `clients`(`client_id` int(128) not null auto_increment,\n" +
                "    `client_name` varchar(128) not null,\n" +
                "    primary key (`client_id`)\n" +
                ");";
        db.execSQL(create_clients_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void InsertRootInfo(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("insert into clients values (1, 'Spar');");
    }
}
