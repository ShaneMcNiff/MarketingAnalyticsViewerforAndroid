package com.example.shane.MAV.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shane on 16/03/2017.
 */

public class Campaign_profiles_DB extends SQLiteOpenHelper {

    public Campaign_profiles_DB (Context context){super (context, "Campaign_profiles",null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_campaign_profiles_table = "CREATE TABLE `campaign_profiles` (\n" +
                "  `id` int(11) not null,\n" +
                "  `campaign_id` int(11) NOT NULL,\n" +
                "  `profile_id` int(11) NOT NULL,\n" +
                "  `valid_entry_count` int(11) not null,\n" +
                "  `invalid_entry_count` int(11) NOT NULL,\n" +
                "  `create_date` datetime DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        db.execSQL(create_campaign_profiles_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void InsertRootInfo(){

    }
}
