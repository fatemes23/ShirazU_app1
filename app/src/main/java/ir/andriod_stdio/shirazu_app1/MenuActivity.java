package ir.andriod_stdio.shirazu_app1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    private Button news;
    private Button announcement;
    private Button email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //_______________________
        news = (Button) findViewById(R.id.news);
        announcement = (Button) findViewById(R.id.announcement);
        email = (Button) findViewById(R.id.email);

        //_________________

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // bere be page fargment
                Intent intent = new Intent( MenuActivity.this,News.class);
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
                Intent intent = new Intent( MenuActivity.this,AnnouncmentActivity.class);
                intent.putExtra("which_news_or_announcement" , "a");
                startActivity(intent);
            }
        });

        //__________
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // bere be page email
                Intent intent = new Intent( MenuActivity.this,EmailActivity.class);
                startActivity(intent);
            }
        });



    }



}
