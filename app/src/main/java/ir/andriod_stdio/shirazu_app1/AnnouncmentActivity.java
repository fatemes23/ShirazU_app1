package ir.andriod_stdio.shirazu_app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        back = (Button) findViewById(R.id.announcment_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( AnnouncmentActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });


        ListView listview = (ListView)findViewById(R.id.announcment_listview);

        ArrayList<AnnouncmentRow> AnnouncmentArray = new ArrayList<>();

        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));
        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));
        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));
        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));
        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));
        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));
        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));
        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));
        AnnouncmentArray.add(new AnnouncmentRow("مراسم اهدا جوایز و تقدیر از قهرمانان ورزشی دانشگاه شیراز ","دوشنبه ۱۳۹۸/۲/۲۳ "));


        AnnouncmnetListAdeptor  adapter = new AnnouncmnetListAdeptor (this , R.layout.announcment_element_activity , AnnouncmentArray);
        ListView mylistview = (ListView) findViewById(R.id.announcment_listview);
        mylistview.setAdapter(adapter);

    }
}
