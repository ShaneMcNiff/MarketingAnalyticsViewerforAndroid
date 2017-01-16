package com.example.shane.MAV;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import static android.content.ContentValues.TAG;

import com.example.shane.MAV.R;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }


    public void HomeClicked(View view){
        Log.d(TAG, "HomeClicked: Success");
    }
    public void PerformanceClicked(View view){
        Log.d(TAG, "PerformanceClicked: Success");
    }
    public void SummaryClicked(View view){
        Log.d(TAG, "SummaryClicked: Success");
    }
    public void LogoutClicked(View view){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

}
