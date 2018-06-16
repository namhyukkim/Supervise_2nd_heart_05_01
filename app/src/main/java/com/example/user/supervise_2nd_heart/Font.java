package com.example.user.supervise_2nd_heart;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

public class Font extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this,"font/a드림고딕1.ttf"));

    }
}