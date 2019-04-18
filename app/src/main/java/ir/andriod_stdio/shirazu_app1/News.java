package ir.andriod_stdio.shirazu_app1;

import android.icu.text.UnicodeSetSpanner;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;

public class News extends AppCompatActivity {

     FragmentPagerAdapter adapterViewPager;
     String newsOrAnnouncement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news);
        //az intent ghabl(news) pas dade shode vali defult value ro nmidanestm chan bezarm :)
        newsOrAnnouncement= getIntent().getStringExtra("which_news_or_announcement");

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager() ,newsOrAnnouncement);
        vpPager.setAdapter(adapterViewPager);


        //___in tike ro nmidoonm chikar mikone__//
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                /* Toast.makeText(News.this,
                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();*/
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });


    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {

        private static int NUM_ITEMS ;//in te'dad tab ha hast k khodemeoon bayad set konim
        String newsOrAnnouncement1;

        public MyPagerAdapter(FragmentManager fragmentManager ,String newsOrAnnouncement) {

            super(fragmentManager);
            //news=new News();
            newsOrAnnouncement1=newsOrAnnouncement;
            if(newsOrAnnouncement1.equals("n")){//agar news bood
                NUM_ITEMS= 3;
            }else if( newsOrAnnouncement1.equals("a")){//agar etalieye boood
                NUM_ITEMS=5;


            }

        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {

            //mikhad bebine rooye fragment chi namayesh bede
            //___fateme ezaf karde */
            ArrayList<String> nameOfakhabar = new ArrayList<String>();
            ArrayList<String> nameOfetaeeye = new ArrayList<String>();

            nameOfakhabar.add("اخبار دانشگاه");
            nameOfakhabar.add("اخبار امروز");
            nameOfakhabar.add("اخبار مرتبط با دانشگاه");

            nameOfetaeeye.add("آموزشی");
            nameOfetaeeye.add("دانشجویی");
            nameOfetaeeye.add(" فرهنگی");
            nameOfetaeeye.add("پژوهشی");
            nameOfetaeeye.add("سایر");

            //____fateme ezaf karde */

            if(newsOrAnnouncement1.equals("n")){
                switch (position) {
                    case 0:
                        // har new stance ye newinstance dare
                        //k ye adad migire : te'dad row ha ,
                        //va ye esm : ke neshoon mide in page chi has
                        //k hala ba tavajoh be in 2ta too file fragment bayad set koni chi neshoon bedi
                        //oon fragmenti k titles nameOfakhabar.get(0) hast va te'dade raw hash 2 hast ro neshoon mide
                        return ListsFragment.newInstance(nameOfakhabar.get(0),"2");
                    case 1:
                        return ListsFragment.newInstance(nameOfakhabar.get(1), "5");
                    case 2: //
                        return ListsFragment.newInstance(nameOfakhabar.get(2) , "4");
                    default:
                        return null;
                }
            }else if(newsOrAnnouncement1.equals("a")){

                switch (position) {
                    case 0:
                        return ListsFragment.newInstance(nameOfetaeeye.get(0),"2");
                    case 1:
                        return ListsFragment.newInstance(nameOfetaeeye.get(1), "5");
                    case 2:
                        return ListsFragment.newInstance(nameOfetaeeye.get(2) , "4");
                    case 3 :
                        return ListsFragment.newInstance(nameOfetaeeye.get(3) , "4");
                    case 4 :
                        return ListsFragment.newInstance(nameOfetaeeye.get(4) , "4");
                    default:
                        return null;
                }

            }else{
                return null;

            }

        }

        //* Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {

            ArrayList<String> nameOfakhabar = new ArrayList<String>();
            ArrayList<String> nameOfetaeeye = new ArrayList<String>();
            // ba tavajoh be etelaaati  k az news.java/menu.java migirim
            //moshakhs mikonim oon bala chi neshoon bedm


            if(newsOrAnnouncement1.equals("n")){
                nameOfakhabar.add("اخبار دانشگاه");
                nameOfakhabar.add("اخبار امروز");
                nameOfakhabar.add("اخبار مرتبط با دانشگاه");
                return nameOfakhabar.get(position);
            }
            if(newsOrAnnouncement1.equals("a")) {

                nameOfetaeeye.add("آموزشی");
                nameOfetaeeye.add("دانشجویی");
                nameOfetaeeye.add(" فرهنگی");
                nameOfetaeeye.add("پژوهشی");
                nameOfetaeeye.add("سایر");
                return nameOfetaeeye.get(position);
            }
            else{
               // Toast.makeText(this , "getPageTitle return null " ,Toast.LENGTH_LONG).show();
                //inja nmitoonm toast konm shayad be moshkel bekhore havaset bashe
                return null;
            }





        }

    }
}
