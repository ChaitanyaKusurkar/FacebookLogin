package com.chaitanyakusurkar.facebooklogin;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class WelcomeActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
    }
}
