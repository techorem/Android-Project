package com.example.androidproject.ui.dataaccess;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.androidproject.R;
import com.example.androidproject.ui.DBManager;
import com.example.androidproject.ui.StatisticsActivity;


public class dataaccess extends Fragment {

    private static final int RED =  (0xffff0000);
    private static final int BLUE =   ( 0xff00aaff );
    private DataaccessViewModel mViewModel;

    public Button button;
    public LinearLayout LinearLocal;
    public LinearLayout LinearDistance;



    private int[] compteurs =new int[9];
    private String[] photos= new String[10];
    private String[] texts= new String[5];


    private DBManager dbManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dataaccess_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DataaccessViewModel.class);
        // TODO: Use the ViewModel

        int limitelocal;
        int limitedistance;

        if(numberOfPreviousMatch()<=5){
            limitelocal  = numberOfPreviousMatch();
            limitedistance = 0;
        }else{
            limitelocal = numberOfPreviousMatch();
            limitedistance = numberOfPreviousMatch() - 5;
        }



        for (int i = limitelocal;i>limitedistance;i--){

            button = new Button(getContext());
            button.setTag(i-1);
            button.setText(playersOfTheMatch(i-1));
            button.setTextColor(BLUE);

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    myClickHandler(v);
                }
            });

            LinearLocal = (LinearLayout) getView().findViewById(R.id.LinearLayoutSaveLocal);
            LinearLocal.addView(button);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


            button.setLayoutParams(lp);
        }

       for (int i = limitedistance;i > 0;i--){
            button = new Button(getContext());
            button.setTag(i-1);
            button.setText(playersOfTheMatch(i-1));
            button.setTextColor(BLUE);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    myClickHandler(v);
                }
            });

            LinearDistance = (LinearLayout) getView().findViewById(R.id.LinearLayoutSaveDistance);
            LinearDistance.addView(button);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(lp);
        }


    }

    private int numberOfPreviousMatch(){
        dbManager = new DBManager(this.getContext());
        dbManager.open();

        Cursor values = dbManager.fetch();

        return values.getCount();
    }


    private String playersOfTheMatch(int numerodumatch){
        dbManager = new DBManager(getContext());
        dbManager.open();


        Cursor values = dbManager.fetch();
        values.move(numerodumatch);

        return values.getString(1) + " vs "+ values.getString(2);
    }

    private void backdatabase(int id){
        dbManager = new DBManager(getContext());
        dbManager.open();

        Cursor values = dbManager.fetch();
        values.move(id);

        for (int i = 0; i <5;i++){
            texts[i] = values.getString(i + 1);
        }

        for (int i = 0; i < 8;i++){
            compteurs[i] = values.getString(6).charAt(i*2);
        }

        String buffer = "";
        char j = '0';
        int cursor = 0;

        for (int i = 0; i < values.getInt(7) ; i++ ){

            while ( (j != '-' ) && ( cursor != values.getString(8).length() ) ){
                j = values.getString(8).charAt(cursor);
                buffer = buffer.concat(j + "");
                cursor++;
            }

            photos[i] = buffer;
            buffer = "";
        }

        dbManager.close();
    }

    public void myClickHandler(View view) {
        String tagg = view.getTag().toString();

        backdatabase(Integer.parseInt(tagg));



        Intent myIntent = new Intent(getContext(), StatisticsActivity.class);
        myIntent.putExtra("id", tagg);
        myIntent.putExtra("photos", photos);
        myIntent.putExtra("texts", texts);
        myIntent.putExtra("compteurs", compteurs);
        startActivity(myIntent);
    }

}
