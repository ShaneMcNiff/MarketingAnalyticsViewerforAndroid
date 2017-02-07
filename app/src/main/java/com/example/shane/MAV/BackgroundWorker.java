package com.example.shane.MAV;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.*;

import static android.content.ContentValues.TAG;

/**
 * Created by Shane on 18/12/2016.
 */

public class BackgroundWorker extends ListActivity{

    private ProgressDialog pDialog;

    //JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> UsersList;

    private static String url_all_users = "http://api.androidhive.info/fyp/android_connect/get_user_login.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_USERS = "users";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_USERNAME = "username";

    JSONArray users = null;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        UsersList = new ArrayList<HashMap<String, String>>();

        new LoadAllUsers().execute();

        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //String email = ((TextView) view.findViewById())
            }
        });
    }
}