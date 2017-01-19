package com.example.shane.MAV;

//Big oal test here for Git

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);




        ((TextView)findViewById(R.id.LoginConfirm)).setText("");
    }

    public void onLogin(View view){

        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);

        /*BackgroundWorker worker = new BackgroundWorker();
        worker.execute();

        String email = findViewById(R.id.input_email).toString();
        String password = findViewById(R.id.input_password).toString();*/
    }
}