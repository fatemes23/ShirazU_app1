package ir.andriod_stdio.shirazu_app1;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Database {


    //****************************************MAN INJA RO TAGHIR DADAM********************************************karim
    static List<New> allnews;
    static List<New> todaynews;
    static List<Announcement> announ;
    static String server="http://alibhapp.pythonanywhere.com/";
    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^MAN INJA RO TAGHIR DADAM^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^karim



    static void LoadData(){
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        new AllNews().execute();
                        new TodayNews().execute();
                        new Announcementlist().execute();
                    }
                }
        ).start();
    }



    //****************************************ALLNews va TodayNews ro man ezafe kardam********************************************karim
    private static class AllNews extends AsyncTask<Void, Void, String> {

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
                    khabar.source=jsonobj.getString("source");
                    khabar.seen= Integer.parseInt(jsonobj.getString("seen"));
                    khabar.id= Integer.parseInt(jsonobj.getString("id"));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    khabar.date= format.parse ( jsonobj.getString("date").substring(0,10));
                    //khabar.date=new Date();
                    allnews.add(khabar);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private static class TodayNews extends AsyncTask<Void, Void, String> {

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
                    khabar.source=jsonobj.getString("source");
                    khabar.id= Integer.parseInt(jsonobj.getString("id"));
                    khabar.seen= Integer.parseInt(jsonobj.getString("seen"));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    khabar.date= format.parse ( jsonobj.getString("date").substring(0,10));
                    //khabar.date=new Date();
                    todaynews.add(khabar);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Announcementlist extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            try {
                String site_url_json = server+"announcement";
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

                announ = new ArrayList<>();
                for(int i=0;i<jsonarray.length();i++){
                    System.out.println("aaaa");
                    JSONObject jsonobj = jsonarray.getJSONObject(i);
                    Announcement announcement=new Announcement();
                    announcement.subject=jsonobj.getString("subject");
                    announcement.context=jsonobj.getString("context");
                    announcement.id= Integer.parseInt(jsonobj.getString("id"));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    announcement.date= format.parse ( jsonobj.getString("sendDate").substring(0,10));
                    announcement.deadLine=format.parse ( jsonobj.getString("deadLine").substring(0,10));
                    announ.add(announcement);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ALLNews va TodayNews ro man ezafe kardam^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^karim

}
