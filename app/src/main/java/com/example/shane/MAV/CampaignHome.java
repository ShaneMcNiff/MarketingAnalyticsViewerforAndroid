package com.example.shane.MAV;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CampaignHome extends AppCompatActivity {

    User currentUser;
    TextView entriesValue, profilesValue, campaignText;
    String campaignName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_screen);

        Bundle extras = getIntent().getExtras();
        String email = extras.getString("Email");
        String name = extras.getString("Name");
        String password = extras.getString("Password");
        campaignName = extras.getString("CampaignName");
        int is_Admin = extras.getInt("is_Admin");
        currentUser = new User(name, email, password, is_Admin);

        Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        ArrayList<String> list = new ArrayList<>();
        list.add("Home");
        list.add("Summary");
        list.add("Performance");
        list.add("Messages");
        list.add("Goals");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
        dropdown.setAdapter(adapter);

        campaignText = (TextView)findViewById(R.id.campaignName);
        entriesValue = (TextView)findViewById(R.id.entriesValue);
        profilesValue = (TextView)findViewById(R.id.profilesValue);

        campaignText.setText(campaignName);
        ProgressBar entriesProgressBar = (ProgressBar)findViewById(R.id.progressBarEntries);
        entriesProgressBar.setMax(1000);
        entriesProgressBar.incrementProgressBy(512);
        entriesValue.setText("" + entriesProgressBar.getProgress());

        ProgressBar profilesProgressBar = (ProgressBar)findViewById(R.id.progressBarProfiles);
        profilesProgressBar.setMax(500);
        profilesProgressBar.incrementProgressBy(125);
        profilesValue.setText("" + profilesProgressBar.getProgress());
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });


    }
}
