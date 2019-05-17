package ir.andriod_stdio.shirazu_app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EmailActivity extends AppCompatActivity {


    private TextView username;
    private TextView password;
    private Button login;
    private Button forgot_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        findviews();
        onclick();

    }

    private void findviews(){
        username = (TextView) findViewById(R.id.enter_email);
        password = (TextView) findViewById(R.id.enter_password);
        login = (Button) findViewById(R.id.log_in);
        forgot_password = (Button) findViewById(R.id.forget_password);
    }

    private void onclick(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View login) {

              /*  EmailMenueActivity emailMenuFrame = new EmailMenueActivity();
                getFragmentManager().beginTransaction()
                        .add(R.id.frame_container,emailMenuFrame)
                        .addToBackStack(null)
                        .commit();*/
                Intent intent = new Intent( EmailActivity.this,EmailNavigation.class);

                intent.putExtra("which_Inbox_or_sent_or_Draft" , "i");
                startActivity(intent);
            }
        });
    }

}
