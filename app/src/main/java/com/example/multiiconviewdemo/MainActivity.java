package com.example.multiiconviewdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.sutanu.multiiconview.MultiColorIconView;

public class MainActivity extends AppCompatActivity {

    Spinner color;
    Spinner animation;
    private CodeColorCustomSpinnerAdapter colorAdp1;
    private AnimationAdapter colorAdp2;
    private String fontColor;
    String animationTxt ="";
    private int intFontColor = Color.parseColor("#212121");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MultiColorIconView multiColorIconView = (MultiColorIconView)findViewById(R.id.multi_color_icon_view);

        color = (Spinner)findViewById(R.id.color);
        animation = (Spinner)findViewById(R.id.animation);

        String[] color1=getResources().getStringArray(R.array.color1);
        String[] aniStr=getResources().getStringArray(R.array.animation);

        colorAdp1 = new CodeColorCustomSpinnerAdapter(this,R.layout.color_adapter_layout,color1);
        color.setAdapter(colorAdp1);

        colorAdp2 = new AnimationAdapter(this,R.layout.animation_adapter_layout,aniStr);
        animation.setAdapter(colorAdp2);

        color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                fontColor = color.getSelectedItem().toString().toUpperCase();
                intFontColor = Color.parseColor(fontColor);
                multiColorIconView.setMultiColorIconViewColor(intFontColor);


                if(animationTxt.equals("FADE_IN")){
                    multiColorIconView.setViewDuration(1000);
                    multiColorIconView.setAnimationType(1);
                }else if(animationTxt.equals("FADE_OUT")){
                    multiColorIconView.setViewDuration(1000);
                    multiColorIconView.setAnimationType(2);
                }else if(animationTxt.equals("ROTATE")){
                    multiColorIconView.setViewDuration(1000);
                    multiColorIconView.setAnimationType(3);
                }else if(animationTxt.equals("SCALE")){
                    multiColorIconView.setViewDuration(1000);
                    multiColorIconView.setAnimationType(4);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        animation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                animationTxt = animation.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
