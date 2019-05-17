package ir.andriod_stdio.shirazu_app1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SingleMailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_mail);


        //__________________tool bar________________________
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_in_single_email);
        toolbar.setTitle("inbox");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_forward_blackk_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ImageView reply = (ImageView) findViewById(R.id.reply_in_singel_email);
        ImageView forward = (ImageView)findViewById(R.id.forward_in_singel_email);
        ImageView spam = (ImageView)findViewById(R.id.spam_in_single_email);
        ImageView delete = (ImageView)findViewById(R.id.delete_in_single_email);

        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //reply
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        spam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bayad add beshe be spam ha
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //______________tool bar ___________________________________________
    }

}
