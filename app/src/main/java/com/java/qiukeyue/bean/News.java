package com.java.qiukeyue.bean;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;

public class News extends SugarRecord {

    /**
     * _id : 5f5488499fced0a24b8c27ad
     * category :
     * content : 6 (Xinhua) -- Eyes bloodshot and voice hoarse, Luo Shuiguang has worked till dawn for three nights in a COVID-19 testing laboratory of Hong Kong.,Luo, a technologist in the clinical laboratory of Huangjiang Hospital in Dongguan, Guangdong province, arrived in Hong Kong on Aug. 28 as a member of the third nucleic testing support team from the Chinese mainland.,The government of the Hong Kong Special Administrative Region (HKSAR) launched Tuesday the Universal Community Testing Program to find asymptomatic carriers of coronavirus and cut transmission chains.,Hong Kong residents aged no less than six can get tested for free and voluntarily.,A total of 141 swab collection stations are operating across Hong Kong and specimens are sent to the "Fire Eye Laboratory" at Sun Yat Sen Memorial Park Sports Center where Luo works.,The lab was built by mainland testing service provider BGI to boost Hong Kong's testing capacity.,Hong Kong has witnessed a new round of the COVID-19 outbreak since early July, which overstretched hospitals and testing institutions.,At the request of the HKSAR government, the central government swiftly set up support teams to assist the anti-epidemic battle in Hong Kong.,After the first seven testing professionals came to Hong Kong on Aug. 2, many more arrived successively.,By Friday, there had been more than 420 mainland medical staff working in Hong Kong.,The testing program has so far gone well thanks to the efforts of both mainland and Hong Kong medical staff and the support of Hong Kong residents.,The support teams have received a warm welcome from Hong Kong residents.,The testing concerns the well-being of all Hong Kong residents and the more people get tested, the better Hong Kong's anti-epidemic work will be, he said.
     * date : Sun, 06 Sep 2020 06:40:35 GMT
     * entities : [{"label":"hong kong residents","url":"http://xlore.org/instance/eni2367574"},{"label":"sun yat sen memorial park","url":"http://xlore.org/instance/eni1154861"},{"label":"bloodshot","url":"http://xlore.org/instance/eni604593"},{"label":"asymptomatic","url":"http://xlore.org/instance/eni223142"},{"label":"hksar government","url":"http://xlore.org/instance/eni86292"},{"label":"coronavirus","url":"http://xlore.org/instance/eni105371"},{"label":"chinese mainland","url":"http://xlore.org/instance/eni2353"},{"label":"hong kong special administrative region","url":"http://xlore.org/instance/eni6284"},{"label":"dongguan","url":"http://xlore.org/instance/eni250510"},{"label":"guangdong province","url":"http://xlore.org/instance/eni31892"},{"label":"xinhua","url":"http://xlore.org/instance/eni126120"},{"label":"COVID-19","url":"https://covid-19.aminer.cn/kg/resource/R332"},{"label":"Community","url":"https://covid-19.aminer.cn/kg/class/community"},{"label":"Guangdong province","url":"https://covid-19.aminer.cn/kg/resource/R25325"},{"label":"coronavirus","url":"https://covid-19.aminer.cn/kg/class/coronavirus"},{"label":"medical","url":"https://covid-19.aminer.cn/kg/class/medical"},{"label":"outbreak","url":"https://covid-19.aminer.cn/kg/resource/R1591"},{"label":"Guangdong","url":"https://covid-19.aminer.cn/kg/resource/R25576"},{"label":"aged","url":"https://covid-19.aminer.cn/kg/class/aged"}]
     * geoInfo : [{"geoName":"Hong Kong Special Administrative Region","latitude":"22.25","longitude":"114.16667","originText":"Hong Kong"},{"geoName":"Chinagarten Zürich","latitude":"47.3546","longitude":"8.55216","originText":"Chinese"},{"geoName":"Dongguan","latitude":"35.52258","longitude":"105.74175","originText":"Dongguan"}]
     * id : xinhua13934675711110320
     * lang : en
     * source : XINHUANET
     * tflag : 1599375433081
     * time : 2020-09-06 14:40:35
     * title : Feature: Mainland medical experts fighting COVID-19 in Hong Kong
     * type : news
     * urls : ["http://www.xinhuanet.com/english/2020-09/06/c_139346757.htm"]
     */

    private String _id;
    private String category;
    private String content;
    private String date;
    private String source;
    private String time;
    private String title;
    private String type;
    @Ignore
    private boolean visited;

    public boolean getVisited() {
        return visited;
    }

    public void setVisited(boolean visited){
        this.visited = visited;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
