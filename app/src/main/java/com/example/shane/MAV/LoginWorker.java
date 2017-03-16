package com.example.shane.MAV;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

public class LoginWorker {

    //private ProgressDialog pDialog;

    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> usersList;

    private static String url_all_users = "http://localhost/get_user_login.php";

    private static String TAG_SUCCESS = "success";
    private static String TAG_USERS = "users";
    private static String TAG_EMAIL = "email";
    private static String TAG_PASSWORD = "password";

    JSONArray users = null;

    public void onCreate(){
        System.out.print("Login worker onCreate started");
        usersList = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> map = new HashMap<>();
        map.put("test","test");

        usersList.add(map);

        Log.d(TAG, "onCreate: LoadAllUsers Running now");
        
        new LoadAllUsers().execute();

    }

    class LoadAllUsers extends AsyncTask<String, String, String>{

        protected void onPreExecute(){
            super.onPreExecute();
        }

        protected String doInBackground(String... args){

            Log.d(TAG, "doInBackground: Start of method");
            
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            JSONObject json = jParser.makeHttpRequest(url_all_users, "GET", params);



            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    users = json.getJSONArray(TAG_USERS);

                    for (int i = 0; i < users.length(); i++) {
                        JSONObject c = users.getJSONObject(i);

                        String password = c.getString(TAG_PASSWORD);
                        String email = c.getString(TAG_EMAIL);

                        HashMap<String, String> map = new HashMap<String, String>();

                        map.put(TAG_EMAIL, email);
                        map.put(TAG_PASSWORD, password);

                        usersList.add(map);
                    }
                } else {
                    Log.d("doInBackground : ", "No user found");
                }
            }catch(JSONException e){
                    e.printStackTrace();
                }
            return null;
            }

        protected void onPostExecution(){}
    }

}
