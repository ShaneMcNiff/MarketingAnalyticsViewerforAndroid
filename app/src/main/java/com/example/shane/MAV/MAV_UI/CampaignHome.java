package com.example.shane.MAV.MAV_UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shane.MAV.Database.Campaigns_DB;
import com.example.shane.MAV.Database.Clients_DB;
import com.example.shane.MAV.Database.Question_Campaign_DB;
import com.example.shane.MAV.Database.Questions_DB;
import com.example.shane.MAV.Objects.Campaign;
import com.example.shane.MAV.Objects.QuestionCampaign;
import com.example.shane.MAV.R;
import com.example.shane.MAV.Objects.User;

import java.util.ArrayList;

public class CampaignHome extends AppCompatActivity{

    User currentUser;
    TextView setCampaignName, setClientName, setDescription, setGoals, setStartDate, setEndDate;
    String campaignName = "";
    Campaigns_DB campaigns_db;
    Question_Campaign_DB question_campaign_db;
    Questions_DB questions_db;
    Clients_DB clients_db;
    Spinner dropdown;
    Campaign campaign;
    QuestionCampaign questionCampaign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_screen);

        //Initialise everything
        campaigns_db = new Campaigns_DB(this);
        question_campaign_db = new Question_Campaign_DB(this);
        clients_db = new Clients_DB(this);
        questions_db = new Questions_DB(this);
        setCampaignName = (TextView)findViewById(R.id.setCampaignName);
        setClientName = (TextView)findViewById(R.id.setClientName);
        setDescription = (TextView)findViewById(R.id.setDescription);
        setGoals = (TextView)findViewById(R.id.setGoals);
        setStartDate = (TextView)findViewById(R.id.setStartDate);
        setEndDate = (TextView)findViewById(R.id.setEndDate);

        Bundle extras = getIntent().getExtras();
        String email = extras.getString("Email");
        String name = extras.getString("Name");
        String password = extras.getString("Password");
        campaignName = extras.getString("CampaignName");
        int is_Admin = extras.getInt("is_Admin");
        currentUser = new User(name, email, password, is_Admin);

        setSpinner();//Set the Spinner object

        setText();//Sets all texts depending on what type of campaign is being viewed


    }

    public void setText(){
        //If another type of campaign is added, just add another chack for that type of campaign and set texts appropriately
        if(isSimpleCampaign()) {
            campaign = campaigns_db.getCampaignDetails(campaignName);
            String ClientName = clients_db.getClientNameFromID(campaign.getClientID());
            setCampaignName.setText(campaign.getCampaignName());
            setClientName.setText(ClientName);
            setDescription.setText(campaign.getDescription());
            setGoals.setText(campaign.getEstimatedEntryCount() + " Entries");
            setStartDate.setText(campaigns_db.getCampaignStartDate(campaignName));
            setEndDate.setText(campaigns_db.getCampaignEndDate(campaignName));
        }else{
            questionCampaign = question_campaign_db.getCampaignDetails(campaignName);
            String ClientName = clients_db.getClientNameFromID(questionCampaign.getClientID());
            setCampaignName.setText(questionCampaign.getCampaignName());
            setClientName.setText(ClientName);
            setDescription.setText(questionCampaign.getDescription());
            setGoals.setText(questionCampaign.getEstimatedEntryCount() + " Entries");
            setStartDate.setText(questionCampaign.getStartDate().toString());
            setEndDate.setText(questionCampaign.getEndDate().toString());
        }
    }

    public boolean isSimpleCampaign(){//simple method to find out what type of campaign is currently being viewed using the name of the campaign
        ArrayList<String> questionCampaigns = question_campaign_db.getActiveCampaigns();
        ArrayList<String> simpleCampaigns = campaigns_db.getActiveCampaigns();
        if(questionCampaigns.contains(campaignName)){
            questionCampaign = (question_campaign_db.getCampaignDetails(campaignName));
            return false;
        }else if(simpleCampaigns.contains(campaignName)){
            return true;
        }
        return false;
    }

    //This Spinner is used in every activity from here in -> it is able to handle both types of campaigns that are currently being used
    public void setSpinner() {

                                if (!isSimpleCampaign()) {//question campaign spinner settings -> same all the way through the project

                                    String campaignID = questionCampaign.getCampaignID() + "";//is needed as a string
                                    String []questionsAsked = questions_db.getQuestions(campaignID);

                                    dropdown = (Spinner) findViewById(R.id.spinner);
                                    ArrayList<String> list = new ArrayList<>();
                                    list.add("Select Tab");
                                    list.add("Home");
                                    list.add("Summary");
                                    list.add("Performance");
                                    list.add("Goals");

                                    //Add tab in the spinner for each question asked in the campaign
                                    for (int i = 0; i < questionsAsked.length; i++) {
                                        list.add(questionsAsked[i]);
                                    }

                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
                                    dropdown.setAdapter(adapter);

                                    dropdown = (Spinner) findViewById(R.id.spinner);
                                    dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                            //This accommodates all questions that are being asked in the campaign -> they all use the same activity
                                            if (position > 4) {
                                                Intent questionIntent = new Intent(CampaignHome.this, QuestionAnswerSummary.class);
                                                questionIntent.putExtra("Name", currentUser.getName());
                                                questionIntent.putExtra("Email", currentUser.getEmail());
                                                questionIntent.putExtra("Password", currentUser.getPassword());
                                                questionIntent.putExtra("is_Admin", currentUser.getAdminStatus());
                                                questionIntent.putExtra("CampaignName", campaignName);
                                                //I am passing the question text to the next activity to get the question details from the DB -> Could easily be changed to something like an ID if needed
                                                questionIntent.putExtra("Question", dropdown.getSelectedItem().toString());
                                                CampaignHome.this.startActivity(questionIntent);
                                                finish();
                                            } else {
                                                switch (position) {
                                                    case (0):
                                                        break;
                                                    case (1):
                                                        break;
                                                    case (2):
                                Intent summaryIntent = new Intent(CampaignHome.this, CampaignSummary.class);
                                summaryIntent.putExtra("Name", currentUser.getName());
                                summaryIntent.putExtra("Email", currentUser.getEmail());
                                summaryIntent.putExtra("Password", currentUser.getPassword());
                                summaryIntent.putExtra("is_Admin", currentUser.getAdminStatus());
                                summaryIntent.putExtra("CampaignName", campaignName);
                                CampaignHome.this.startActivity(summaryIntent);
                                finish();
                                break;
                            case (3):
                                Intent performanceIntent = new Intent(CampaignHome.this, CampaignPerformance.class);
                                performanceIntent.putExtra("Name", currentUser.getName());
                                performanceIntent.putExtra("Email", currentUser.getEmail());
                                performanceIntent.putExtra("Password", currentUser.getPassword());
                                performanceIntent.putExtra("is_Admin", currentUser.getAdminStatus());
                                performanceIntent.putExtra("CampaignName", campaignName);
                                CampaignHome.this.startActivity(performanceIntent);
                                finish();
                                break;
                            case (4):
                                Intent goalsIntent = new Intent(CampaignHome.this, CampaignGoals.class);
                                goalsIntent.putExtra("Name", currentUser.getName());
                                goalsIntent.putExtra("Email", currentUser.getEmail());
                                goalsIntent.putExtra("Password", currentUser.getPassword());
                                goalsIntent.putExtra("is_Admin", currentUser.getAdminStatus());
                                goalsIntent.putExtra("CampaignName", campaignName);
                                CampaignHome.this.startActivity(goalsIntent);
                                finish();
                                break;

                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {//Simple campaign spinner settings -> same all the way through the project
            dropdown = (Spinner) findViewById(R.id.spinner);
            ArrayList<String> list = new ArrayList<>();
            list.add("Select Tab");
            list.add("Home");
            list.add("Summary");
            list.add("Performance");
            list.add("Goals");
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
            dropdown.setAdapter(adapter);

            dropdown = (Spinner) findViewById(R.id.spinner);
            //dropdown.setOnItemSelectedListener(new CustomSpinnerActivity());
            dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case (0):
                            break;
                        case (1):
                            break;
                        case (2):
                            Intent summaryIntent = new Intent(CampaignHome.this, CampaignSummary.class);
                            summaryIntent.putExtra("Name", currentUser.getName());
                            summaryIntent.putExtra("Email", currentUser.getEmail());
                            summaryIntent.putExtra("Password", currentUser.getPassword());
                            summaryIntent.putExtra("is_Admin", currentUser.getAdminStatus());
                            summaryIntent.putExtra("CampaignName", campaignName);
                            CampaignHome.this.startActivity(summaryIntent);
                            finish();
                            break;
                        case (3):
                            Intent performanceIntent = new Intent(CampaignHome.this, CampaignPerformance.class);
                            performanceIntent.putExtra("Name", currentUser.getName());
                            performanceIntent.putExtra("Email", currentUser.getEmail());
                            performanceIntent.putExtra("Password", currentUser.getPassword());
                            performanceIntent.putExtra("is_Admin", currentUser.getAdminStatus());
                            performanceIntent.putExtra("CampaignName", campaignName);
                            CampaignHome.this.startActivity(performanceIntent);
                            finish();
                            break;
                        case (4):
                            Intent goalsIntent = new Intent(CampaignHome.this, CampaignGoals.class);
                            goalsIntent.putExtra("Name", currentUser.getName());
                            goalsIntent.putExtra("Email", currentUser.getEmail());
                            goalsIntent.putExtra("Password", currentUser.getPassword());
                            goalsIntent.putExtra("is_Admin", currentUser.getAdminStatus());
                            goalsIntent.putExtra("CampaignName", campaignName);
                            CampaignHome.this.startActivity(goalsIntent);
                            finish();
                            break;

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }
}