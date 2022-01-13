package com.aariyan.linxtimeandbilling.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.aariyan.linxtimeandbilling.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Home");
    }
}