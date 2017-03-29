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

    public void InsertRootData(){
        SQLiteDatabase db = this.getWritableDatabase();

        String insert_answer1 = "insert into responses values (1,1,1,4)";
        String insert_answer2 = "insert into responses values (1,1,1,4)";
        String insert_answer3 = "insert into responses values (1,1,1,4)";
        String insert_answer4 = "insert into responses values (1,1,2,3)";
        String insert_answer5 = "insert into responses values (1,2,2,3)";
        String insert_answer6 = "insert into responses values (1,2,2,3)";
        String insert_answer7 = "insert into responses values (1,2,2,4)";
        String insert_answer8 = "insert into responses values (1,2,2,4)";
        String insert_answer9 = "insert into responses values (1,2,3,4)";
        String insert_answer10 = "insert into responses values (1,2,3,2)";
        String insert_answer11 = "insert into responses values (1,2,3,2)";
        String insert_answer12 = "insert into responses values (1,3,3,4)";
        String insert_answer13 = "insert into responses values (1,3,4,1)";
        String insert_answer14 = "insert into responses values (1,3,4,1)";
        String insert_answer15 = "insert into responses values (1,4,4,2)";

        /*db.execSQL(insert_answer1);
        db.execSQL(insert_answer2);
        db.execSQL(insert_answer3);
        db.execSQL(insert_answer4);
        db.execSQL(insert_answer5);
        db.execSQL(insert_answer6);
        db.execSQL(insert_answer7);
        db.execSQL(insert_answer8);
        db.execSQL(insert_answer9);
        db.execSQL(insert_answer10);
        db.execSQL(insert_answer11);
        db.execSQL(insert_answer12);
        db.execSQL(insert_answer13);
        db.execSQL(insert_answer14);
        db.execSQL(insert_answer15);*/
    }

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


    public String[] getResposnes(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resultSet = db.rawQuery("Select * from responses",new String[]{});
        resultSet.moveToFirst();
        String []responses = new String[resultSet.getCount()];
        for(int i = 0; i < resultSet.getCount(); i++){
            responses[i] = resultSet.getString(i);
        }
        return responses;
    }
}
