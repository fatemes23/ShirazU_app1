package ir.andriod_stdio.shirazu_app1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SingleMailActivity extends AppCompatActivity {

    String whichPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_mail);

        whichPage= getIntent().getStringExtra("which_inbox_sent_");
        //__________________tool bar________________________
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_in_single_email);
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
        ImageView edit = (ImageView) findViewById(R.id.edit_in_singel_email);

        //_________________inja moshkhas konim etelaAte che page ii gozashte beshe________
       if(whichPage.equals("inbox")){

           toolbar.setTitle("inbox");

           edit.setVisibility(View.GONE);
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

       }else if (whichPage.equals("trash")){
           toolbar.setTitle("trash");

           //--unvisible kardane button ha too ye trash

           spam.setVisibility(View.GONE);
           forward.setVisibility(View.GONE);
           edit.setVisibility(View.GONE);
           reply.setVisibility(View.GONE);


       }else if ( whichPage.equals("sent")){

           toolbar.setTitle("sent");


           spam.setVisibility(View.GONE);
           delete.setVisibility(View.GONE);
           edit.setVisibility(View.GONE);

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




       }else if ( whichPage.equals("draft")){
           toolbar.setTitle("draft");

           spam.setVisibility(View.GONE);
           reply.setVisibility(View.GONE);
           edit.setVisibility(View.GONE);
           forward.setVisibility(View.GONE);

           delete.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

               }
           });

           edit.setVisibility(View.VISIBLE);//edit ro visible mikone
           edit.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent i = new Intent(SingleMailActivity.this, ComposeMailActivity.class);
                   startActivity(i);
                   //put extra etelaato be compose bayad enteghal bedii
               }
           });

       }



    }

}
