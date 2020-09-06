package com.java.qiukeyue;

import android.annotation.SuppressLint;
import android.util.Log;


import com.java.qiukeyue.bean.News;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
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
    @SuppressLint("CheckResult")
    Manager(){

    }
    @SuppressLint("CheckResult")
    public void refresh(final String type){
        Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Response> e) throws Exception{
                Request.Builder builder = new Request.Builder();
                HttpUrl.Builder urlBuilder = HttpUrl.parse("https://covid-dashboard.aminer.cn/api/events/list").newBuilder();
                urlBuilder.addQueryParameter("type",type);
                //.url("https://covid-dashboard.aminer.cn/api/events/list")
                //.get();
                builder.url(urlBuilder.build());
                Request request = builder.build();
                Call call = new OkHttpClient().newCall(request);
                Response response = call.execute();
                e.onNext(response);
            }
        }).map(new Function<Response, News>(){
            @Override
            public News apply(Response response) throws Exception {
                Log.e("Manager","doOnNext Thread: " + Thread.currentThread().getName() + "\n");
                if(response.isSuccessful()){
                    ResponseBody body = response.body();
                    if(body != null){
                        Log.e("Manager", "map: before convert: " + response.body());
                        return new Gson().fromJson(body.string(), News.class);
                    }
                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<News>() {
                    @Override
                    public void accept(News news) throws Exception {
                        Log.e("Manager", "doOnNext Thread:" + Thread.currentThread().getName() + "\n");
                        Log.e("Manager","doOnNext: successfully saved：" + news.toString() + "\n");
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<News>() {
                    @Override
                    public void accept(News news) throws Exception {
                        Log.e("Manager", "subscribe Thread:" + Thread.currentThread().getName() + "\n");
                        Log.e("Manager","Success: "+news.toString()+"\n");
                    }
                }, new Consumer<Throwable>(){
                    @Override
                    public void accept(Throwable throwable) throws Exception{
                        Log.e("Manager", "subscribe Thread:" + Thread.currentThread().getName() + "\n");
                        Log.e("Manager","Fail: "+throwable.getMessage()+"\n");
                    }
                });
    }
}



