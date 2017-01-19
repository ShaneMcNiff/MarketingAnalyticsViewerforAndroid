package com.example.shane.MAV;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import static android.content.ContentValues.TAG;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Spinner dropdown = (Spinner)findViewById(R.id.dropdown);
        ArrayList<String> list = new ArrayList<>();
        list.add("Campaign1");
        list.add("Campaign2");
        list.add("Campaign3");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
        dropdown.setAdapter(adapter);
    }


    public void NavigateCampaignScreen(View view){
        Intent intent = new Intent(this, CampaignScreen.class);
        startActivity(intent);
    }
    public void PerformanceClicked(View view){
        Log.d(TAG, "PerformanceClicked: Success");
    }
    public void SummaryClicked(View view){
        Log.d(TAG, "SummaryClicked: Success");
    }
    public void LogoutClicked(View view){
        /*Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        */
        finish();
    }

}
