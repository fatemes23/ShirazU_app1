package ir.andriod_stdio.shirazu_app1;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ComposeMailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_mail);

        //_____________________tool bar_________________________
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_incompose);
        toolbar.setTitle("Compose");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_forward_blackk_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button sendButton = (Button) findViewById(R.id.sendButton_composePage);
        Button attach = (Button) findViewById(R.id.attachButton_composepage);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ersal kardn email
            }
        });
        attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //attach kardn file
               /* Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
                fileintent.setType("gagt/sdf");
                try {
                    startActivityForResult(fileintent, 11);
                } catch (ActivityNotFoundException e) {
                    Log.e("tag", "No activity can handle picking a file. Showing alternatives.");
                }*/
               /* Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("file/*");
                startActivity(intent);*/
               Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 11);


            }
        });

        //______________________tool bar _______________________________

        //*__*

        EditText receiverEmial = (EditText) findViewById(R.id.to_username_composePage);
        EditText subject = (EditText) findViewById(R.id.sunject_in_compose);
        EditText composeemail = (EditText) findViewById(R.id.emailcontent_composepage);
        TextView senderEmail = (TextView) findViewById(R.id.from_username_composePage);

        senderEmail.setText("example@shirazu.ac.ir");// email e usere asli in ja set mishe

        //matni k too edit text ha neveshte mishe too in variable ha gharar migire
        String reciverEmailtext = receiverEmial.getText().toString();
        String subjecttext = subject.getText().toString();
        String composemailtext = composeemail.getText().toString();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Fix no activity available
        if (data == null)
            return;
        switch (requestCode) {
            case 11://in hamooon 11 balae
                if (resultCode == RESULT_OK) {
                    String FilePath = data.getData().getPath();
                    //FilePath is your file as a string
                    Toast.makeText(ComposeMailActivity
                            .this, FilePath , Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
