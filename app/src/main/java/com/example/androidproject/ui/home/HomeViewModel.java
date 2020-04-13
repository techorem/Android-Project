package com.example.androidproject.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue(getString()"Welcome on Boxe Tracker");

    }

    public LiveData<String> getText() {
        return mText;
    }
}