package com.example.androidproject.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.androidproject.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class StatisticsActivity extends AppCompatActivity {

    private int[] compteurs =new int[9];
    private String[] photos= new String[10];
    private String[] texts= new String[5];
    private int id ;

    Intent intent = getIntent();
    private static final String PHOTOS = "photos";
    private static final String TEXTS = "texts";
    private static final String COMPTEURS = "compteurs";
    private static final String ID = "id";


    ViewPager2 viewPager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);


        Bundle extras = getIntent().getExtras();
        String st = extras.getString("id");
        texts = extras.getStringArray("texts");
        photos = extras.getStringArray("photos");
        compteurs = extras.getIntArray("compteurs");

        id = Integer.parseInt(st);


        ((MyApplication) this.getApplication()).setSomeVariable(st);



        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle(getString(R.string.stat_of_the_match));

        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(createCardAdapter());
        TabLayout tabLayout = findViewById(R.id.tab_layout);


        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        //tab.setText("OBJECT " + (position + 1));
                        if (position == 0){
                            tab.setText(getString(R.string.basic_data));
                        }
                        if (position == 1){
                            tab.setText(texts[0]);
                        }
                        if (position == 2){
                            tab.setText(texts[1]);
                        }
                        if (position == 3){
                            tab.setText(getString(R.string.photos_gallery));
                        }
                    }
                }
        ).attach();


    }



    private StatsCollectionAdapter createCardAdapter() {
        StatsCollectionAdapter statscollectionadapter = new StatsCollectionAdapter(this);

        return statscollectionadapter;
    }



    @Override
    protected void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArray(PHOTOS,photos);
        outState.putStringArray(TEXTS,texts);
        outState.putIntArray(COMPTEURS,compteurs);
        outState.putInt(ID,id);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Read the state of item position
        photos = savedInstanceState.getStringArray(PHOTOS);
        texts = savedInstanceState.getStringArray(TEXTS);
        compteurs = savedInstanceState.getIntArray(COMPTEURS);
        id = savedInstanceState.getInt(ID);

    }


}

