package com.example.androidproject.ui;

import android.app.TabActivity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    private String[] texts= new String[5];

    ViewPager2 viewPager;

    private DBManager dbManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle(getString(R.string.stat_of_the_match));

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

        dbManager = new DBManager(this);
        dbManager.open();

    }

    private StatsCollectionAdapter createCardAdapter() {
        StatsCollectionAdapter statscollectionadapter = new StatsCollectionAdapter(this);
        return statscollectionadapter;
    }

    private void backdatabase(int id){

        Cursor values = dbManager.fetch();
        values.move(id);

        for (int i = 0; i <5;i++){
            texts[i] = values.getString(i + 1);
        }

        for (int i = 0; i <9;i++){
            compteurs[i] = values.getString(6).charAt(i*2);
        }

        String buffer = "";
        char j = '0';
        int cursor = 0;

        for (int i = 0; i < values.getInt(7) ; i++ ){

            while (j != '-'){
                j = values.getString(8).charAt(cursor);
                buffer = buffer.concat(j + "");
                cursor++;
            }

            photos[i] = buffer;
            buffer = "";
        }

    }

    public String[] getPhotos(){
        backdatabase(2);
        Log.d("Statphoto",""+photos[0]);
        return photos;
    }

    public String[] getTexts(){
        backdatabase(2);
        return texts;
    }

    public int[] getCompteurs(){
        backdatabase(2);
        return compteurs;
    }


/*
    public void setPic() {
        // Get the dimensions of the View
        //Layout lt = getLayoutInflater(fragment_gallery_photo);
        ImageView imageView = findViewById(R.id.imageview01);

        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(photos[0], bmOptions);
        imageView.setImageBitmap(bitmap);
    }
*/

}

