package com.example.shane.MAV.MAV_UI;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shane.MAV.Database.Campaigns_DB;
import com.example.shane.MAV.Database.Question_Campaign_DB;
import com.example.shane.MAV.R;
import com.example.shane.MAV.Objects.User;

import java.util.ArrayList;
import java.util.Collections;

public class MainScreen extends AppCompatActivity {

    User currentUser;
    TextView nameText, emailText;
    Spinner dropdown;
    Campaigns_DB campaigns_db;
    Question_Campaign_DB question_campaign_db;
    String campaignName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        Bundle extras = getIntent().getExtras();
        //Grab all details passed from previous activity
        String email = extras.getString("Email");
        String name = extras.getString("Name");
        String password = extras.getString("Password");
        int is_Admin = extras.getInt("is_Admin");
        currentUser = new User(name, email, password, is_Admin);

        nameText = (TextView) findViewById(R.id.nameText);
        emailText = (TextView) findViewById(R.id.emailText);
        nameText.setText(currentUser.getName());
        emailText.setText(currentUser.getEmail());

        //This spinner object is different to rest of application, reason for it being named differently
        dropdown =  (Spinner)findViewById(R.id.dropdown);

        campaigns_db = new Campaigns_DB(this);
        question_campaign_db = new Question_Campaign_DB(this);

        //Get all active campaigns and add them into the spinner to be selected by the user
        //Potential to add user designated campaigns only if needed -> just add check for said
        ArrayList<String> first = campaigns_db.getActiveCampaigns();
        ArrayList<String> second = question_campaign_db.getActiveCampaigns();
        first.addAll(second);

        Collections.sort(first);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, first);
        dropdown.setAdapter(adapter);
    }


    public void NavigateCampaignScreen(View view){
        campaignName = dropdown.getSelectedItem().toString();
        Intent intent = new Intent(this, CampaignHome.class);
        intent.putExtra("Name", currentUser.getName());
        intent.putExtra("Email",currentUser.getEmail());
        intent.putExtra("Password",currentUser.getPassword());
        intent.putExtra("is_Admin",currentUser.getAdminStatus());
        //Passing campaign name in order to get campaigns data from DB -> Could use campaign id if needed, just look for ID instead of name
        intent.putExtra("CampaignName",campaignName);
        startActivity(intent);
    }
    public void LogoutClicked(View view){
        //Pops current activity from the stack and returns to previous
        finish();
    }

}
