package com.example.shane.MAV.MAV_UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shane.MAV.Database.Campaigns_DB;
import com.example.shane.MAV.Database.Question_Campaign_DB;
import com.example.shane.MAV.Database.Questions_DB;
import com.example.shane.MAV.Objects.Campaign;
import com.example.shane.MAV.Objects.QuestionCampaign;
import com.example.shane.MAV.R;
import com.example.shane.MAV.Objects.User;

import java.util.ArrayList;

public class CampaignGoals extends AppCompatActivity {

    String campaignName = "";
    User currentUser;
    Spinner dropdown;
    TextView setProfiles, setEntries, setFailed, setSuccess;
    Campaigns_DB campaigns_db;
    QuestionCampaign questionCampaign;
    Question_Campaign_DB question_campaign_db;
    Questions_DB questions_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_goals);
        campaigns_db = new Campaigns_DB(this);
        question_campaign_db = new Question_Campaign_DB(this);
        questions_db = new Questions_DB(this);

        Bundle extras = getIntent().getExtras();
        String email = extras.getString("Email");
        String name = extras.getString("Name");
        String password = extras.getString("Password");
        campaignName = extras.getString("CampaignName");
        int is_Admin = extras.getInt("is_Admin");
        currentUser = new User(name, email, password, is_Admin);

        setTexts();
        setSpinner();

    }

    public void setTexts(){
        if(isSimpleCampaign()) {
            Campaign campaign = campaigns_db.getCampaignDetails(campaignName);

            setEntries = (TextView) findViewById(R.id.setEntries);
            setProfiles = (TextView) findViewById(R.id.setProfiles);
            setFailed = (TextView) findViewById(R.id.setFail);
            setSuccess = (TextView) findViewById(R.id.setSuccess);

            setEntries.setText("" + campaign.getEstimatedEntryCount());
            setProfiles.setText("Profiles not created by campaign");
            setFailed.setText("Campaign has a 50% negative response");
            setSuccess.setText("Campaign has 75% positive response");
        }else{
            QuestionCampaign questionCampaign = question_campaign_db.getCampaignDetails(campaignName);

            setEntries = (TextView) findViewById(R.id.setEntries);
            setProfiles = (TextView) findViewById(R.id.setProfiles);
            setFailed = (TextView) findViewById(R.id.setFail);
            setSuccess = (TextView) findViewById(R.id.setSuccess);

            setEntries.setText("" + questionCampaign.getEstimatedEntryCount());
            setProfiles.setText("" + questionCampaign.getEstimatedEntryCount());
            setFailed.setText("Less than 100 entries in the required time frame.");
            setSuccess.setText("Campaign receives mostly positive feedback.");
        }
    }

    public boolean isSimpleCampaign(){
        ArrayList<String> questionCamapaigns = question_campaign_db.getActiveCampaigns();
        ArrayList<String> simpleCamapaigns = campaigns_db.getActiveCampaigns();
        if(questionCamapaigns.contains(campaignName)){
            questionCampaign = (question_campaign_db.getCampaignDetails(campaignName));
            return false;
        }else if(simpleCamapaigns.contains(campaignName)){
            return true;
        }
        return false;
    }

    public void setSpinner() {

        if (!isSimpleCampaign()) {

            Log.d("questionCampaign", "setSpinner: " + questionCampaign.getCampaignID());
            String campaignID = questionCampaign.getCampaignID() + "";
            String []questionsAsked = questions_db.getQuestions(campaignID);

            Log.d("Questionnaire", "setSpinner: " + questionsAsked[0]);
            dropdown = (Spinner) findViewById(R.id.spinner);
            ArrayList<String> list = new ArrayList<>();
            list.add("Select Tab");
            list.add("Home");
            list.add("Summary");
            list.add("Performance");
            list.add("Goals");

            for (int i = 0; i < questionsAsked.length; i++) {
                list.add(questionsAsked[i]);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
            dropdown.setAdapter(adapter);

            dropdown = (Spinner) findViewById(R.id.spinner);
            //dropdown.setOnItemSelectedListener(new CustomSpinnerActivity());
            dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position > 4) {
                        Intent questionIntent = new Intent(CampaignGoals.this, QuestionAnswerSummary.class);
                        questionIntent.putExtra("Name", currentUser.getName());
                        questionIntent.putExtra("Email", currentUser.getEmail());
                        questionIntent.putExtra("Password", currentUser.getPassword());
                        questionIntent.putExtra("is_Admin", currentUser.getAdminStatus());
                        questionIntent.putExtra("CampaignName", campaignName);
                        questionIntent.putExtra("Question", dropdown.getSelectedItem().toString());
                        CampaignGoals.this.startActivity(questionIntent);
                        finish();
                    } else {
                        switch (position){
                            case (0):break;
                            case (1):
                                Intent homeIntent = new Intent(CampaignGoals.this, CampaignHome.class);
                                homeIntent.putExtra("Name", currentUser.getName());
                                homeIntent.putExtra("Email",currentUser.getEmail());
                                homeIntent.putExtra("Password",currentUser.getPassword());
                                homeIntent.putExtra("is_Admin",currentUser.getAdminStatus());
                                homeIntent.putExtra("CampaignName",campaignName);
                                CampaignGoals.this.startActivity(homeIntent);finish();
                                break;
                            case (2):
                                Intent summaryIntent = new Intent(CampaignGoals.this, CampaignSummary.class);
                                summaryIntent.putExtra("Name", currentUser.getName());
                                summaryIntent.putExtra("Email",currentUser.getEmail());
                                summaryIntent.putExtra("Password",currentUser.getPassword());
                                summaryIntent.putExtra("is_Admin",currentUser.getAdminStatus());
                                summaryIntent.putExtra("CampaignName",campaignName);
                                CampaignGoals.this.startActivity(summaryIntent);finish();break;
                            case (3):
                                Intent perforemanceIntent = new Intent(CampaignGoals.this, CampaignPerforemance.class);
                                perforemanceIntent.putExtra("Name", currentUser.getName());
                                perforemanceIntent.putExtra("Email",currentUser.getEmail());
                                perforemanceIntent.putExtra("Password",currentUser.getPassword());
                                perforemanceIntent.putExtra("is_Admin",currentUser.getAdminStatus());
                                perforemanceIntent.putExtra("CampaignName",campaignName);
                                CampaignGoals.this.startActivity(perforemanceIntent);finish();break;
                            case (4):break;

                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
            dropdown = (Spinner)findViewById(R.id.spinner);
            ArrayList<String> list = new ArrayList<>();
            list.add("Select Tab");
            list.add("Home");
            list.add("Summary");
            list.add("Performance");
            list.add("Goals");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
            dropdown.setAdapter(adapter);

            dropdown = (Spinner) findViewById(R.id.spinner);
            //dropdown.setOnItemSelectedListener(new CustomSpinnerActivity());
            dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position){
                        case (0):break;
                        case (1):
                            Intent homeIntent = new Intent(CampaignGoals.this, CampaignHome.class);
                            homeIntent.putExtra("Name", currentUser.getName());
                            homeIntent.putExtra("Email",currentUser.getEmail());
                            homeIntent.putExtra("Password",currentUser.getPassword());
                            homeIntent.putExtra("is_Admin",currentUser.getAdminStatus());
                            homeIntent.putExtra("CampaignName",campaignName);
                            CampaignGoals.this.startActivity(homeIntent);finish();
                            break;
                        case (2):
                            Intent summaryIntent = new Intent(CampaignGoals.this, CampaignSummary.class);
                            summaryIntent.putExtra("Name", currentUser.getName());
                            summaryIntent.putExtra("Email",currentUser.getEmail());
                            summaryIntent.putExtra("Password",currentUser.getPassword());
                            summaryIntent.putExtra("is_Admin",currentUser.getAdminStatus());
                            summaryIntent.putExtra("CampaignName",campaignName);
                            CampaignGoals.this.startActivity(summaryIntent);finish();break;
                        case (3):
                            Intent perforemanceIntent = new Intent(CampaignGoals.this, CampaignPerforemance.class);
                            perforemanceIntent.putExtra("Name", currentUser.getName());
                            perforemanceIntent.putExtra("Email",currentUser.getEmail());
                            perforemanceIntent.putExtra("Password",currentUser.getPassword());
                            perforemanceIntent.putExtra("is_Admin",currentUser.getAdminStatus());
                            perforemanceIntent.putExtra("CampaignName",campaignName);
                            CampaignGoals.this.startActivity(perforemanceIntent);finish();break;
                        case (4):break;

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }
}
