package com.example.androidproject.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class StatsCollectionAdapter extends FragmentStateAdapter {
    public StatsCollectionAdapter(FragmentActivity fragmentactivity ) {
        super(fragmentactivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        Fragment fragment = new ObjectStatFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(ObjectStatFragment.ARG_OBJECT, position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
