package com.example.shane.MAV.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Shane on 24/03/2017.
 */

public class Responses_DB extends SQLiteOpenHelper{

    public Responses_DB(Context context){super(context,"responses", null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_responses_table = "CREATE TABLE `responses` (\n" +
                "  `campaign_id` int(1) NOT NULL,\n" +
                "  `q_answer1` int(1) NOT NULL,\n" +
                "  `q_answer2` int(1) NOT NULL,\n" +
                "  `q_answer3` int(1) NOT NULL);";
        db.execSQL(create_responses_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void InsertRootData(){//this is a method that can be used to insert data or just run SQL manually during development
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //Returns the statistics of all the answers that were supplied
    public String[] getStatsAnswer1(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resultSet = db.rawQuery("select q_answer1 from responses",new String[]{});
        resultSet.moveToFirst();

        String returnString[] = new String[resultSet.getCount()];
        for(int i = 0; i < resultSet.getCount(); i++){
            returnString[i] = (resultSet.getString(0) + "");
            resultSet.moveToNext();
        }

        return  returnString;
    }

    //Returns the statistics of all the answers that were supplied
    public String[] getStatsAnswer2(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resultSet = db.rawQuery("select q_answer2 from responses",new String[]{});
        resultSet.moveToFirst();

        String returnString[] = new String[resultSet.getCount()];
        for(int i = 0; i < resultSet.getCount(); i++){
            returnString[i] = resultSet.getString(0);
            resultSet.moveToNext();
        }

        return  returnString;
    }

    //Returns the statistics of all the answers that were supplied
    public String[] getStatsAnswer3(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resultSet = db.rawQuery("select q_answer3 from responses",new String[]{});
        resultSet.moveToFirst();

        String returnString[] = new String[resultSet.getCount()];
        for(int i = 0; i < resultSet.getCount(); i++){
            returnString[i] = resultSet.getString(0);
            resultSet.moveToNext();
        }

        return  returnString;
    }
}
