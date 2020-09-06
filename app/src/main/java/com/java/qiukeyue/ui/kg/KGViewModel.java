package com.java.qiukeyue.ui.kg;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KGViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public KGViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is kg fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}