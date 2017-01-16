package com.example.shane.MAV;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

/**
 * Created by Shane on 18/12/2016.
 */

public class BackgroundWorker extends AsyncTask<Void,Void,Void> {


    BackgroundWorker (){
        
    }

    @Override
    protected Void doInBackground(Void... params){

        String login_url = "http://10.0.0.2/ClientsList.php";
        Log.d(TAG, "doInBackground: url = " + login_url);
            try {
                URL url = new URL(login_url);
                Log.d(TAG, "doInBackground: URL obj created");
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                Log.d(TAG, "doInBackground: Connection created");
                httpURLConnection.setRequestMethod("POST");
                Log.d(TAG, "doInBackground: Request method set to POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                Log.d(TAG, "doInBackground: in and out set to true");

                InputStream inputstream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputstream));
                Log.d(TAG, "doInBackground: inputstream and bufferedreader created");

                String result = "";
                String line = "";
                Log.d(TAG, "doInBackground: result and line Strings created");

                while((line = bufferedReader.readLine()) != null){
                    Log.d(TAG, "Stepping through while loop -> current input: " + line);
                    result += line;
                }

                Log.d(TAG, "doInBackground: result = " + result);


                httpURLConnection.disconnect();
                Log.d(TAG, "doInBackground: Connection closed");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }
}
