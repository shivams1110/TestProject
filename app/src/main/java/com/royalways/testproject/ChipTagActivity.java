package com.royalways.testproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ChipTagActivity extends AppCompatActivity {

    private FlowLayout linearLayout;
    private Button btnNext;
    private SharedPreferences preferences;
    private ArrayList<String> catList = new ArrayList<>();
    String[] chips = {"Comedy","Technology","BB Ki Vine","Technical Guruji","Micheal Jackson"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chip_tag);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int passVal = this.getIntent().getIntExtra("val",0);

        btnNext = (Button) findViewById(R.id.btn_tag);
        linearLayout = (FlowLayout) findViewById(R.id.linear_chip_view);
//        chipsInput = (ChipsInput) findViewById(R.id.chips_input);

        preferences = getSharedPreferences("viewTube",MODE_PRIVATE);
        Set<String> set = preferences.getStringSet("catList",null);

        if (set != null){
            catList = new ArrayList<>(set);
        }

        if (catList.size()>2 && passVal == 0){
            Intent intent = new Intent(ChipTagActivity.this, VideoViewActivity.class);
            startActivity(intent);
            finish();
        }

//        if (catList.size()>2){
//
//            setAllTag(textView);
//
//        }


        setChipView();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = preferences.edit();
                HashSet<String> hashSet = new HashSet<String>();
                hashSet.addAll(catList);
                editor.putStringSet("catList",hashSet);
                editor.commit();

                Intent intent = new Intent(ChipTagActivity.this, VideoViewActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void setAllTag(TextView textView, String text) {

        int counter = 0;
        if (catList.size()>0){

            for (int i = 0; i < catList.size(); i++) {

                if (catList.get(i).equals(text)){
//                            Toast.makeText(ChipTagActivity.this, "" + text, Toast.LENGTH_SHORT).show();
                    textView.setBackgroundResource(R.drawable.label_bg_change);
                    textView.setTextColor(Color.parseColor("#ffffff"));
                    counter++;
                    break;
                }

            }

        }
        if (counter == 0) {
//                    Toast.makeText(ChipTagActivity.this, "" + text, Toast.LENGTH_SHORT).show();
            textView.setBackgroundResource(R.drawable.label_bg);
            textView.setTextColor(Color.parseColor("#000000"));

        }

    }

    private void setChipView() {



        for (String text : chips) {
            TextView textView = buildLabel(text);
            linearLayout.addView(textView);
        }

    }

    private TextView buildLabel(final String text) {
        final TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

        textView.setPadding((int)dpToPx(16), (int)dpToPx(8), (int)dpToPx(16), (int)dpToPx(8));
        textView.setBackgroundResource(R.drawable.label_bg);

        setAllTag(textView, text);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int counter = 0;
                if (catList.size()>0){

                    for (int i = 0; i < catList.size(); i++) {

                        if (catList.get(i).equals(text)){
                            catList.remove(i);
//                            Toast.makeText(ChipTagActivity.this, "" + text, Toast.LENGTH_SHORT).show();
                            textView.setBackgroundResource(R.drawable.label_bg);
                            textView.setTextColor(Color.parseColor("#000000"));
                            counter++;
                            break;
                        }

                    }

                }
                if (counter == 0) {
                    catList.add(text);
//                    Toast.makeText(ChipTagActivity.this, "" + text, Toast.LENGTH_SHORT).show();
                    textView.setBackgroundResource(R.drawable.label_bg_change);
                    textView.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });

        return textView;
    }

    private float dpToPx(float dp){
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

}
