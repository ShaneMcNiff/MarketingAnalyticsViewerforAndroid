package com.example.shane.MAV;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shane on 16/03/2017.
 */

public class Campaigns_DB extends SQLiteOpenHelper {

    public Campaigns_DB (Context context){super(context,"Campaigns", null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_campaigns_table = "CREATE TABLE `campaigns` (\n" +
                "  `id` int(11) NOT NULL auto_increment,\n" +
                "  `campaign_name` varchar(128) NOT NULL,\n" +
                "  `description` varchar(500) NOT NULL,\n" +
                "  `country_id` int(4) NOT NULL,\n" +
                "  `client_id` int(4) NOT NULL,\n" +
                "  `current_entry_count` int(11) NOT NULL,\n" +
                "  `estimated_entry_count` int(11) DEFAULT NULL,\n" +
                "  `positive_responses` int(11) DEFAULT NULL,\n" +
                "  `negative_responses` int(11) NOT NULL,\n" +
                "  `start_date` datetime NOT NULL,\n" +
                "  `end_date` datetime NOT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        db.execSQL(create_campaigns_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void InsertRootInfo(){
        SQLiteDatabase db = this.getWritableDatabase();
        String insert_into_campaigns = "insert into campaigns values (1, 'Spar first try', 'Spar want to see how happy their customers are in a specific shop using the Happy or Not scale', 1, 1, 542, 1000, 463, 79, '01-01-2017', '01-05-2017');";
        db.execSQL(insert_into_campaigns);
    }
}
