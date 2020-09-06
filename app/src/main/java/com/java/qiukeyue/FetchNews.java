package com.java.qiukeyue;

import android.util.Log;

import com.google.gson.Gson;
import com.java.qiukeyue.bean.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class FetchNews {
    private FetchNews(){
    }

    static public void fetch(String type) throws IOException, JSONException {
        String url = new String(String.format("https://covid-dashboard.aminer.cn/api/events/list?type=%s&page=%d&size=%d",type,1,20));
        Request.Builder builder = new Request.Builder()
                .url(url)
                .get();
        //HttpUrl.Builder urlBuilder = HttpUrl.parse("https://covid-dashboard.aminer.cn/api/events/list").newBuilder();
        //urlBuilder.addQueryParameter("type",type);
        //builder.url(urlBuilder.build());

        Request request = builder.build();
        Call call = new OkHttpClient().newCall(request);
        Response response = call.execute();
        if(response.isSuccessful()){
            ResponseBody body = response.body();
            String json=body.string();

            Log.e("FetchNews", "json: " + json);
            if(json != null){
                JSONObject root = new JSONObject(json);
                Log.e("FetchNews", "before convert: " + root.getString("data"));
                JSONArray array = root.getJSONArray("data");
                Gson gson = new Gson();
                for(int i = 0; i < array.length(); i++){
                    String singleNews = array.getJSONObject(i).toString();
                    Log.e("FetchNews", "before convert: " + singleNews);
                    News news = gson.fromJson(singleNews, News.class);
                    Log.e("FetchNews", "after convert: " + news.getTitle());
                    news.save();
                }
            }
        }
    }

    static public void printNews(){
        List<News> myNews = News.listAll(News.class);
        for(News n: myNews){
            Log.e("printNews", "after convert: " + n.getTitle() + n.getSeen());
        }
    }
}
