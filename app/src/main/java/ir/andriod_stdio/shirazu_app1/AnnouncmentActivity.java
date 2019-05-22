package ir.andriod_stdio.shirazu_app1;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.provider.SyncStateContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AnnouncmentActivity extends AppCompatActivity {
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcment);


        //***************************************nternet access chechikng **************************************************


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


        ListView listview = (ListView)findViewById(R.id.announcment_listview);

        ArrayList<AnnouncmentRow> AnnouncmentArray = new ArrayList<>();

        for(int i=0;i<MainActivity.announ.size();i++){
            AnnouncmentArray.add(new AnnouncmentRow(MainActivity.announ.get(i).subject,MainActivity.announ.get(i).date));
        }

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


        AnnouncmnetListAdeptor  adapter = new AnnouncmnetListAdeptor (this , R.layout.announcment_element_activity , AnnouncmentArray);
        ListView mylistview = (ListView) findViewById(R.id.announcment_listview);
        mylistview.setAdapter(adapter);

    }


}
