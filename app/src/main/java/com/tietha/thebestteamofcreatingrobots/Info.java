package com.tietha.thebestteamofcreatingrobots;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ViewFlipper;

public class Info extends AppCompatActivity {
    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        viewFlipper = findViewById(R.id.simpleViewFlipper);
        viewFlipper.startFlipping();
    }
}
