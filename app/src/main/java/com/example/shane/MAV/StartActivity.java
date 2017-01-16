package com.example.shane.MAV;

//Big oal test here for Git

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ((TextView)findViewById(R.id.LoginConfirm)).setText("");
    }

    public void onLogin(View view){
        BackgroundWorker worker = new BackgroundWorker();
        worker.execute();

        String email = findViewById(R.id.input_email).toString();
        String password = findViewById(R.id.input_password).toString();

        TextView text = ((TextView)findViewById(R.id.LoginConfirm));
        text.setText("Login successful");


        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);

    }
}