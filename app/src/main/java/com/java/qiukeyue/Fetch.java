package com.java.qiukeyue;

import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.java.qiukeyue.bean.CovidData;
import com.java.qiukeyue.bean.Entity;
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
    private int searchIndex_n;//用于搜索
    private String[] province = new String[]{
            "China|Hong Kong", "China|Xinjiang", "China|Beijing", "China|Sichuan", "China|Gansu", "China|Shanghai", "China|Guangdong", "China|Taiwan", "China|Hebei", "China|Shaanxi", "China|Shanxi", "China|Yunnan", "China|Chongqing", "China|Inner Mongol", "China|Shandong", "China|Zhejiang", "China|Tianjin", "China|Liaoning", "China|Fujian", "China|Jiangsu", "China|Hainan", "China|Macao", "China|Jilin", "China|Hubei", "China|Jiangxi", "China|Heilongjiang", "China|Anhui", "China|Guizhou", "China|Hunan", "China|Henan", "China|Guangxi", "China|Ningxia", "China|Qinghai", "China|Xizang"
    };
    private String[] country = new String[]{
            "China", "United States of America", "Brazil", "India", "United Kingdom", "Russia", "Bangladesh", "Peru", "Mexico", "Spain", "Pakistan", "Argentina", "Egypt", "Saudi Arabia", "Philippines", "Indonesia", "Netherlands", "Iraq", "Canada", "Ukraine", "Kazakhstan", "Guatemala", "Israel", "Iran", "Oman", "Nigeria", "Turkey", "Afghanistan", "Armenia", "Romania", "Kuwait", "United Arab Emirates", "Azerbaijan", "Poland", "Belarus", "Kenya", "Palestine", "Moldova", "Uzbekistan", "Nepal", "El Salvador", "Japan", "Singapore", "Germany", "Czechia", "Bosnia and Herz.", "Bulgaria", "Sudan", "Bahrain", "Luxembourg", "Ghana", "Ethiopia", "Mauritania", "Senegal", "Morocco", "Albania", "Switzerland", "South Korea", "Libya", "Lithuania", "Latvia", "Estonia", "Hungary", "Cyprus", "São Tomé and Principe", "Angola", "Syria", "Finland", "Slovenia", "Niger", "Georgia", "Guyana", "Thailand", "Uruguay", "Mauritius", "South Africa", "Colombia", "Sweden", "France", "Ecuador", "Bolivia", "Belgium", "Panama", "Dominican Rep.", "Honduras", "Chile", "Portugal", "Italy", "Puerto Rico", "Serbia", "Venezuela", "Costa Rica", "Côte d\"Ivoire", "Haiti", "Algeria", "Dem. Rep. Congo", "Macedonia", "Cameroon", "Qatar", "Central African Rep.", "Madagascar", "Denmark", "Nicaragua", "Gabon", "Australia", "Greece", "Paraguay", "Eq. Guinea", "Somalia", "Austria", "Cuba", "Malawi", "Djibouti", "Guinea", "Croatia", "Benin", "Lebanon", "Mozambique", "Namibia", "Chad", "Mali", "Jamaica", "eSwatini", "Zimbabwe", "Rwanda", "Sri Lanka", "San Marino", "Liberia", "Togo", "Norway", "Sierra Leone", "Ireland", "Maldives", "Slovakia", "Botswana", "Jordan", "Andorra", "Tanzania", "Isle of Man", "Guam", "Malta", "Jersey", "Malaysia", "Suriname", "Tunisia", "Guernsey", "Mongolia", "Lesotho", "Cayman Is.", "Faeroe Is.", "Gibraltar", "Burkina Faso", "Iceland", "Uganda", "Trinidad and Tobago", "Myanmar", "Vietnam", "Bahamas", "Seychelles", "Barbados", "Monaco", "Fr. Polynesia", "Bhutan", "Antigua and Barb.", "Gambia", "Cambodia", "Sint Maarten", "Belize", "St. Vin. and Gren.", "Fiji", "Saint Lucia", "Laos", "Liechtenstein", "Vatican", "Papua New Guinea", "Brunei", "New Zealand", "Turkmenistan", "World", "Montenegro", "Kyrgyzstan", "Congo", "Zambia", "Cabo Verde", "St-Martin", "Greenland", "Eritrea", "Timor-Leste", "Dominica", "U.S. Virgin Is.", "American Samoa", "N. Mariana Is.", "Guinea-Bissau", "Kosovo", "Aruba", "St. Kitts and Nevis", "Montserrat", "Grenada", "Burundi", "S. Sudan", "Yemen", "Tajikistan"
    };
    Fetch() throws IOException, JSONException {
        backwardIndex_n = getTotal();
    }
    public int checkCurrent_n(){
        return backwardIndex_n;
    }
    public void setCurrent_n(int current_n){
        backwardIndex_n = current_n;
    }
    public int getSearchIndex_n(){
        return searchIndex_n;
    }
    public void setSearchIndex_n(int searchIndex_n){
        this.searchIndex_n=searchIndex_n;
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
    public List<News> fetchNews(String type, int pageIndex, int startNum, int endNum, String keyword, int maxCount) throws IOException, JSONException {//不包括endNum的
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
            //Log.e("FetchNews", "json: " + json);
            List<News> result=new ArrayList<>();
            if(json != null){
                JSONObject root = new JSONObject(json);
                //Log.e("FetchNews", "before convert: " + root.getString("data"));
                JSONArray array = root.getJSONArray("data");
                JSONObject pagination = root.getJSONObject("pagination");
                Integer total=pagination.getInt("total");
                Gson gson = new Gson();
                for(int i = startNum; i < endNum; i++){
                    String singleNews = array.getJSONObject(i).toString();
                    int start = singleNews.indexOf("\"id");
                    int end = singleNews.indexOf("\"lang");
                    // 由于拉取下来的数据里包含id项，而News继承了SugarRecord，里面本身就有一个Long类型的id，与String类型的id冲突，因此把singleNews中的id段直接删掉
                    String singleNews2 = singleNews.substring(0,start) + singleNews.substring(end);
                    //Log.e("FetchNews", "before convert: " + singleNews2);
                    News news = gson.fromJson(singleNews2, News.class);
                    //Log.e("FetchNews", "after convert: " + news.getTitle());


                    if(keyword == null){
                        result.add(news);
                    }
                    else if(news.getTitle().indexOf(keyword) > -1 || news.getCategory().indexOf(keyword) > -1){
                        result.add(news);
                        /*
                        if(result.size() == maxCount){
                            setSearchIndex_n(total-(pageIndex-1)*20-i-1);
                            break;
                        }

                         */
                    }
                }
                //setSearchIndex_n(total-pageIndex*20);

                if(keyword!=null){
                    Log.e("Fetch","page: "+pageIndex+"; check: "+result.size());
                }
            }
            return result;
        }
        return null;
    }

    public List<CovidData> fetchCovidData(boolean inChina) throws IOException, JSONException {
        Request.Builder builder = new Request.Builder()
                .url("https://covid-dashboard.aminer.cn/api/dist/epidemic.json")
                .get();

        Request request = builder.build();
        Call call = new OkHttpClient().newCall(request);
        Response response = call.execute();
        if(response.isSuccessful()){
            List<CovidData> result = new ArrayList<>();
            ResponseBody body = response.body();
            String json = body.string();
            JSONObject root = new JSONObject(json);
            if(inChina){
                for(String p: this.province){
                    String all_data = root.getJSONObject(p).getString("data");
                    int index = all_data.length()-3;
                    while(all_data.charAt(index) != '['){
                        index--;
                    }
                    String today_data = all_data.substring(index+1,all_data.length()-2);
                    String[] split_data = today_data.split(",");
                    if(split_data.length != 7){
                        Log.e("Fetch","get wrong covid data!");
                        return null;
                    }
                    CovidData p_data = new CovidData(true, p.substring(6),Integer.parseInt(split_data[0]),Integer.parseInt(split_data[1]),Integer.parseInt(split_data[2]),Integer.parseInt(split_data[3]));
                    result.add(p_data);
                }
                return result;
            }
            else{
                for(String p: this.country){
                    String all_data = root.getJSONObject(p).getString("data");
                    int index = all_data.length()-3;
                    while(all_data.charAt(index) != '['){
                        index--;
                    }
                    String today_data = all_data.substring(index+1,all_data.length()-2);
                    String[] split_data = today_data.split(",");
                    if(split_data.length != 7){
                        Log.e("Fetch","get wrong covid data!");
                        return null;
                    }
                    CovidData p_data = new CovidData(false, p,Integer.parseInt(split_data[0]),Integer.parseInt(split_data[1]),Integer.parseInt(split_data[2]),Integer.parseInt(split_data[3]));
                    result.add(p_data);
                }
                return result;
            }
        }
        return null;
    }

    public List<Entity> fetchEntity(String queryName, boolean onlyOne) throws IOException, JSONException {
        //onlyOne：用于在一个实体的relation中通过点击精准跳转到另一个实体
        String url = new String(String.format("https://innovaapi.aminer.cn/covid/api/v1/pneumonia/entityquery?entity=%s",queryName));
        Request.Builder builder = new Request.Builder()
                .url(url)
                .get();
        Request request = builder.build();
        Call call = new OkHttpClient().newCall(request);
        Response response = call.execute();
        if(response.isSuccessful()){
            List<Entity> result = new ArrayList<>();
            ResponseBody body = response.body();
            String json = body.string();
            if(json != null){
                JSONObject root = new JSONObject(json);
                JSONArray array = root.getJSONArray("data");
                Gson gson = new Gson();
                if(onlyOne){
                    String singleEntity = array.getJSONObject(0).toString();
                    Entity entity = gson.fromJson(singleEntity, Entity.class);
                    result.add(entity);
                    return result;
                }
                int endNum = 10;
                if(array.length() < endNum){
                    endNum = array.length();
                }
                for(int i = 0; i < endNum; i++){
                    String singleEntity = array.getJSONObject(i).toString();
                    Entity entity = gson.fromJson(singleEntity, Entity.class);
                    entity.trimRelations();  //relation至多保留10个
                    //Log.e("FetchNews", "after convert: " + news.getTitle());
                    result.add(entity);
                }
            }
            return result;
        }
        return null;
    }
}
