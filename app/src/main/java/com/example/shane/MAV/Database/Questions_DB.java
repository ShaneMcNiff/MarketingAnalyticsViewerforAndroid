package com.example.shane.MAV.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Shane on 24/03/2017.
 */

public class Questions_DB extends SQLiteOpenHelper {

    public Questions_DB(Context context){super(context,"Questions", null, 1);}
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_questions_table = "CREATE TABLE `questions` (\n" +
                "  `id` int(11) NOT NULL,\n" +
                "  `questionText` varchar(200) NOT NULL,\n" +
                "  `answer1` varchar(200),\n" +
                "  `answer2` varchar(200),\n" +
                "  `answer3` varchar(200),\n" +
                "  `answer4` varchar(200),\n" +
                "  `campaign_id` varchar(500) NOT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        db.execSQL(create_questions_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void InsertRootInfo(){//this is a method that can be used to insert data or just run SQL manually during development
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //Returns a String array with all of the questions asked for a specific campaign -> uses the campaign id that links a question to a campaign
    public String[] getQuestions(String campaignID){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resultSet = db.rawQuery("select questionText from questions where campaign_id = ?",new String[]{campaignID});
        resultSet.moveToFirst();
        String[] returnString = new String[resultSet.getCount()];
        for(int i = 0; i < resultSet.getCount(); i++){
            returnString[i] = resultSet.getString(0);
            resultSet.moveToNext();
        }
        return returnString;
    }

    //returns all answers that are supplied to a question that a consumer can choose
    public String[] getAnswers(String questionText){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resultSet1 = db.rawQuery("select answer1 from questions where questionText = ?",new String[]{questionText});
        Cursor resultSet2 = db.rawQuery("select answer2 from questions where questionText = ?",new String[]{questionText});
        Cursor resultSet3 = db.rawQuery("select answer3 from questions where questionText = ?",new String[]{questionText});
        Cursor resultSet4 = db.rawQuery("select answer4 from questions where questionText = ?",new String[]{questionText});

        resultSet1.moveToFirst();
        resultSet2.moveToFirst();
        resultSet3.moveToFirst();
        resultSet4.moveToFirst();

        String returnString[] = new String[4];

        returnString[0] = resultSet1.getString(0);
        returnString[1] = resultSet2.getString(0);
        returnString[2] = resultSet3.getString(0);
        returnString[3] = resultSet4.getString(0);


        Log.d("return", "getAnswers: " + returnString[0]);
        return returnString;
    }

    //Returns the question ID when supplied the text of the question
    public int getQuestionId(String questionText){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resultSet = db.rawQuery("select id from questions where questionText = ?", new String[]{questionText});
        resultSet.moveToFirst();

        return resultSet.getInt(0);
    }
}
