package com.example.multiiconviewdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sutanu.multiiconview.MultiColorIconView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MultiColorIconView aa = (MultiColorIconView)findViewById(R.id.multi_color_icon_view);

        aa.setMultiColorIconViewColor(Color.RED);

         // aa.setViewDuration(10000);
         // aa.setAnimationType(MultiColorIconView.FADE_IN);
    }
}
