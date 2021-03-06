package ir.andriod_stdio.shirazu_app1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

public class EmailNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;
    private ArrayList<EmailsRow> UserArrayList;
    private String inboxOrSentOrDraft;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);



        //_________floating action button /button e compose ______________
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "hello ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent( EmailNavigation.this,ComposeMailActivity.class);
                startActivity(intent);
            }
        });
        //_______________________________________________________________

        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();




        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.nav_emailusername);
        TextView navEmailAddres = (TextView) headerView.findViewById(R.id.nav_emailAddress);
        ImageView navImageView =(ImageView)headerView.findViewById(R.id.nav_imageView);

        String username = "fateme";
        navUsername.setText(username);
        navEmailAddres.setText("example@shirazU.ac.ir");
        String firstLetter = String.valueOf(username.charAt(0));
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder().buildRound(firstLetter, color); // radius in px
        navImageView.setImageDrawable(drawable);
        navigationView.setNavigationItemSelectedListener(this);


        //___________________________________________


        //__________________toolbar __________________________________
        inboxOrSentOrDraft= getIntent().getStringExtra("which_Inbox_or_sent_or_Draft");//bara inke befahmim che page ii baz she
        listView = (ListView) findViewById(R.id.list_item);

        if(inboxOrSentOrDraft.equals("i")){
            toolbar.setTitle("Inbox");
        }else if (inboxOrSentOrDraft.equals("s") ){
            toolbar.setTitle("Sent");
        }else if (inboxOrSentOrDraft.equals("d")){
            toolbar.setTitle("Draft");
        }else if(inboxOrSentOrDraft.equals("trash")){
            toolbar.setTitle("trash");
        }
       // setSupportActionBar(toolbar);
        //_______________________________

        setData();// too in function bayad array ha ro por konid
        //____________kodoom lay out draft ya ... ___________________

        if (inboxOrSentOrDraft.equals("d") ){
            DraftEmailListAdaptor adapter = new DraftEmailListAdaptor(this, R.layout.activity_draft_email_list_adaptor,UserArrayList );
            listView.setAdapter(adapter);
            // Toast.makeText(this , "Drafttttttt " , Toast.LENGTH_SHORT).show();

        }else{
            EmailListAdaptor adapter = new EmailListAdaptor(this, R.layout.email_list_adaptor_layout,UserArrayList ,"sentOrInbox" );
            listView.setAdapter(adapter);
        }


        //______________inja baraye click kardn roo ye har list___________________
        //______________mishe har etelaAti k khastin pas bedin be safe ye yedoone email masln

        if (inboxOrSentOrDraft.equals("i")){//inbox
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                    Intent i = new Intent(EmailNavigation.this, SingleMailActivity.class);
                    //If you wanna send any data to nextActicity.class you can use
                    // i.putExtra(String key, value.get(position));
                    i.putExtra("which_inbox_sent_", "inbox");


                    startActivity(i);
                }
            });
        }else if(inboxOrSentOrDraft.equals("d")){//draft
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                    Intent i = new Intent(EmailNavigation.this, SingleMailActivity.class);
                    //If you wanna send any data to nextActicity.class you can use
                    // i.putExtra(String key, value.get(position));
                    i.putExtra("which_inbox_sent_", "draft");


                    startActivity(i);
                }
            });
        }else if(inboxOrSentOrDraft.equals("s")){//sent
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                    Intent i = new Intent(EmailNavigation.this, SingleMailActivity.class);
                    //If you wanna send any data to nextActicity.class you can use
                     i.putExtra("which_inbox_sent_", "sent");

                    startActivity(i);
                }
            });
        }else if(inboxOrSentOrDraft.equals("trash")){
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                    Intent i = new Intent(EmailNavigation.this, SingleMailActivity.class);
                    //If you wanna send any data to nextActicity.class you can use
                    i.putExtra("which_inbox_sent_", "trash");



                    startActivity(i);
                }
            });
        }


        //__________________________________________________________________

    }

    // in method bra dokme backe e gooshie
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
            if(inboxOrSentOrDraft.equals("i")){
               // startActivity(new Intent(EmailNavigation.this, MainActivity.class));
             //   finish();
                //inja bayad bargardi b main activity vali nmikham harekate logo tekrar beshe set sh nakardm:/

            }else{
              //  Intent intent = new Intent(EmailNavigation.this , EmailNavigation.class);
               // intent.putExtra("which_Inbox_or_sent_or_Draft" , "i");
              //  startActivity(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.email_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_addacount) {
            return true;
        }
        if( id==R.id.action_logOut){
            /////////in ja karaii k bara log out hast to anjam mididm
            Intent intent = new Intent( EmailNavigation.this,EmailActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_inbox) {
            // Handle the camera action
            //Toast check = Toast.makeText(getActivity(), "inbox", Toast.LENGTH_SHORT);
            // check.show();
            //bara in ke too safe ba'd neshoon bedim che etelaati namayesh bede sent ya inbox
            Intent intent = new Intent(EmailNavigation.this , EmailNavigation.class);
            intent.putExtra("which_Inbox_or_sent_or_Draft" , "i");
            startActivity(intent);
        } else if (id == R.id.nav_draft) {
           // Toast check = Toast.makeText(getActivity(), "drafts", Toast.LENGTH_SHORT);
            Intent intent = new Intent(EmailNavigation.this , EmailNavigation.class);
            intent.putExtra("which_Inbox_or_sent_or_Draft" , "d");
            startActivity(intent);

        } else if (id == R.id.nav_sent) {
           // Toast check = Toast.makeText(getActivity(), "sent", Toast.LENGTH_SHORT);
            Intent intent = new Intent(EmailNavigation.this , EmailNavigation.class);
            intent.putExtra("which_Inbox_or_sent_or_Draft" , "s");
            startActivity(intent);

        } else if (id == R.id.nav_trash) {
            Intent intent = new Intent(EmailNavigation.this , EmailNavigation.class);
            intent.putExtra("which_Inbox_or_sent_or_Draft" , "trash");
            startActivity(intent);

        } else if (id == R.id.nav_spam) {

        } else if (id == R.id.nav_contacts) {

        }else if (id == R.id.nav_calender) {

        }else if (id == R.id.nav_task) {

        }else if (id == R.id.nav_jornal) {

        }else if (id == R.id.nav_notes) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private void setData() {
        UserArrayList = new ArrayList<>();

        if(inboxOrSentOrDraft.equals("i")){


            UserArrayList.add(new EmailsRow("username","date","summery","title"));
            UserArrayList.add(new EmailsRow("fateme","23 may","summery","inbox"));
            UserArrayList.add(new EmailsRow("sadaf","23 may","summery",null));
            UserArrayList.add(new EmailsRow("ali","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("karim","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("sana","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("zahra","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("sana","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("sana","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("sss","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("qq","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("ww","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("rr","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("nn","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("ee","23 may","summery","title"));


        }else if (inboxOrSentOrDraft.equals("s") ){
            UserArrayList.add(new EmailsRow("username","date","summery","title"));
            UserArrayList.add(new EmailsRow("fateme","23 may","summery","sent"));
            UserArrayList.add(new EmailsRow("sadaf","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("ali","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("karim","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("sana","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("zahra","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("sana","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("sana","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("sss","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("qq","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("ww","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("rr","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("nn","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("ee","23 may","summery","title"));
        }


        else if (inboxOrSentOrDraft.equals("d") ){
            // be jaye user name ii k dare emailesho chek mikone bezarim
            UserArrayList.add(new EmailsRow("username","date ","summery","title"));
            UserArrayList.add(new EmailsRow(null,"23 may","summery","title"));
            UserArrayList.add(new EmailsRow("rr","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("nn","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("ee","23 may","summery","title"));
        }
        else if ( inboxOrSentOrDraft.equals("trash")){
            UserArrayList.add(new EmailsRow("username","date ","summery","title"));
            UserArrayList.add(new EmailsRow("trash","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("rr","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("nn","23 may","summery","title"));
            UserArrayList.add(new EmailsRow("ee","23 may","summery","title"));
        }


    }


}
