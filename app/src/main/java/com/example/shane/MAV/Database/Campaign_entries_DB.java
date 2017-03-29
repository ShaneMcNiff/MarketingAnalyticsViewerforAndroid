package com.example.shane.MAV.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shane on 16/03/2017.
 */

public class Campaign_entries_DB extends SQLiteOpenHelper {

    public Campaign_entries_DB(Context context){super(context,"Campaign_entries",null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_campaign_entries_table = "create table `campaign_entries`(`entry_id` int(11) not null,\n" +
                "    `campaign_id` int(11) not null,\n" +
                "    `profile_id` int(11) not null,\n" +
                "    `valid_entry` bool not null,\n" +
                "    `feedback` int(2) not null,\n" +
                "    primary key (`entry_id`));";
        db.execSQL(create_campaign_entries_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void InsertRootInfo(){
        SQLiteDatabase db = this.getWritableDatabase();

        /*for(int i = 0; i < 463; i++){
            db.execSQL("insert into campaign_entries values(" + i + ", 1, " + i + ", true, 1);");
        }for(int i = 463; i < 542; i++){
            db.execSQL("insert into campaign_entries values(" + i + ", 1, " + i + ", true, 0);");
        }*/
    }
}
