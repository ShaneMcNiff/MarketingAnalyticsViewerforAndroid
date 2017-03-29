package com.example.shane.MAV.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shane on 16/03/2017.
 */

public class Clients_DB extends SQLiteOpenHelper {

    public Clients_DB (Context context){super(context, "Clients", null, 1);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_clients_table = "create table `clients`(`client_id` int(128) not null,\n" +
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
        //db.execSQL("insert into clients values (2, 'Tesco');");
    }

    public String getClientNameFromID(int clientID){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] constraint = {clientID + ""};

        Cursor resultSet = db.rawQuery("Select client_name from clients where client_id = ?",constraint);
        resultSet.moveToFirst();

        return resultSet.getString(0);
    }
}
