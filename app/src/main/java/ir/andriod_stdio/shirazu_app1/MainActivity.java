package ir.andriod_stdio.shirazu_app1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.app.ProgressDialog;

public class MainActivity extends AppCompatActivity {

    private ImageView shz_logo;
    private LinearLayout buttons;
    private Button news;
    private Button announcement;
    private Button email;
    private ImageView shz_uni_pic;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findviews();
        logoAnimation();
        onClick();

        //******************************test for internet conection*************************************************

        //****************************************Thread vase gereftan news az server********************************************karim

        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^Thread vase gereftan news az server^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^karim



    }

    //*********************************** UI **********************************
    public void findviews(){
        shz_logo = (ImageView) findViewById(R.id.shz_img);
        buttons = (LinearLayout) findViewById(R.id.buttons);
        news = (Button) findViewById(R.id.news);
        announcement = (Button) findViewById(R.id.announcement);
        email = (Button) findViewById(R.id.email);
        shz_uni_pic = (ImageView) findViewById(R.id.shz_img);
    }

    public void logoAnimation(){
        //ObjectAnimator animatorXscale = ObjectAnimator.ofFloat(shz_logo, "ScaleX", 1f, 1.5f, 1f);
        //ObjectAnimator animatorYscale= ObjectAnimator.ofFloat(shz_logo, "ScaleY", 1f, 1.5f, 1f);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        ObjectAnimator animatorYtranslateLogo = ObjectAnimator.ofFloat(shz_logo, "translationY", - (metrics.heightPixels/4 - shz_uni_pic.getHeight()/2));
        ObjectAnimator animatorYtranslatePic = ObjectAnimator.ofFloat(shz_uni_pic, "translationY", - (metrics.heightPixels/4 - shz_uni_pic.getHeight()/2));
        //animatorXscale.setDuration(3000);
        //animatorYscale.setDuration(3000);
        animatorYtranslateLogo.setDuration(3000);
        animatorYtranslatePic.setDuration(3000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorYtranslateLogo, animatorYtranslatePic);
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

                if (isOnline1()){


                    new CountDownTimer(0, 1000) {
                        public void onFinish() {
                            // When timer is finished
                            // Execute your code here
                            Intent intent = new Intent( MainActivity.this,News.class);
                            //bara ye news.java moshakhas konim k alan news ro baz karde ya announcement
                            intent.putExtra("which_news_or_announcement" , "n");
                            startActivity(intent);
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                        }
                    }.start();

                }
                else{
                    mesg_box_exit();
                }
                // bere be page fargment

            }
        });

        //______________
        announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isOnline1()){

                    new CountDownTimer(0, 1000) {
                        public void onFinish() {
                            // When timer is finished
                            // Execute your code here
                            // bere be page fargment
                            Intent intent = new Intent( MainActivity.this,AnnouncmentActivity.class);
                            intent.putExtra("which_news_or_announcement" , "a");
                            startActivity(intent);
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                        }
                    }.start();



                } else{
                    mesg_box_exit();

                }

            }
        });

        //__________
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isOnline1()){
                    new CountDownTimer(0, 1000) {
                        public void onFinish() {
                            // When timer is finished
                            // Execute your code here
                            // bere be page email
                          /*  Intent intent = new Intent( MainActivity.this,EmailActivity.class);
                            startActivity(intent);*/


                            // Use a CustomTabsIntent.Builder to configure CustomTabsIntent.
                            String url = "https://webmail.cse.shirazu.ac.ir";
                            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
// set toolbar color and/or setting custom actions before invoking build()
// Once ready, call CustomTabsIntent.Builder.build() to create a CustomTabsIntent

// set toolbar color
                            builder.setToolbarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));
                            CustomTabsIntent customTabsIntent = builder.build();
// and launch the desired Url with CustomTabsIntent.launchUrl()
                            customTabsIntent.launchUrl(MainActivity.this, Uri.parse(url));




                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                        }
                    }.start();


                }else{
                    mesg_box_exit();
                }

            }
        });
    }



    public void  mesg_box_exit(){
        if (isOnline(getApplicationContext())) {
            //do whatever you want to do
        } else {
            try {
                android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(MainActivity.this).create();

                alertDialog.setTitle("عدم دسترسی به اینترنت");
                alertDialog.setMessage("از اتصال دستگاه خود به اینترنت اطمینان پیدا کنید و سپس مجددا تلاش کنید .");
                alertDialog.setIcon(R.drawable.ic_signal_wifi_off_black_24dp);
                alertDialog.show();

            } catch (Exception e) {

            }
        }
    }



    private BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            String reason = intent.getStringExtra(ConnectivityManager.EXTRA_REASON);
            boolean isFailover = intent.getBooleanExtra(ConnectivityManager.EXTRA_IS_FAILOVER, false);

            NetworkInfo currentNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            NetworkInfo otherNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_OTHER_NETWORK_INFO);

            if(currentNetworkInfo.isConnected()){
                //Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();


            }
            else{

                //Toast.makeText(getApplicationContext(), "Not Connected , pleas check your internet conection  then try again", Toast.LENGTH_LONG).show();
                mesg_box_exit();

            }
        }
    };



    @TargetApi(Build.VERSION_CODES.M)
    public static boolean isOnline (Context context) {
        boolean isOnline = false;
        try {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkCapabilities capabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());  // need ACCESS_NETWORK_STATE permission
            isOnline = capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isOnline;
    }

    private boolean isOnline1() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
