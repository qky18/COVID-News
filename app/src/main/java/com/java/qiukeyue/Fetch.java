package com.java.qiukeyue;

import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
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

public class Fetch {
    private int backwardIndex_n;//第一条新闻到最后一条的距离
    Fetch() throws IOException, JSONException {
        backwardIndex_n = getTotal();
    }
    public int checkCurrent_n(){
        return backwardIndex_n;
    }
    public void setCurrent_n(int current_n){
        backwardIndex_n = current_n;
    }
    public int getTotal() throws IOException, JSONException {
        String url = new String(String.format("https://covid-dashboard.aminer.cn/api/events/list?type=%s&page=%d&size=%d","news",1,20));
        Request.Builder builder = new Request.Builder().url(url).get();
        Request request = builder.build();
        Call call = new OkHttpClient().newCall(request);
        Response response = call.execute();
        if(response.isSuccessful()){
            JSONObject root = new JSONObject(response.body().string());
            JSONObject pagination = root.getJSONObject("pagination");
            Integer total=pagination.getInt("total");
            return total;
        }
        return -1;
    }
    public List<News> fetchNews(String type, int pageIndex, int startNum, int endNum) throws IOException, JSONException {//不包括endNum的
        String url = new String(String.format("https://covid-dashboard.aminer.cn/api/events/list?type=%s&page=%d&size=%d",type,pageIndex,20));
        Request.Builder builder = new Request.Builder()
                .url(url)
                .get();

        Request request = builder.build();
        Call call = new OkHttpClient().newCall(request);
        Response response = call.execute();
        if(response.isSuccessful()){
            ResponseBody body = response.body();
            String json = body.string();
            Log.e("FetchNews", "json: " + json);

            List<News> result=new ArrayList<>();
            if(json != null){
                JSONObject root = new JSONObject(json);
                //Log.e("FetchNews", "before convert: " + root.getString("data"));
                JSONArray array = root.getJSONArray("data");
                JSONObject pagination = root.getJSONObject("pagination");
                Integer total=pagination.getInt("total");
                Log.e("FetchNews", "total: " + total);
                Gson gson = new Gson();
                for(int i = startNum; i < endNum; i++){
                    String singleNews = array.getJSONObject(i).toString();
                    int start = singleNews.indexOf("\"id");
                    int end = singleNews.indexOf("\"lang");
                    // 由于拉取下来的数据里包含id项，而News继承了SugarRecord，里面本身就有一个Long类型的id，与String类型的id冲突，因此把singleNews中的id段直接删掉
                    String singleNews2 = singleNews.substring(0,start) + singleNews.substring(end);
                    Log.e("FetchNews", "before convert: " + singleNews2);
                    News news = gson.fromJson(singleNews2, News.class);
                    Log.e("FetchNews", "after convert: " + news.getTitle());
                    result.add(news);
                }
            }
            return result;
        }
        return null;
    }
}
