package ir.andriod_stdio.shirazu_app1;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.CountDownTimer;
import android.provider.SyncStateContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AnnouncmentActivity extends AppCompatActivity {
    private Button back;
    private ProgressBar spinner;
   private  ListView  mylistview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcment);


        //***************************************internet access chechikng **************************************************

        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        mylistview = (ListView) findViewById(R.id.announcment_listview);
        mylistview.setVisibility(View.GONE);


        ListView listview = (ListView)findViewById(R.id.announcment_listview);




        Database.LoadData();
        new CountDownTimer(1000, 1000) {
            public void onFinish() {


                ArrayList<AnnouncmentRow> AnnouncmentArray = new ArrayList<>();
                String pattern = "yyyy/MM/dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                for(int i=0;i<Database.announ.size();i++){
                    String rooz= new JalaliCalendar(Database.announ.get(i).date).getDayOfWeekString();
                    String date = new JalaliCalendar(Database.announ.get(i).date).toString() +" "+ rooz;
                    AnnouncmentArray.add(new AnnouncmentRow(Database.announ.get(i).subject,date));
                }
                spinner.setVisibility(View.GONE);
                mylistview.setVisibility(View.VISIBLE);
                AnnouncmnetListAdeptor  adapter = new AnnouncmnetListAdeptor (AnnouncmentActivity.this , R.layout.announcment_element_activity , AnnouncmentArray);
                mylistview.setAdapter(adapter);



            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();

        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                 //id = (int) mylistview.getItemAtPosition(position);
                int pos = mylistview.getPositionForView(v);

                Intent intent = new Intent( AnnouncmentActivity.this,ActivityOneAnnouncment.class);
                intent.putExtra("id",Database.announ.get(pos).id);
               // intent.putExtra("id",1);
                //Toast.makeText(getApplicationContext(),"pos :"+MainActivity.announ.get(pos).id , Toast.LENGTH_LONG).show();
                startActivity(intent);


            }
        });


        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        ///_________tool bar _____________
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarinannouncmenr);
        toolbar.setTitle("اطلاعیه");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_forward_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //__________



//
//        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));
//        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));
//        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));
//        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));
//        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));
//        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));
//        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));
//        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));
//        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));




    }


}
