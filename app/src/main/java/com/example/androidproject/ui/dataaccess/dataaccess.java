package com.example.androidproject.ui.dataaccess;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
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


        char value = 0;
        int cursor2 = 0;
        String stat = "";
        int statint =0;

        Log.d("value compteur",values.getString(6));
        for (int i = 1; i < 9;i++){

            while ((value != '-')&&(cursor2 != values.getString(6).length())){
                value = values.getString(6).charAt(cursor2);
                stat = stat.concat(value + "");

                cursor2++;
                if(cursor2 < values.getString(6).length())
                    value = values.getString(6).charAt(cursor2);
            }
            Log.d("value compteur",stat);

            if(stat!=""){
                if (stat.charAt(stat.length()-1)==('-')){
                    stat = stat.substring(0, stat.length() - 1);

                }
                statint = Integer.parseInt(stat);
            }


            compteurs[i] = statint;
            stat="";
            statint = 0;
            cursor2++;
            if(cursor2 < values.getString(6).length())
                value = values.getString(6).charAt(cursor2);

        }




        String buffer = "";
        char j = '0';
        int cursor = 0;

        for (int i = 0; i < values.getInt(7) ; i++ ){

            while ( (j != '-' ) && ( cursor != values.getString(8).length() ) ){
                j = values.getString(8).charAt(cursor);
                buffer = buffer.concat(j + "");
                cursor++;
                if(cursor < values.getString(8).length())
                    j = values.getString(8).charAt(cursor);
            }

            if(buffer!=""){
                if (buffer.charAt(buffer.length()-1)==('-'))
                    buffer = buffer.substring(0, buffer.length() - 1);
            }

            photos[i] = buffer;
            buffer = "";
            cursor++;
            if(cursor < values.getString(8).length())
                j = values.getString(8).charAt(cursor);
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
