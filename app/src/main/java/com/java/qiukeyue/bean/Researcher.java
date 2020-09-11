package com.java.qiukeyue.bean;

import java.util.List;

public class Researcher {

    /**
     * aff : {}
     * avatar : https://static.aminer.cn/upload/avatar/1273/1967/1116/53f4495cdabfaeb22f4cc34d_2.jpg
     * bind : false
     * id : 53f4495cdabfaeb22f4cc34d
     * indices : {"activity":32.7103,"citations":11939,"diversity":4.2613,"gindex":100,"hindex":48,"newStar":2.1675,"pubs":519,"risingStar":2.1675,"sociability":7.8339}
     * name : Nanshan Zhong
     * name_zh : 钟南山
     * num_followed : 5
     * num_viewed : 7053
     * profile : {"address":"","affiliation":"国家呼吸系统疾病临床医学研究中心/国家卫健委/广州呼吸疾病研究所","affiliation_zh":"中华医学会","bio":"钟南山（1936.10.20- ）呼吸病学学家。福建厦门人。1960年毕业于北京医科大学医疗系，获临床医学学士学位。现任呼吸疾病国家重点实验室主任、国家呼吸疾病临床医学研究中心主任，曾任中华医学会第23任会长。<br><br>通过创制的\u201c简易气道反应性测定法\u201d及流行病学调查，首次证实并完善\u201c隐匿型哮喘\u201d的概念。对我国慢性咳嗽病因谱进行了系统的分析，阐明了胃食道反流性咳嗽的气道神经炎症机制，创制运动膈肌功能测定法。牵头主持我国\u201c十五\u201d科技攻关项目慢性阻塞性肺疾病（COPD）人群防治的系统研究，并获广东省科技进步一等奖（2013年）。在2003年我国SARS疫情中，明确了广东的病原学，组织了广东省SARS防治研究，创建了\u201c合理使用皮质激素，合理使用无创通气，合理治疗并发症\u201d的方法治疗危重SARS患者，获国际上的存活率（96.2%）。组织整理了国内支气管哮喘、慢性阻塞性肺疾病、咳嗽、SARS、人高致病性禽流感等方面的诊治指南文件。2013年任广东省H7N9防控专家组组长，并将H7N9系列研究发表在《New England Journal Medicine》（IF51.658）上，对H7N9防控做出重要贡献。2015年成功治愈广州首例H5N6患者。曾荣获全国先进工作者，全国五一劳动奖章等荣誉称号。<br>1996年当选中国工程院院士。","edu":"1960年毕业于北京医学院（现北京大学医学部）并留校任教。1970年到广州医学院进修。\n1979年4月曾到英国进修。\n2007年获得英国爱丁堡大学荣誉博士学位。\n2010年获得澳门科技大学荣誉博士学位。\n1981年8月结束进修归国。","email":"","email_cr":"","emails_u":[{"address":"nanshan@vip.163.com","src":"google","weight":0.90591705},{"address":"nanshan@vip.163.com","src":"google","weight":0.4261646},{"address":"zhxwwk@mail.sysu.edu.cn","src":"google","weight":0.21653663},{"address":"jianwang1986@yahoo.com","src":"google","weight":0.06067852},{"address":"suhua333333@163.com","src":"google","weight":0.052163094},{"address":"cjbme@vip.tom.com","src":"google","weight":0.052163094}],"fax":"","homepage":"http://www.cae.cn/cae/html/main/colys/71145511.html ","note":"教育来源https://baike.baidu.com/item/%E9%92%9F%E5%8D%97%E5%B1%B1/653914?fr=aladdin<br>无电话、传真、邮箱信息。<br>论文：https://www.ncbi.nlm.nih.gov/pubmed","phone":"","position":"中国工程院院士、主任、组长","work":"1960.07-1971.08北京医学院放射医学教研组 助教\n1971.09-1974.04广州医学院第一附属医院内科 住院医师\n1974.04-1978.08 广州医学院第一附属医院内科主治医师\n1978.09-1982.12 广州医学院第一附属医院内科副主任医师、讲师\n1979.10至今 广州呼吸疾病研究所副所长、所长\n1983.01-1986.12 广州医学院副教授\n1986.12至今 广州医学院教授\n1986.12至今 广州医学院呼吸内科硕士导师\n1987.12-1992.10 广州医学院第一附属医院院长\n1992.5-2002.12 广州医学院院长、党委书记\n1995.08至今 北京大学医学部呼吸内科博士生导师\n1996.02-至今中国工程院院士\n2007.10至今 广州呼吸疾病国家重点实验室主任\n2009.7.26至今 任贵州医科大学名誉院长"}
     * score : 1
     * sourcetype : person
     * tags : ["Asthma","Biomedical Research","Bioinformatics","Copd","Chronic Obstructive Pulmonary Disease","China","Epidemiology","Risk Factor","Cough","Airway Inflammation","Lung Cancer","Lung-Function","Medicine","Air Pollution","Pulmonary Disease","Eosinophil","Physics","Chronic Cough","Chemistry","Biology"]
     * tags_score : [30,19,19,18,14,14,12,11,11,9,8,8,8,8,8,7,7,7,7,7]
     * index : 0
     * tab : 0
     * is_passedaway : false
     */

