package ir.andriod_stdio.shirazu_app1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {

    private ImageView shz_logo;
    private LinearLayout buttons;

    private Button news;
    private Button announcement;
    private Button email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findviews();
        logoAnimation();
        onClick();

    }

    public void findviews(){
        shz_logo = (ImageView) findViewById(R.id.shz_img);
        buttons = (LinearLayout) findViewById(R.id.buttons);
        news = (Button) findViewById(R.id.news);
        announcement = (Button) findViewById(R.id.announcement);
        email = (Button) findViewById(R.id.email);
    }

    public void logoAnimation(){
        ObjectAnimator animatorXscale = ObjectAnimator.ofFloat(shz_logo, "ScaleX", 1f, 1.5f, 1f);
        ObjectAnimator animatorYscale= ObjectAnimator.ofFloat(shz_logo, "ScaleY", 1f, 1.5f, 1f);
        ObjectAnimator animatorYtranslate= ObjectAnimator.ofFloat(shz_logo, "translationY", -400f);
        animatorXscale.setDuration(3000);
        animatorYscale.setDuration(3000);
        animatorYtranslate.setDuration(3000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorXscale,animatorYscale,animatorYtranslate);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                buttons.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        animatorSet.start();
    }

    public void onClick(){
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // bere be page fargment
                Intent intent = new Intent( MenuActivity.this,News.class);
                //bara ye news.java moshakhas konim k alan news ro baz karde ya announcement
                intent.putExtra("which_news_or_announcement" , "n");
                startActivity(intent);
            }
        });

        //______________
        announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // bere be page fargment
                Intent intent = new Intent( MenuActivity.this,News.class);
                intent.putExtra("which_news_or_announcement" , "a");
                startActivity(intent);
            }
        });

        //__________
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // bere be page email
                Intent intent = new Intent( MenuActivity.this,EmailActivity.class);
                startActivity(intent);
            }
        });
    }

}
