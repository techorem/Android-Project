package com.example.androidproject.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.androidproject.R;

public class CurrentMatch_j1_fragment extends Fragment {

    private CurrentMatch_j1_fragment.OnFragmentInteractionPlayer1Listener mListener;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mListener.onFragmentPlayer1Interaction(null);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_currentmatch, container, false);

        return view;
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






/*Interface de communication avec l'activity*/

    public interface OnFragmentInteractionPlayer1Listener {

        void onFragmentPlayer1Interaction(Object ref);
    }
}
