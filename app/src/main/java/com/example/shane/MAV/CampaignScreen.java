package com.example.shane.MAV;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class CampaignScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_screen);

        Spinner dropdown = (Spinner)findViewById(R.id.dropdown);
        ArrayList<String> list = new ArrayList<>();
        list.add("Home");
        list.add("Summary");
        list.add("Performance");
        list.add("Messages");
        list.add("Goals");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        dropdown.setAdapter(adapter);
    }
}
