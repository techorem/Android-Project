package com.example.androidproject.ui.dataaccess;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
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
import com.example.androidproject.ui.StatisticsActivity;

public class dataaccess extends Fragment {

    private static final int RED =  (0xffff0000);
    private static final int BLUE =   ( 0xff00aaff );
    private DataaccessViewModel mViewModel;

    public Button button;
    public LinearLayout LinearLocal;
    public LinearLayout LinearDistance;
    public static dataaccess newInstance() {
        return new dataaccess();
    }

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
            limitelocal = 5;
            limitedistance = numberOfPreviousMatch() - 5;
        }

        for (int i = 0;i<limitelocal;i++){

            button = new Button(getContext());
            button.setTag(i);
            button.setText(playersOfTheMatch(i));
            button.setTextColor(BLUE);

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    myClickHandler(v);
                }
            });

            LinearLocal = (LinearLayout) getView().findViewById(R.id.LinearLayoutSaveLocal);
            LinearLocal.addView(button);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            //lp.setGravity(Gravity.CENTER);
            button.setLayoutParams(lp);
        }

        for (int i = 0;i<limitedistance;i++){
            button = new Button(getContext());
            button.setTag(playersOfTheMatch(i));
            button.setText(playersOfTheMatch(i));
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

        return 4;
    }


    private String playersOfTheMatch(int numerodumatch){

        return "Tom"+" vs "+"Alex";
    }




    public void myClickHandler(View view) {
        String tagg = String.valueOf(view.getTag());

        Intent myIntent = new Intent(getContext(), StatisticsActivity.class);
        startActivity(myIntent);


    }

}
