package com.example.walk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class splash extends Activity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = (ImageView) findViewById(R.id.slashImage);

//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                i++;
//                if( i >= image.length) {
//                    i=0;
//                }
//                imageView.setImageResource(image[i]);
//            }
//        },0,1000);

        Handler handler = new Handler();
        handler.postDelayed(new splashHandler(),1000);
    }

    private class splashHandler implements Runnable {
        @Override
        public void run() {
            Intent intent= new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
            splash.this.finish();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}