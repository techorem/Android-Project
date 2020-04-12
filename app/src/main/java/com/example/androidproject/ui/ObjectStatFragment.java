package com.example.androidproject.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidproject.R;

// Instances of this class are fragments representing a single
// object in our collection.
public class ObjectStatFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    private Integer counter;

    public ObjectStatFragment() {
        // Required empty public constructor
    }
    public static ObjectStatFragment newInstance(Integer counter) {
        ObjectStatFragment fragment = new ObjectStatFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_OBJECT, counter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            counter = getArguments().getInt(ARG_OBJECT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collection_object, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        TextView textViewCounter  = view.findViewById(R.id.TextView01);
        textViewCounter.setText(Integer.toString(args.getInt(ARG_OBJECT)));
    }
}
