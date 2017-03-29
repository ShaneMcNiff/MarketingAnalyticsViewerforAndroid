package com.example.shane.MAV;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.shane.MAV.MAV_UI.CampaignGoals;
import com.example.shane.MAV.MAV_UI.CampaignPerforemance;
import com.example.shane.MAV.MAV_UI.CampaignSummary;

/**
 * Created by Shane on 19/01/2017.
 */

public class CustomSpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position){
            case (1):
                Intent summaryIntent1 = new Intent(this, CampaignSummary.class);
                this.startActivity(summaryIntent1);break;
            case (2):
                Intent summaryIntent = new Intent(this, CampaignSummary.class);
                this.startActivity(summaryIntent);break;
            case (3):
                Intent perforemanceIntent = new Intent(this, CampaignPerforemance.class);
                this.startActivity(perforemanceIntent);break;
            case (4):
                Intent goalsIntent = new Intent(this, CampaignGoals.class);
                this.startActivity(goalsIntent);break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
