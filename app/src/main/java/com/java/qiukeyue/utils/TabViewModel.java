package com.java.qiukeyue.utils;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TabViewModel extends ViewModel {

    private MutableLiveData<List<String>> category, delCategory;

    public TabViewModel() {
        category = new MutableLiveData<>();
        delCategory = new MutableLiveData<>();
        ArrayList<String> temp = new ArrayList<>();
        temp.add("news");
        temp.add("paper");
        category.setValue(temp);
        delCategory.setValue(new ArrayList<String>());
    }

    public LiveData<List<String>> getCategory(){
        return category;
    }

    public LiveData<List<String>> getDelCategory(){
        return delCategory;
    }

    public void setCategory(List<String> cat){
        category.setValue(cat);
    }

    public void setDelCategory(List<String> delCat){
        delCategory.setValue(delCat);
    }
}

/*
public class TabManager {
    static private ArrayList<String> category = new ArrayList<>(Arrays.asList("news", "paper"));
    static private ArrayList<String> delCategory = new ArrayList<>();

    public static ArrayList<String> getCategory(){
        return category;
    }

    public static ArrayList<String> getDelCategory(){
        return delCategory;
    }

    public static void setCategory(ArrayList<String> cat){
        category = cat;
    }

    public static void setDelCategory(ArrayList<String> delCat){
        delCategory = delCat;
    }
}

 */

