package com.example.shane.MAV.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shane.MAV.Objects.Campaign;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Shane on 16/03/2017.
 */

public class Campaigns_DB extends SQLiteOpenHelper {

    private Date startDate;
    private Date endDate;

    public Campaigns_DB (Context context){super(context,"Campaigns", null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_campaigns_table = "CREATE TABLE `campaigns` (\n" +
                "  `id` int(11) NOT NULL,\n" +
                "  `campaign_name` varchar(128) NOT NULL,\n" +
                "  `description` varchar(500) NOT NULL,\n" +
                "  `country_id` int(4) NOT NULL,\n" +
                "  `client_id` int(4) NOT NULL,\n" +
                "  `current_entry_count` int(11) NOT NULL,\n" +
                "  `estimated_entry_count` int(11) DEFAULT NULL,\n" +
                "  `failed_entries` int(11) DEFAULT NULL,\n" +
                "  `positive_responses` int(11) DEFAULT NULL,\n" +
                "  `negative_responses` int(11) NOT NULL,\n" +
                "  `start_date` datetime DEFAULT NOT NULL,\n" +
                "  `end_date` datetime DEFAULT NOT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        db.execSQL(create_campaigns_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void InsertRootInfo(){
        SQLiteDatabase db = this.getWritableDatabase();
        String insert_into_campaigns = "insert into campaigns values (1, 'Spar Customer Satisfaction Survey', 'Spar want to see how happy their customers are in a specific shop using the Happy or Not scale', 1, 1, 542, 1000, 136, 463, 79, '01-01-2017', '01-05-2017');";
        String change = "update campaigns set `start_date` = '01-01-2017' where `id` = 1";
        String drop = "drop table campaigns";
        String create_campaigns_table = "CREATE TABLE `campaigns` (\n" +
                "  `id` int(11) NOT NULL,\n" +
                "  `campaign_name` varchar(128) NOT NULL,\n" +
                "  `description` varchar(500) NOT NULL,\n" +
                "  `country_id` int(4) NOT NULL,\n" +
                "  `client_id` int(4) NOT NULL,\n" +
                "  `current_entry_count` int(11) NOT NULL,\n" +
                "  `estimated_entry_count` int(11) DEFAULT NULL,\n" +
                "  `failed_entries` int(11) DEFAULT NULL,\n" +
                "  `positive_responses` int(11) DEFAULT NULL,\n" +
                "  `negative_responses` int(11) NOT NULL,\n" +
                "  `start_date` datetime NOT NULL,\n" +
                "  `end_date` datetime NOT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        db.execSQL(change);
    }

    public ArrayList<String> getActiveCampaigns(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resultSet = db.rawQuery("Select campaign_name from campaigns",new String[]{});
        resultSet.moveToFirst();
        ArrayList<String> campaignNames = new ArrayList<>();
        for(int i = 0; i < resultSet.getCount(); i++){
            campaignNames.add(resultSet.getString(0));
        }
        return campaignNames;
    }

    public Campaign getCampaignDetails(String campaignName){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] constraint = {campaignName};

        Cursor resultSet = db.rawQuery("Select * from campaigns where campaign_name = ?",constraint);
        resultSet.moveToFirst();

        String returnedStartDate = resultSet.getString(9);
        String returnedEndDate = resultSet.getString(10);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try{
            startDate = df.parse(returnedStartDate);
            endDate = df.parse(returnedEndDate);
        }catch (ParseException e) {}

        Campaign returnedCampaign = new Campaign(resultSet.getInt(0),resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getInt(8), resultSet.getInt(9),startDate,endDate);

        return returnedCampaign;
    }

    public String getCampaignStartDate(String campaignName){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] constraint = {campaignName};

        Cursor resultSet = db.rawQuery("Select start_date from campaigns where campaign_name = ?",constraint);
        resultSet.moveToFirst();

        String returnedStartDate = resultSet.getString(0);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try{
            startDate = df.parse(returnedStartDate);
        }catch (ParseException e) {}
        return startDate.toString();
    }

    public String getCampaignEndDate(String campaignName){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] constraint = {campaignName};

        Cursor resultSet = db.rawQuery("Select end_date from campaigns where campaign_name = ?",constraint);
        resultSet.moveToFirst();

        String returnedStartDate = resultSet.getString(0);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try{
            startDate = df.parse(returnedStartDate);
        }catch (ParseException e) {}
        return startDate.toString();
    }

}

