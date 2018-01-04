package com.royalways.testproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Set;

public class VideoViewActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager pager;
    private SharedPreferences preferences;
    private ArrayList<String> catList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tab_home);
        pager = (ViewPager) findViewById(R.id.view_pager);

        preferences = getSharedPreferences("viewTube",MODE_PRIVATE);
        Set<String> set = preferences.getStringSet("catList",null);

        if (set !=null){
            catList = new ArrayList<>(set);
        }

        for (String cat : catList){
            tabLayout.addTab(tabLayout.newTab().setText(cat));
        }

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), catList);
        pager.setAdapter(adapter);

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search){
            Intent intent = new Intent(VideoViewActivity.this, SearchActivty.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.add_tag){
            Intent intent = new Intent(VideoViewActivity.this, ChipTagActivity.class);
            intent.putExtra("val",1);
            startActivity(intent);
        }



        return super.onOptionsItemSelected(item);
    }
}
