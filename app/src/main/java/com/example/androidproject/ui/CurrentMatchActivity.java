package com.example.androidproject.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidproject.R;

public class CurrentMatchActivity extends AppCompatActivity {


    private String tab[];
    private TextView debugField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentmatch);


        Bundle extras = getIntent().getExtras();
        String tab[] = extras.getStringArray("infos");

        debugField = (TextView) findViewById(R.id.textview1);
        for (int i =0 ; i< 4 ; i++)
            debugField.setText(debugField.getText() + tab[i]);
    }


}
