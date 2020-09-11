package com.java.qiukeyue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;


import com.java.qiukeyue.bean.CovidData;
import com.java.qiukeyue.bean.Entity;
import com.java.qiukeyue.bean.News;
import com.google.gson.Gson;
import com.java.qiukeyue.bean.Researcher;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final Map<String,String> urlMap = new HashMap<String, String>() {
        {
            put("Afghanistan", "阿富汗");
            put("Andorra", "安道尔");
            put("Angola", "安哥拉");
            put("Anguilla", "安圭拉");
            put("Antarctica", "南极洲");
            put("Argentina", "阿根廷");
            put("Armenia", "亚美尼亚");
            put("Aruba", "阿鲁巴");
            put("Australia", "澳大利亚");
            put("Austria", "奥地利");
            put("Azerbaijan", "阿塞拜疆");
            put("Bahamas", "巴哈马");
            put("Bahrain", "巴林");
            put("Baikonur", "拜科努尔");
            put("Bangladesh", "孟加拉国");
            put("Barbados", "巴巴多斯");
            put("Belarus", "白罗斯");
            put("Belgium", "比利时");
            put("Belize", "伯利兹");
            put("Benin", "贝宁");
            put("Bermuda", "百慕大");
            put("Bhutan", "不丹");
            put("Bolivia", "玻利维亚");
            put("Botswana", "波札那");
            put("Brazil", "巴西");
            put("Brunei", "文莱");
            put("Bulgaria", "保加利亚");
            put("Burundi", "蒲隆地");
            put("Cabo Verde", "佛得角");
            put("Cambodia", "柬埔寨");
            put("Cameroon", "喀麦隆");
            put("Canada", "加拿大");
            put("Cayman Is.", "开曼群岛");
            put("Chad", "乍得");
            put("Chile", "智利");
            put("China", "中国");
            put("Colombia", "哥伦比亚");
            put("Comoros", "葛摩");
            put("Cook Is.", "库克群岛");
            put("Croatia", "克罗地亚");
            put("Cuba", "古巴");
            put("Curaçao", "库拉索");
            put("Cyprus", "赛普勒斯");
            put("Czechia", "捷克");
            put("Côte d'Ivoire", "科特迪瓦");
            put("Denmark", "丹麦");
            put("Djibouti", "吉布提");
            put("Dominica", "多米尼克");
            put("Dominican Rep.", "多明尼加");
            put("Ecuador", "厄瓜多尔");
            put("Egypt", "埃及");
            put("El Salvador", "萨尔瓦多");
            put("Estonia", "爱沙尼亚");
            put("Faeroe Is.", "法罗群岛");
            put("Fiji", "斐济");
            put("Finland", "芬兰");
            put("France", "法国");
            put("Gabon", "加蓬");
            put("Gambia", "冈比亚");
            put("Georgia", "格鲁吉亚");
            put("Germany", "德国");
            put("Ghana", "迦纳");
            put("Gibraltar", "直布罗陀");
            put("Greece", "希腊");
            put("Greenland", "格陵兰");
            put("Grenada", "格林纳达");
            put("Guam", "关岛");
            put("Guatemala", "危地马拉");
            put("Guernsey", "根西岛");
            put("Guinea", "几内亚");
            put("Guyana", "圭亚那");
            put("Haiti", "海地");
            put("Honduras", "洪都拉斯");
            put("Hungary", "匈牙利");
            put("Iceland", "冰岛");
            put("India", "印度");
            put("Iran", "伊朗");
            put("Iraq", "伊拉克");
            put("Isle of Man", "马恩岛");
            put("Israel", "以色列");
            put("Italy", "意大利");
            put("Jamaica", "牙买加");
            put("Japan", "日本");
            put("Jersey", "泽西岛");
            put("Jordan", "约旦");
            put("Kenya", "肯尼亚");
            put("Kiribati", "吉里巴斯");
            put("Kosovo", "科索沃");
            put("Kuwait", "科威特");
            put("Laos", "老挝");
            put("Latvia", "拉脱维亚");
            put("Lebanon", "黎巴嫩");
            put("Lesotho", "莱索托");
            put("Liberia", "利比里亚");
            put("Libya", "利比亚");
            put("Lithuania", "立陶宛");
            put("Luxembourg", "卢森堡");
            put("Malawi", "马拉维");
            put("Malaysia", "马来西亚");
            put("Maldives", "马尔代夫");
            put("Malta", "马耳他");
            put("Mauritius", "毛里求斯");
            put("Mexico", "墨西哥");
            put("Moldova", "摩尔多瓦");
            put("Monaco", "摩纳哥");
            put("Mongolia", "蒙古国");
            put("Morocco", "摩洛哥");
            put("Mozambique", "莫桑比克");
            put("Myanmar", "缅甸");
            put("Namibia", "纳米比亚");
            put("Nauru", "诺鲁");
            put("Nepal", "尼泊尔");
            put("Netherlands", "荷兰");
            put("New Zealand", "新西兰");
            put("Nicaragua", "尼加拉瓜");
            put("Niger", "尼日尔");
            put("Nigeria", "奈及利亚");
            put("Niue", "纽埃");
            put("Norfolk Island", "诺福克岛");
            put("Norway", "挪威");
            put("Oman", "阿曼");
            put("Pakistan", "巴基斯坦");
            put("Palau", "帛琉");
            put("Palestine", "巴勒斯坦");
            put("Panama", "巴拿马");
            put("Paraguay", "巴拉圭");
            put("Peru", "秘鲁");
            put("Philippines", "菲律宾");
            put("Poland", "波兰");
            put("Portugal", "葡萄牙");
            put("Puerto Rico", "波多黎各");
            put("Qatar", "卡塔尔");
            put("Romania", "罗马尼亚");
            put("Russia", "俄罗斯");
            put("Rwanda", "卢旺达");
            put("S. Sudan", "南苏丹");
            put("Saint Lucia", "圣卢西亚");
            put("Samoa", "萨摩亚");
            put("San Marino", "圣马力诺");
            put("Scarborough Reef", "黄岩岛");
            put("Senegal", "塞内加尔");
            put("Serbia", "塞尔维亚");
            put("Seychelles", "塞舌尔");
            put("Sierra Leone", "塞拉利昂");
            put("Singapore", "新加坡");
            put("Slovakia", "斯洛伐克");
            put("Somalia", "索马里");
            put("Somaliland", "索马里兰");
            put("South Africa", "南非");
            put("South Korea", "大韩民国");
            put("Spain", "西班牙");
            put("Sri Lanka", "斯里兰卡");
            put("Suriname", "苏利南");
            put("Sweden", "瑞典");
            put("Switzerland", "瑞士");
            put("Syria", "叙利亚");
            put("Tanzania", "坦桑尼亚");
            put("Thailand", "泰国");
            put("Timor-Leste", "东帝汶");
            put("Togo", "多哥");
            put("Tonga", "东加");
            put("Tunisia", "突尼西亚");
            put("Turkey", "土耳其");
            put("Tuvalu", "吐瓦鲁");
            put("Uganda", "乌干达");
            put("Ukraine", "乌克兰");
            put("United Kingdom", "英国");
            put("United States of America", "美国");
            put("Uruguay", "乌拉圭");
            put("Vanuatu", "万那杜");
            put("Vatican", "梵蒂冈");
            put("Venezuela", "委内瑞拉");
            put("Vietnam", "越南");
            put("W. Sahara", "西撒哈拉");
            put("World", "全球");
            put("Yemen", "也门");
            put("Zambia", "赞比亚");
            put("Zimbabwe", "辛巴威");
            put("eSwatini", "斯威士兰");
            put("Åland", "奥兰群岛");
        }
    };

    @SuppressLint("CheckResult")
    Manager() {
    }
    public static String getName(String country){
        return urlMap.getOrDefault(country, null);
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
                int total = fetch.getTotal(type);
                if(total == -1){
                    Log.e("Manager","Error on getting total");
                }
                int sub = 0;//与最新一条的差距
                if(getNew){//下拉刷新，拉取最新20条
                    if(type.equals("news")){
                        fetch.setCurrent_n(total);//拉取的是最新的
                    }
                    else{
                        fetch.setCurrent_p(total);
                    }
                }
                else{//上拉加载更多
                    if(type.equals("news")){
                        int backwardIndex_n = fetch.checkCurrent_n();
                        sub = total-backwardIndex_n+20;//拉取后面20条
                        fetch.setCurrent_n(backwardIndex_n-20);
                    }
                    else{
                        int backwardIndex_p = fetch.checkCurrent_p();
                        sub = total-backwardIndex_p+20;//拉取后面20条
                        fetch.setCurrent_p(backwardIndex_p-20);
                    }
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

    public static void getCluster(Observer<List<News>> observer, final String type, final Context context){
        Observable.create(new ObservableOnSubscribe<List<News>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<News>> e) throws Exception{
                List<News> result = new ArrayList<>();
                if(!type.equals("传播")&&!type.equals("抗体")&&!type.equals("易感人群")&&!type.equals("疫情")&&!type.equals("疫苗")&&!type.equals("病毒")&&!type.equals("药品")){
                    Log.e("Manager","wrong type:"+type);
                    e.onNext(result);
                    e.onComplete();
                }
                try{
                    InputStream in = context.getAssets().open(type+".txt");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while(reader.ready()){
                        News temp = new News();
                        temp.setTitle(reader.readLine());
                        result.add(temp);
                    }
                    in.close();
                    reader.close();
                }
                catch(IOException exception){
                    exception.printStackTrace();
                }
                e.onNext(result);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())//Observable在哪个线程（不能放在主线程）
                .observeOn(AndroidSchedulers.mainThread())//Observer在哪个线程
                .subscribe(observer);
    }
    public static void getResearcher(Observer<List<Researcher>> observer, final boolean passed_away){
        Observable.create(new ObservableOnSubscribe<List<Researcher>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Researcher>> e) throws Exception{
                if(fetch==null){
                    fetch = new Fetch();
                }
                e.onNext(fetch.fetchResearcher(passed_away));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())//Observable在哪个线程（不能放在主线程）
                .observeOn(AndroidSchedulers.mainThread())//Observer在哪个线程
                .subscribe(observer);
    }
}