    private String avatar;
    private String id;
    private IndicesBean indices;
    private String name;
    private String name_zh;
    private ProfileBean profile;
    private boolean is_passedaway;
    private java.util.List<String> tags;
    private java.util.List<Integer> tags_score;

    public static class IndicesBean{
        private double activity;
        private int citations;
        private double diversity;
        private int gindex;
        private int hindex;
        private double newStar;
        private int pubs;
        private double risingStar;
        private double sociability;

        public double getActivity() {
            return activity;
        }

        public void setActivity(double activity) {
            this.activity = activity;
        }

        public int getCitations() {
            return citations;
        }

        public void setCitations(int citations) {
            this.citations = citations;
        }

        public double getDiversity() {
            return diversity;
        }

        public void setDiversity(double diversity) {
            this.diversity = diversity;
        }

        public int getGindex() {
            return gindex;
        }

        public void setGindex(int gindex) {
            this.gindex = gindex;
        }

        public int getHindex() {
            return hindex;
        }

        public void setHindex(int hindex) {
            this.hindex = hindex;
        }

        public double getNewStar() {
            return newStar;
        }

        public void setNewStar(double newStar) {
            this.newStar = newStar;
        }

        public int getPubs() {
            return pubs;
        }

        public void setPubs(int pubs) {
            this.pubs = pubs;
        }

        public double getRisingStar() {
            return risingStar;
        }

        public void setRisingStar(double risingStar) {
            this.risingStar = risingStar;
        }

        public double getSociability() {
            return sociability;
        }

        public void setSociability(double sociability) {
            this.sociability = sociability;
        }
    }
    public static class ProfileBean{
        private String address;
        private String affiliation;
        private String bio;
        private String edu;
        private String email;
        private String fax;
        private String homepage;
        private String position;
        private String work;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAffiliation() {
            return affiliation;
        }

        public void setAffiliation(String affiliation) {
            this.affiliation = affiliation;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getEdu() {
            return edu;
        }

        public void setEdu(String edu) {
            this.edu = edu;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getHomepage() {
            return homepage;
        }

        public void setHomepage(String homepage) {
            this.homepage = homepage;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IndicesBean getIndices() {
        return indices;
    }

    public void setIndices(IndicesBean indices) {
        this.indices = indices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_zh() {
        return name_zh;
    }

    public void setName_zh(String name_zh) {
        this.name_zh = name_zh;
    }

    public ProfileBean getProfile() {
        return profile;
    }

    public void setProfile(ProfileBean profile) {
        this.profile = profile;
    }

    public boolean isIs_passedaway() {
        return is_passedaway;
    }

    public void setIs_passedaway(boolean is_passedaway) {
        this.is_passedaway = is_passedaway;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Integer> getTags_score() {
        return tags_score;
    }

    public void setTags_score(List<Integer> tags_score) {
        this.tags_score = tags_score;
    }
}
