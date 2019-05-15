package ir.andriod_stdio.shirazu_app1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //****************************************MAN INJA RO TAGHIR DADAM********************************************karim
    static List<New> allnews;
    static List<New> todaynews;
    private String server="http://alibhapp.pythonanywhere.com/";
    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^MAN INJA RO TAGHIR DADAM^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^karim


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //****************************************Thread vase gereftan news az server********************************************karim
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        new AllNews().execute();
                        new TodayNews().execute();
                    }
                }
        ).start();
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^Thread vase gereftan news az server^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^karim


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(i);
                finish();

            }
        },4000);
    }

    //****************************************ALLNews va TodayNews ro man ezafe kardam********************************************karim
    private class AllNews extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            try {
                String site_url_json = server+"akhbar";
                URL url = new URL(site_url_json);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }


        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);

            try {
                JSONArray jsonarray = new JSONArray(strJson);

                allnews = new ArrayList<New>();
                for(int i=0;i<jsonarray.length();i++){
                    JSONObject jsonobj = jsonarray.getJSONObject(i);
                    New khabar=new New();
                    khabar.subject=jsonobj.getString("subject");
                    khabar.summary=jsonobj.getString("summary");
                    khabar.context=jsonobj.getString("context");
                    khabar.date=jsonobj.getString("date").substring(0,10);
                    allnews.add(khabar);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class TodayNews extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            try {
                String site_url_json = server+"akhbar/today";
                URL url = new URL(site_url_json);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }


        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);

            try {
                JSONArray jsonarray = new JSONArray(strJson);

                todaynews = new ArrayList<New>();
                for(int i=0;i<jsonarray.length();i++){
                    JSONObject jsonobj = jsonarray.getJSONObject(i);
                    New khabar=new New();
                    khabar.subject=jsonobj.getString("subject");
                    khabar.context=jsonobj.getString("context");
                    khabar.summary=jsonobj.getString("summary");
                    khabar.date=jsonobj.getString("date").substring(0,10);
                    todaynews.add(khabar);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ALLNews va TodayNews ro man ezafe kardam^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^karim

}
