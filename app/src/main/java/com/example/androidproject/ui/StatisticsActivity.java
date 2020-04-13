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
    private int id = 1;


    private static final String PHOTOS = "photos";
    private static final String TEXTS = "texts";
    private static final String COMPTEURS = "compteurs";
    private static final String ID = "id";


    ViewPager2 viewPager;

    private DBManager dbManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);


        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        Log.d("/////////////id ","/////////////////////////////////////////////////"+id);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle(getString(R.string.stat_of_the_match));

        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(createCardAdapter());
        TabLayout tabLayout = findViewById(R.id.tab_layout);


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


    }

    private StatsCollectionAdapter createCardAdapter() {
        StatsCollectionAdapter statscollectionadapter = new StatsCollectionAdapter(this);
        return statscollectionadapter;
    }

    private void backdatabase(int id){
        dbManager = new DBManager(this);
        dbManager.open();

        Cursor values = dbManager.fetch();
        values.move(id);

        Log.d("8","//////////////////////////////////////////////////"+values.getString(8));
        Log.d("6",values.getString(6));

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
        dbManager.close();
    }

    public String[] getPhotos(){
        backdatabase(id);
        Log.d("Statphoto",""+photos[0]);
        return photos;
    }

    public String[] getTexts(){
        backdatabase(id);
        return texts;
    }

    public int[] getCompteurs(){
        backdatabase(id);
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

