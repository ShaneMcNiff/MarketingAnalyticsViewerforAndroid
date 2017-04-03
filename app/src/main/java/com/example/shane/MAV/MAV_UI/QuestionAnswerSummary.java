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
import com.example.shane.MAV.Database.Clients_DB;
import com.example.shane.MAV.Database.Question_Campaign_DB;
import com.example.shane.MAV.Database.Questions_DB;
import com.example.shane.MAV.Database.Responses_DB;
import com.example.shane.MAV.Objects.Campaign;
import com.example.shane.MAV.Objects.QuestionCampaign;
import com.example.shane.MAV.Objects.User;
import com.example.shane.MAV.R;

import java.util.ArrayList;

public class QuestionAnswerSummary extends AppCompatActivity {

    User currentUser;
    TextView setQuestionText, setAnswer1, setAnswer2, setAnswer3, setAnswer4, setStat1, setStat2, setStat3, setStat4;
    String campaignName = "", questionText = "";
    Campaigns_DB campaigns_db;
    Question_Campaign_DB question_campaign_db;
    Questions_DB questions_db;
    Clients_DB clients_db;
    Spinner dropdown;
    Campaign campaign;
    QuestionCampaign questionCampaign;
    Responses_DB responses_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer_summary);

        Bundle extras = getIntent().getExtras();
        String email = extras.getString("Email");
        String name = extras.getString("Name");
        String password = extras.getString("Password");
        campaignName = extras.getString("CampaignName");
        int is_Admin = extras.getInt("is_Admin");
        questionText = extras.getString("Question");
        currentUser = new User(name, email, password, is_Admin);
        question_campaign_db = new Question_Campaign_DB(this);
        questions_db = new Questions_DB(this);
        campaigns_db = new Campaigns_DB(this);
        responses_db = new Responses_DB(this);

        String []answersQuestion1 = responses_db.getStatsAnswer1();
        String []answersQuestion2 = responses_db.getStatsAnswer2();
        String []answersQuestion3 = responses_db.getStatsAnswer3();

        int id = questions_db.getQuestionId(questionText);

        setQuestionText = (TextView)findViewById(R.id.setQuestionText);
        setAnswer1 = (TextView)findViewById(R.id.setAnswer1);
        setAnswer2 = (TextView)findViewById(R.id.setAnswer2);
        setAnswer3 = (TextView)findViewById(R.id.setAnswer3);
        setAnswer4 = (TextView)findViewById(R.id.setAnswer4);
        setStat1 = (TextView)findViewById(R.id.setStat1);
        setStat2 = (TextView)findViewById(R.id.setStat2);
        setStat3 = (TextView)findViewById(R.id.setStat3);
        setStat4 = (TextView)findViewById(R.id.setStat4);


        questionCampaign = new QuestionCampaign(question_campaign_db.getCampaignDetails(campaignName));

        String answers[] = questions_db.getAnswers(questionText);

        setQuestionText.setText(questionText);
        setAnswer1.setText(answers[0]);
        setAnswer2.setText(answers[1]);
        setAnswer3.setText(answers[2]);
        setAnswer4.setText(answers[3]);

        if(id == 1){
            int answer1 = 0,answer2 = 0,answer3 = 0,answer4 = 0;
            for(int i = 0; i < answersQuestion1.length; i++){
                if(answersQuestion1[i].equals("1"))
                    answer1++;
                else if(answersQuestion1[i].equals("2"))
                    answer2++;
                else if(answersQuestion1[i].equals("3"))
                    answer3++;
                else if(answersQuestion1[i].equals("4"))
                    answer4++;
            }
            setStat1.setText(answer1 + "");
            setStat2.setText(answer2 + "");
            setStat3.setText(answer3 + "");
            setStat4.setText(answer4 + "");
        }else if(id == 2){
            int answer1 = 0,answer2 = 0,answer3 = 0,answer4 = 0;
            for(int i = 0; i < answersQuestion2.length; i++){
                if(answersQuestion1[i].equals("1"))
                    answer1++;
                else if(answersQuestion2[i].equals("2"))
                    answer2++;
                else if(answersQuestion2[i].equals("3"))
                    answer3++;
                else if(answersQuestion2[i].equals("4"))
                    answer4++;
            }
            setStat1.setText(answer1 + "");
            setStat2.setText(answer2 + "");
            setStat3.setText(answer3 + "");
            setStat4.setText(answer4 + "");
        }else if(id == 3){
            int answer1 = 0,answer2 = 0,answer3 = 0,answer4 = 0;
            for(int i = 0; i < answersQuestion3.length; i++){
                if(answersQuestion1[i].equals("1"))
                    answer1++;
                else if(answersQuestion3[i].equals("2"))
                    answer2++;
                else if(answersQuestion3[i].equals("3"))
                    answer3++;
                else if(answersQuestion3[i].equals("4"))
                    answer4++;
            }
            setStat1.setText(answer1 + "");
            setStat2.setText(answer2 + "");
            setStat3.setText(answer3 + "");
            setStat4.setText(answer4 + "");
        }

        setSpinner();
    }

    public boolean isSimpleCampaign(){
        ArrayList<String> questionCamapaigns = question_campaign_db.getActiveCampaigns();
        ArrayList<String> simpleCamapaigns = campaigns_db.getActiveCampaigns();
        if(questionCamapaigns.contains(campaignName)){
            return true;
        }else if(simpleCamapaigns.contains(campaignName)){
            return false;
        }
        return false;
    }

    public void setSpinner() {

        if (!isSimpleCampaign()) {
            Log.d("questionCampaign", "setSpinner: " + questionCampaign.getCampaignID());
            String campaignID = questionCampaign.getCampaignID() + "";
            String []questionsAsked = questions_db.getQuestions(campaignID);

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
                        Intent questionIntent = new Intent(QuestionAnswerSummary.this, QuestionAnswerSummary.class);
                        questionIntent.putExtra("Name", currentUser.getName());
                        questionIntent.putExtra("Email", currentUser.getEmail());
                        questionIntent.putExtra("Password", currentUser.getPassword());
                        questionIntent.putExtra("is_Admin", currentUser.getAdminStatus());
                        questionIntent.putExtra("CampaignName", campaignName);
                        questionIntent.putExtra("Question", dropdown.getSelectedItem().toString());
                        QuestionAnswerSummary.this.startActivity(questionIntent);
                        finish();
                    } else {
                        switch (position) {
                            case (0):
                                break;
                            case (1):
                                Intent homeIntent = new Intent(QuestionAnswerSummary.this, CampaignHome.class);
                                homeIntent.putExtra("Name", currentUser.getName());
                                homeIntent.putExtra("Email",currentUser.getEmail());
                                homeIntent.putExtra("Password",currentUser.getPassword());
                                homeIntent.putExtra("is_Admin",currentUser.getAdminStatus());
                                homeIntent.putExtra("CampaignName",campaignName);
                                QuestionAnswerSummary.this.startActivity(homeIntent);finish();
                                break;
                            case (2):
                                Intent summaryIntent = new Intent(QuestionAnswerSummary.this, CampaignSummary.class);
                                summaryIntent.putExtra("Name", currentUser.getName());
                                summaryIntent.putExtra("Email", currentUser.getEmail());
                                summaryIntent.putExtra("Password", currentUser.getPassword());
                                summaryIntent.putExtra("is_Admin", currentUser.getAdminStatus());
                                summaryIntent.putExtra("CampaignName", campaignName);
                                QuestionAnswerSummary.this.startActivity(summaryIntent);
                                finish();
                                break;
                            case (3):
                                Intent perforemanceIntent = new Intent(QuestionAnswerSummary.this, CampaignPerformance.class);
                                perforemanceIntent.putExtra("Name", currentUser.getName());
                                perforemanceIntent.putExtra("Email", currentUser.getEmail());
                                perforemanceIntent.putExtra("Password", currentUser.getPassword());
                                perforemanceIntent.putExtra("is_Admin", currentUser.getAdminStatus());
                                perforemanceIntent.putExtra("CampaignName", campaignName);
                                QuestionAnswerSummary.this.startActivity(perforemanceIntent);
                                finish();
                                break;
                            case (4):
                                Intent goalsIntent = new Intent(QuestionAnswerSummary.this, CampaignGoals.class);
                                goalsIntent.putExtra("Name", currentUser.getName());
                                goalsIntent.putExtra("Email", currentUser.getEmail());
                                goalsIntent.putExtra("Password", currentUser.getPassword());
                                goalsIntent.putExtra("is_Admin", currentUser.getAdminStatus());
                                goalsIntent.putExtra("CampaignName", campaignName);
                                QuestionAnswerSummary.this.startActivity(goalsIntent);
                                finish();
                                break;

                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
            dropdown = (Spinner) findViewById(R.id.spinner);
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
                    switch (position) {
                        case (0):
                            break;
                        case (1):
                            Intent homeIntent = new Intent(QuestionAnswerSummary.this, CampaignHome.class);
                            homeIntent.putExtra("Name", currentUser.getName());
                            homeIntent.putExtra("Email",currentUser.getEmail());
                            homeIntent.putExtra("Password",currentUser.getPassword());
                            homeIntent.putExtra("is_Admin",currentUser.getAdminStatus());
                            homeIntent.putExtra("CampaignName",campaignName);
                            QuestionAnswerSummary.this.startActivity(homeIntent);finish();
                            break;
                        case (2):
                            Intent summaryIntent = new Intent(QuestionAnswerSummary.this, CampaignSummary.class);
                            summaryIntent.putExtra("Name", currentUser.getName());
                            summaryIntent.putExtra("Email", currentUser.getEmail());
                            summaryIntent.putExtra("Password", currentUser.getPassword());
                            summaryIntent.putExtra("is_Admin", currentUser.getAdminStatus());
                            summaryIntent.putExtra("CampaignName", campaignName);
                            QuestionAnswerSummary.this.startActivity(summaryIntent);
                            finish();
                            break;
                        case (3):
                            Intent perforemanceIntent = new Intent(QuestionAnswerSummary.this, CampaignPerformance.class);
                            perforemanceIntent.putExtra("Name", currentUser.getName());
                            perforemanceIntent.putExtra("Email", currentUser.getEmail());
                            perforemanceIntent.putExtra("Password", currentUser.getPassword());
                            perforemanceIntent.putExtra("is_Admin", currentUser.getAdminStatus());
                            perforemanceIntent.putExtra("CampaignName", campaignName);
                            QuestionAnswerSummary.this.startActivity(perforemanceIntent);
                            finish();
                            break;
                        case (4):
                            Intent goalsIntent = new Intent(QuestionAnswerSummary.this, CampaignGoals.class);
                            goalsIntent.putExtra("Name", currentUser.getName());
                            goalsIntent.putExtra("Email", currentUser.getEmail());
                            goalsIntent.putExtra("Password", currentUser.getPassword());
                            goalsIntent.putExtra("is_Admin", currentUser.getAdminStatus());
                            goalsIntent.putExtra("CampaignName", campaignName);
                            QuestionAnswerSummary.this.startActivity(goalsIntent);
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
