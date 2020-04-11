package com.example.androidproject.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.androidproject.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


public abstract class CurrentMatch_j1_fragment extends Fragment {

    private CurrentMatch_j1_fragment.OnFragmentInteractionPlayer1Listener mListener;

    private Button buttonPhoto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mListener.onFragmentPlayer1Interaction(null);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.currentmatch_j1_fragment, container, false);


        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CurrentMatch_j1_fragment.OnFragmentInteractionPlayer1Listener) {
            mListener = (CurrentMatch_j1_fragment.OnFragmentInteractionPlayer1Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }









    /*Interface de communication retour avec l'activity*/

    public interface OnFragmentInteractionPlayer1Listener {

        void onFragmentPlayer1Interaction(Object ref);
    }
}
