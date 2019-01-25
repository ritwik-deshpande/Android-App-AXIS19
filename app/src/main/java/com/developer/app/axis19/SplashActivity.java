package com.developer.app.axis19;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {

    ImageView logo,typo;
    TextView tv_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);



        logo = (ImageView)findViewById(R.id.imgLogo);
        typo = (ImageView)findViewById(R.id.typo);
        Glide.with(getApplicationContext()).load(R.drawable.infinity)
                .into(typo);

        Animation logofade = AnimationUtils.loadAnimation(getBaseContext(), R.anim.logofade);
        final Animation fade = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade);

        final Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(1000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    Intent start = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(start);
                    finish();
                    overridePendingTransition(R.anim.fadefast,R.anim.fadefast);
                }
            }
        };

        logo.startAnimation(logofade);
        logofade.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logo.setVisibility(View.VISIBLE);
                typo.startAnimation(fade);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fade.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                typo.setVisibility(View.VISIBLE);
                timer.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
