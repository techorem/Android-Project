package com.example.androidproject.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidproject.R;

public class CurrentMatchActivity extends AppCompatActivity implements CurrentMatch_j1_fragment.OnFragmentInteractionPlayer1Listener{


    private TextView debugField;
    public LinearLayout LayoutO;
    private int mPosition;
    private static final String SELECTED_ITEM_POSITION = "ItemPosition";
    public int orientation = LinearLayout.VERTICAL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentmatch);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("Current Match");


        Bundle extras = getIntent().getExtras();
        String tab[] = extras.getStringArray("infos");



        LayoutO = (LinearLayout) findViewById(R.id.LayoutOrient);
        LayoutO.setOrientation(orientation);



        /*debugField = (TextView) findViewById(R.id.textview1);
        for (int i =0 ; i< 4 ; i++)
            debugField.setText(debugField.getText() + tab[i]);*/
    }



    @Override
    public void onResume() {
        super.onResume();
        int ori = getResources().getConfiguration().orientation;
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
            orientation= LinearLayout.HORIZONTAL;
        } else {
            orientation = LinearLayout.VERTICAL;
        }
        LayoutO.setOrientation(orientation);
    }

 /*   @Override
    protected void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the state of item position
        outState.putInt(SELECTED_ITEM_POSITION, mPosition);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Read the state of item position
        mPosition = savedInstanceState.gettInt(SELECTED_ITEM_POSITION);
    }*/


    @Override
    public void onFragmentPlayer1Interaction(Object ref) {
        //Log.d("provider : ","=/-----------------------------------------------------------------------------------///");
        //Log.d("provider : ","=/-----------------------------------------------------------------------------------///");


    }

}
