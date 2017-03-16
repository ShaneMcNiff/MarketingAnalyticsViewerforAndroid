package com.example.shane.MAV;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    User currentUser;
    TextView nameText, emailText;
    Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        Bundle extras = getIntent().getExtras();
        String email = extras.getString("Email");
        String name = extras.getString("Name");
        String password = extras.getString("Password");
        int is_Admin = extras.getInt("is_Admin");
        currentUser = new User(name, email, password, is_Admin);

        nameText = (TextView) findViewById(R.id.nameText);
        emailText = (TextView) findViewById(R.id.emailText);
        nameText.setText(currentUser.getName());
        emailText.setText(currentUser.getEmail());

        dropdown =  (Spinner)findViewById(R.id.dropdown);

        ArrayList<String> list = new ArrayList<>();
        list.add("Campaign1");
        list.add("Campaign2");
        list.add("Campaign3");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
        dropdown.setAdapter(adapter);
    }


    public void NavigateCampaignScreen(View view){
        Intent intent = new Intent(this, CampaignHome.class);
        intent.putExtra("Name", currentUser.getName());
        intent.putExtra("Email",currentUser.getEmail());
        intent.putExtra("Password",currentUser.getPassword());
        intent.putExtra("is_Admin",currentUser.getAdminStatus());
        intent.putExtra("CampaignName",dropdown.getSelectedItem().toString());
        startActivity(intent);
    }
    public void LogoutClicked(View view){
        /*Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        */
        finish();
    }

}
