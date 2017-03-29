package com.example.shane.MAV.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shane.MAV.Objects.Campaign;
import com.example.shane.MAV.Objects.QuestionCampaign;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Shane on 24/03/2017.
 */

public class Question_Campaign_DB extends SQLiteOpenHelper {

    private Date startDate;
    private Date endDate;

    public Question_Campaign_DB(Context context){super(context,"QuestionCampaigns", null, 1);}
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_campaigns_table = "CREATE TABLE `questionCampaigns` (\n" +
                "  `id` int(11) NOT NULL,\n" +
                "  `campaign_name` varchar(128) NOT NULL,\n" +
                "  `description` varchar(500) NOT NULL,\n" +
                "  `country_id` int(4) NOT NULL,\n" +
                "  `client_id` int(4) NOT NULL,\n" +
                "  `current_entry_count` int(11) NOT NULL,\n" +
                "  `estimated_entry_count` int(11) DEFAULT NULL,\n" +
                "  `failed_entries` int(11) DEFAULT NULL,\n" +
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
        String insert_into_campaigns = "insert into questionCampaigns values (1, 'Tesco Online Survey', 'Tesco are conducting an online survey to find out more about their customers.', 1, 2, 15, 100, 0, '01-01-2017', '01-05-2017');";
        String update = "update questionCampaigns set id = 2 where campaign_name = 'Tesco Online Survey'";
        db.execSQL(update);
    }

    public ArrayList<String> getActiveCampaigns(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resultSet = db.rawQuery("Select campaign_name from QuestionCampaigns",new String[]{});
        resultSet.moveToFirst();
        ArrayList<String> campaignNames = new ArrayList<>();
        for(int i = 0; i < resultSet.getCount(); i++){
            campaignNames.add(resultSet.getString(i));
        }
        return campaignNames;
    }

    public QuestionCampaign getCampaignDetails(String campaignName){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] constraint = {campaignName};

        Cursor resultSet = db.rawQuery("Select * from QuestionCampaigns where campaign_name = ?",constraint);
        resultSet.moveToFirst();

        String returnedStartDate = resultSet.getString(8);
        String returnedEndDate = resultSet.getString(9);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try{
            startDate = df.parse(returnedStartDate);
            endDate = df.parse(returnedEndDate);
        }catch (ParseException e) {}

        QuestionCampaign returnedCampaign = new QuestionCampaign(resultSet.getInt(0),resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7),startDate,endDate);

        return returnedCampaign;
    }
}
