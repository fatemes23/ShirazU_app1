package ir.andriod_stdio.shirazu_app1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    private ImageView shz_logo;
    private LinearLayout buttons;

    private Button news;
    private Button announcement;
    private Button email;

    //****************************************MAN INJA RO TAGHIR DADAM********************************************karim
    static List<New> allnews;
    static List<New> todaynews;
    private String server="http://alibhapp.pythonanywhere.com/";
    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^MAN INJA RO TAGHIR DADAM^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^karim


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findviews();
        logoAnimation();
        onClick();

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



    }

    //*********************************** UI **********************************
    public void findviews(){
        shz_logo = (ImageView) findViewById(R.id.shz_img);
        buttons = (LinearLayout) findViewById(R.id.buttons);
        news = (Button) findViewById(R.id.news);
        announcement = (Button) findViewById(R.id.announcement);
        email = (Button) findViewById(R.id.email);
    }

    public void logoAnimation(){
        ObjectAnimator animatorXscale = ObjectAnimator.ofFloat(shz_logo, "ScaleX", 1f, 1.5f, 1f);
        ObjectAnimator animatorYscale= ObjectAnimator.ofFloat(shz_logo, "ScaleY", 1f, 1.5f, 1f);
        ObjectAnimator animatorYtranslate= ObjectAnimator.ofFloat(shz_logo, "translationY", -400f);
        animatorXscale.setDuration(3000);
        animatorYscale.setDuration(3000);
        animatorYtranslate.setDuration(3000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorXscale,animatorYscale,animatorYtranslate);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                buttons.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        animatorSet.start();
    }

    public void onClick(){
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // bere be page fargment
                Intent intent = new Intent( MainActivity.this,News.class);
                //bara ye news.java moshakhas konim k alan news ro baz karde ya announcement
                intent.putExtra("which_news_or_announcement" , "n");
                startActivity(intent);
            }
        });

        //______________
        announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // bere be page fargment
                Intent intent = new Intent( MainActivity.this,AnnouncmentActivity.class);
                intent.putExtra("which_news_or_announcement" , "a");
                startActivity(intent);
            }
        });

        //__________
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // bere be page email
                Intent intent = new Intent( MainActivity.this,EmailActivity.class);
                startActivity(intent);
            }
        });
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
                    khabar.source=jsonobj.getString("source");
                    khabar.seen= Integer.parseInt(jsonobj.getString("seen"));
                    khabar.id= Integer.parseInt(jsonobj.getString("id"));
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
                    khabar.source=jsonobj.getString("source");
                    khabar.id= Integer.parseInt(jsonobj.getString("id"));
                    khabar.seen= Integer.parseInt(jsonobj.getString("seen"));
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
