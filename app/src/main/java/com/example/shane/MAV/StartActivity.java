package com.example.shane.MAV;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    EditText Email, Password;
    TextView popup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        popup = (TextView) findViewById(R.id.popup);
        popup.setVisibility(View.INVISIBLE);
        Email = (EditText)findViewById(R.id.input_email);
        Password = (EditText)findViewById(R.id.input_password);
    }

    public void onLogin(View view){

        String email = Email.getText().toString();
        String password = Password.getText().toString();

        InitialiseDB();

        Users_DB user_db = new Users_DB(this);


        boolean isUser = user_db.userExists(email, password);
        if (isUser) {
            User user = user_db.getUserDetails(email, password);
            Intent intent = new Intent(this, MainScreen.class);
            intent.putExtra("Email", user.getEmail());
            intent.putExtra("Name", user.getName());
            intent.putExtra("Password", user.getPassword());
            intent.putExtra("is_Admin", user.getAdminStatus());
            startActivity(intent);
        }else{
            popup.setVisibility(View.VISIBLE);
        }
    }

    public void InitialiseDB(){
        Users_DB user_db = new Users_DB(this);
        user_db.InsertRootInfo();
        Countries_DB countries_db = new Countries_DB(this);
        countries_db.InsertRootInfo();
        Clients_DB clients_db = new Clients_DB(this);
        clients_db.InsertRootInfo();
        Campaign_profiles_DB campaign_profiles_db = new Campaign_profiles_DB(this);
        campaign_profiles_db.InsertRootInfo();
        Campaigns_DB campaigns_db = new Campaigns_DB(this);
        campaigns_db.InsertRootInfo();
        Campaign_entries_DB campaign_entries_db = new Campaign_entries_DB(this);
        campaign_entries_db.InsertRootInfo();
    }
}

/* LoginWorker worker = new LoginWorker();
        worker.onCreate();
       */