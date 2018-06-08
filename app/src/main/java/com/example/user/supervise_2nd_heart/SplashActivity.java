package com.example.user.supervise_2nd_heart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {
    ImageView comIm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Handler handler = new Handler();
        handler.postDelayed(new splashhandler(),3000);

        comIm = (ImageView)findViewById(R.id.comIm);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.loading);
        comIm.startAnimation(animation);
    }
    private class splashhandler implements Runnable{
        public void run() {
            startActivity(new Intent(getApplication(),MainActivity.class));
            SplashActivity.this.finish();
        }
    }
}
