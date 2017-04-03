package com.example.shane.MAV.MAV_UI;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shane.MAV.Database.Campaigns_DB;
import com.example.shane.MAV.Database.Clients_DB;
import com.example.shane.MAV.Database.Countries_DB;
import com.example.shane.MAV.Database.Question_Campaign_DB;
import com.example.shane.MAV.Database.Questions_DB;
import com.example.shane.MAV.Database.Responses_DB;
import com.example.shane.MAV.Database.Users_DB;
import com.example.shane.MAV.R;
import com.example.shane.MAV.Objects.User;

public class StartActivity extends AppCompatActivity {

    EditText Email, Password;
    TextView popup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        popup = (TextView) findViewById(R.id.popup);//Getting the 'wrong email/password' message ready here
        popup.setVisibility(View.INVISIBLE);
        Email = (EditText)findViewById(R.id.input_email);
        Password = (EditText)findViewById(R.id.input_password);
    }

    public void onLogin(View view){

        String email = Email.getText().toString();//Grab Email
        String password = Password.getText().toString();//Grab password

        //Initialise Users database here in order to keep it in scope for checking email and password
        Users_DB user_db = new Users_DB(this);
        user_db.InsertRootInfo();

        InitialiseDB();

        boolean isUser = user_db.userExists(email, password);
        if (isUser) {
            User user = user_db.getUserDetails(email, password);
            Intent intent = new Intent(this, MainScreen.class);
            //Pass along the user throughout the application in case of user specific details coming from databases
            intent.putExtra("Email", user.getEmail());
            intent.putExtra("Name", user.getName());
            intent.putExtra("Password", user.getPassword());
            intent.putExtra("is_Admin", user.getAdminStatus());
            startActivity(intent);
        }else{
            popup.setVisibility(View.VISIBLE);//Email or password was incorrect or does not exist
        }
    }

    //Initialise all databases here -> if you are pulling info from a server, you want to do that here as well
    public void InitialiseDB(){

        Countries_DB countries_db = new Countries_DB(this);
        countries_db.InsertRootInfo();
        Clients_DB clients_db = new Clients_DB(this);
        clients_db.InsertRootInfo();
        Campaigns_DB campaigns_db = new Campaigns_DB(this);
        campaigns_db.InsertRootInfo();
        Question_Campaign_DB question_campaign_db = new Question_Campaign_DB(this);
        question_campaign_db.InsertRootInfo();
        Questions_DB questions_db = new Questions_DB(this);
        questions_db.InsertRootInfo();
        Responses_DB responses_db = new Responses_DB(this);
        responses_db.InsertRootData();
    }
}
