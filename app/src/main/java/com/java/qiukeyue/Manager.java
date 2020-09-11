package com.java.qiukeyue;

import android.annotation.SuppressLint;
import android.util.Log;


import com.java.qiukeyue.bean.CovidData;
import com.java.qiukeyue.bean.Entity;
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
                List<News> result;
                int total = fetch.getTotal();
                if(total == -1){
                    Log.e("Manager","Error on getting total");
                }
                int sub = 0;//与最新一条的差距
                if(getNew){//下拉刷新，拉取最新20条
                    fetch.setCurrent_n(total);//拉取的是最新的
                }
                else{//上拉加载更多
                    int backwardIndex_n = fetch.checkCurrent_n();
                    sub = total-backwardIndex_n+20;//拉取后面20条
                    fetch.setCurrent_n(backwardIndex_n-20);
                }
                if(sub % 20 == 0){//正好是整数页
                    result = fetch.fetchNews(type,1+sub/20,0,20,null,20);
                }
                else{
                    result = fetch.fetchNews(type,1+sub/20,sub%20,20,null,20);
                    result.addAll(fetch.fetchNews(type,2+sub%20,0,sub%20,null,20));
                }
                e.onNext(result);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())//Observable在哪个线程（不能放在主线程）
                .observeOn(AndroidSchedulers.mainThread())//Observer在哪个线程
                .subscribe(observer);
    }
    public static void search_n(final String keyword, Observer observer){//newSearch为true则为新的查询，否则只是之前的查询的上拉查看更多
        Log.e("Manager","refresh");
        Observable.create(new ObservableOnSubscribe<List<News>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<News>> e) throws Exception{
                /*
                //计算要调用哪条
                if(fetch==null){
                    fetch = new Fetch();
                }
                List<News> result=null;
                int total = fetch.getTotal();
                int sub=0;
                if(!newSearch){
                    int SearchIndex_n = fetch.checkCurrent_n();
                    sub = total-SearchIndex_n+20;//拉取后面20条
                    int pageIndex = 1+sub/20;
                    int startNum = sub%20;
                    int needNum = 20;//需要找到20个
                    while(SearchIndex_n < total && needNum > 0){

                    }
                }
                for(News n:result){
                    Log.e("Manager","fetched: "+n.getTitle());
                }
                e.onNext(result);
                e.onComplete();

                 */
                if(fetch==null){
                    fetch = new Fetch();
                }
                List<News> news=fetch.fetchNews("news",1,0,20,keyword,20);
                for(int i = 2; i <= 10; i++){
                    news.addAll(fetch.fetchNews("news",i,0,20,keyword,20));
                }

                e.onNext(news);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())//Observable在哪个线程（不能放在主线程）
                .observeOn(AndroidSchedulers.mainThread())//Observer在哪个线程
                .subscribe(observer);
    }
    public static void get_covid_data(Observer<List<CovidData>> observer, final boolean inChina) {
        Observable.create(new ObservableOnSubscribe<List<CovidData>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<CovidData>> e) throws Exception{
                if(fetch==null){
                    fetch = new Fetch();
                }
                if(inChina){
                    List<CovidData> province = fetch.fetchCovidData(true);
                    e.onNext(province);
                }
                else{
                    List<CovidData> country = fetch.fetchCovidData(false);
                    e.onNext(country);
                }
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())//Observable在哪个线程（不能放在主线程）
                .observeOn(AndroidSchedulers.mainThread())//Observer在哪个线程
                .subscribe(observer);
    }

    public static void getEntiity(Observer<List<Entity>> observer, final String queryName) {
        Observable.create(new ObservableOnSubscribe<List<Entity>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Entity>> e) throws Exception{
                if(fetch==null){
                    fetch = new Fetch();
                }
                List<Entity> entities = fetch.fetchEntity(queryName,false);
                e.onNext(entities);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())//Observable在哪个线程（不能放在主线程）
                .observeOn(AndroidSchedulers.mainThread())//Observer在哪个线程
                .subscribe(observer);
    }
    //用于在relation中点击时跳转
    public static void getExactEntity(Observer<Entity> observer, final String name) {
        Observable.create(new ObservableOnSubscribe<Entity>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Entity> e) throws Exception{
                if(fetch==null){
                    fetch = new Fetch();
                }
                List<Entity> entities = fetch.fetchEntity(name,true);
                e.onNext(entities.get(0));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())//Observable在哪个线程（不能放在主线程）
                .observeOn(AndroidSchedulers.mainThread())//Observer在哪个线程
                .subscribe(observer);
    }
}



