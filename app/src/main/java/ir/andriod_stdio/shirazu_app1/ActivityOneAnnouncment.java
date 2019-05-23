package ir.andriod_stdio.shirazu_app1;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityOneAnnouncment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_announcment);

        Intent intent=getIntent();
        ImageView imageView = (ImageView) findViewById(R.id.announcement_img);
        TextView textView = (TextView)findViewById(R.id.announcement_tx);
        TextView title = (TextView)findViewById(R.id.title_tx);

        int id = intent.getIntExtra("id", -1);
        for (int i=0;i<Database.announ.size();i++){
            if (Database.announ.get(i).id == id){
                textView.setText(Database.announ.get(i).context);
                title.setText(Database.announ.get(i).subject);
                //imageView.setImageResource(R.drawable.tafahomname);

            }
        }

}
}
