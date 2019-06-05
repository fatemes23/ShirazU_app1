package ir.andriod_stdio.shirazu_app1;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.nio.ByteBuffer;

public class SingleNewsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_news);
        ImageView imageView = (ImageView) findViewById(R.id.news_img);
        Intent intent=getIntent();
        TextView newsContex = (TextView)findViewById(R.id.news_context);
        TextView newsDate = (TextView)findViewById(R.id.news_date);
        TextView newsTitle = (TextView)findViewById(R.id.news_title);
        TextView newsViews = (TextView)findViewById(R.id.news_views);
        TextView newsSource = (TextView)findViewById(R.id.news_source);
        int id = intent.getIntExtra("id", -1);
        boolean related = intent.getBooleanExtra("related", true);

        if (related) {
            for (int i=0;i<Database.relatednews.size();i++){
                if (Database.relatednews.get(i).id==id){
                    newsContex.setText(Database.relatednews.get(i).context);
                    newsTitle.setText(Database.relatednews.get(i).subject);

                    Glide.with(this).load(Database.server+Database.relatednews.get(i).picture).into(imageView);

                    String rooz= new JalaliCalendar(Database.relatednews.get(i).date).getDayOfWeekString();
                    String date = new JalaliCalendar(Database.relatednews.get(i).date).toString() +" "+ rooz;
                    newsDate.setText(date);
                    newsSource.setText(Database.relatednews.get(i).source);
                    newsViews.setText(String.valueOf(Database.relatednews.get(i).seen));
                }
            }
        } else {
            for (int i=0;i<Database.allnews.size();i++){
                if (Database.allnews.get(i).id==id){
                    newsContex.setText(Database.allnews.get(i).context);
                    newsTitle.setText(Database.allnews.get(i).subject);

                    Glide.with(this).load(Database.server+Database.allnews.get(i).picture).into(imageView);

                    String rooz= new JalaliCalendar(Database.allnews.get(i).date).getDayOfWeekString();
                    String date = new JalaliCalendar(Database.allnews.get(i).date).toString() +" "+ rooz;
                    newsDate.setText(date);
                    newsSource.setText(Database.allnews.get(i).source);
                    newsViews.setText(String.valueOf(Database.allnews.get(i).seen));
                }
            }
        }



       //imageView.setImageResource(R.drawable.tafahomname);

    }

    @NonNull
    public Class<ByteBuffer> getDataClass() {
        return ByteBuffer.class;
    }



}

