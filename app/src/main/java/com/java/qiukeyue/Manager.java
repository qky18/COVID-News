package com.java.qiukeyue;

import android.annotation.SuppressLint;
import android.util.Log;


import com.java.qiukeyue.bean.News;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Manager {
    private static Fetch fetch=null;
    @SuppressLint("CheckResult")
    Manager() {
    }
    @SuppressLint("CheckResult")
    public static void refresh_n(final String type, final boolean getNew, Observer observer){
        Log.e("Manager","refresh");
        Observable.create(new ObservableOnSubscribe<List<News>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<News>> e) throws Exception{
                //计算要调用哪条
                if(fetch==null){
                    fetch = new Fetch();
                }
                List<News> result=null;
                int total = fetch.getTotal();
                int backwardIndex_n = fetch.checkCurrent_n();
                Log.e("Manager","total:"+total+" backwardIndex:"+backwardIndex_n);
                if(total == -1){
                    Log.e("Manager","Error on getting total");
                }
                int sub = 0;//与最新一条的差距
                if(getNew){//下拉刷新，拉取最新20条
                    fetch.setCurrent_n(total);//拉取的是最新的
                }
                else{//上拉加载更多
                    sub = total-backwardIndex_n+20;//拉取后面20条
                    fetch.setCurrent_n(backwardIndex_n-20);
                }
                if(sub % 20 == 0){//正好是整数页
                    result = fetch.fetchNews(type,1+sub/20,0,20);
                }
                else{
                    result = fetch.fetchNews(type,1+sub/20,sub%20,20);
                    result.addAll(fetch.fetchNews(type,2+sub%20,0,sub%20));
                }
                for(News n:result){
                    Log.e("Manager","fetched: "+n.getTitle());
                }
                e.onNext(result);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())//Observable在哪个线程（不能放在主线程）
                .observeOn(AndroidSchedulers.mainThread())//Observer在哪个线程
                .subscribe(observer);
    }
}



