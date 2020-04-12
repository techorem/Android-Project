package com.example.androidproject.ui;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.androidproject.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class StatisticsActivity extends AppCompatActivity {

    private int[] compteurs =new int[9];
    private String[] photos= new String[10];
    private String[] texts= new String[4];
    ViewPager2 viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("Statistics of the Match");

        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(createCardAdapter());
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        int id = 1;
        backdatabase(id);

        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        //tab.setText("OBJECT " + (position + 1));
                        if (position == 0){
                            tab.setText(texts[0]);
                        }
                        if (position == 1){
                            tab.setText(texts[1]);
                        }
                        if (position == 2){
                            tab.setText("Photos Gallery");
                        }
                    }
                }
        ).attach();

    }

    private StatsCollectionAdapter createCardAdapter() {
        StatsCollectionAdapter statscollectionadapter = new StatsCollectionAdapter(this);
        return statscollectionadapter;
    }

    private void backdatabase(int id){
        String[] pt = {"/storage/emulated/0/Android/data/com.example.androidproject/files/Pictures/JPEG_20200411_180031_3633678634967471160.jpg",
                "/storage/emulated/0/Android/data/com.example.androidproject/files/Pictures/JPEG_20200411_183839_1400330841427523984.jpg",
                "/storage/emulated/0/Android/data/com.example.androidproject/files/Pictures/JPEG_20200412_185155_5157237694631528152.jpg"};
        String[] txt = {"Alexandre","Lilian","poids plumes","Paname"};
        for (int i = 0; i <9;i++){
            compteurs[i] = i;
        }
        for (int i = 0; i <pt.length;i++){
            photos[i] = pt[i];
        }
        for (int i = 0; i <4;i++){
            texts[i] = txt[i];
        }
    }
}

