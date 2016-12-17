package com.qianfeng.com.a6_6foods.bean;

/**
 * Created by Administrator on 2016/8/1.
 */
public class FoodDetail {
    /**
     * count : 2859
     * description : 棕榈油的话你可以尝尝味道有没有酸味，最好是让对方提供一份检验报告之类的东西，还有就是油的色泽，是否澄清透明
     * disease : 胆固醇脓胸,家族性高胆固醇血症,猝死型冠心病,胆囊胆固醇沉着症,磷脂酰胆碱-胆固醇酰基转移酶缺乏,胆固醇肺炎,小儿细胞外胆固醇沉着综合征,蓝鼓膜与胆固醇肉芽肿,冠心病
     * fcount : 0
     * food : 板绞油,油抒,羊油,玉米油,玛琪琳,裹入油,油皮,油面,油豆角,油豆腐,鸭油,鹅油卷,青甘鱼,酱油露,白脱油,芝麻油,牛油汁,炼制猪油,胡麻油,棕榈油,猪网油,猪背油
     * id : 1599
     * img : /food/150804/2ad85610af25a980b5fd6d1828bbade1.jpg
     * keywords : 棕榈油 体质 食用 就是 饱和
     * message : <strong>棕榈油</strong>的食疗价值
     <p>　　棕榈油含有饱和脂肪酸和不饱和脂肪酸的比例均衡，大约有44%的棕榈酸，5%的硬脂酸(两种均为饱和酸)，40%的油酸(不饱和酸)，10%的亚油酸和0.4%的α-亚麻酸(两种都是多不饱和酸)。像其它植物油一样，棕榈油不含胆固醇(4，5)。具有抗氧化性、抗癌和降低胆固醇等对健康有益的特性。</p> 食用
     <strong>棕榈油</strong>的日常注意事项
     <p>哪些体质的人适宜吃棕榈油?（测一测你的体质）</p>健康体质平和质
     <p>哪些疾病的患者不适宜食用棕榈油?</p>糖尿病
     <p>哪些体质的人不适宜食用棕榈油?（测一测你的体质）</p>特禀体质
     <p>食用禁忌</p>
     <p>　　对于糖尿病患者，应当少食用棕榈油。</p> 辨别
     <strong>棕榈油</strong>的好坏
     <p>知名产区:</p>
     <p>东亚</p>
     <p>生成过程</p>棕榈油是从油棕树上的棕果中榨取出来的。
     <p>怎么辨别棕榈油的好坏?</p>
     <p>　　棕榈油的话你可以尝尝味道有没有酸味，最好是让对方提供一份检验报告之类的东西，还有就是油的色泽，是否澄清透明。你说的44°以上的可能是棕榈硬脂油，这个油的话熔点高你可以看是否马上冻结，还有一个就是看是否含有水分。当然最好的话就是带个检验人员一起过去当场检测油的酸价和过氧化值。</p>
     * name : 棕榈油
     * rcount : 0
     * status : true
     * summary : <p>【性质】平</p>
     <p>【五味】辛</p>
     <p>【热量】900.00大卡(千焦)/100克 </p>
     <p>【功效】抑癌抗瘤</p>
     <p>【棕榈油是什么】 棕榈油是从油棕树上的棕果(Elaeis Guineensis)中榨取出来的，果肉压榨出的油称为棕榈油( Palm Oil)，而果仁压榨出的油称为棕榈仁油(Palm Kernel Oil)，两种油的成分大不相同。棕榈油主要含有棕榈酸(C 16)和油酸(C 18)两种最普通的脂肪酸，棕榈油的饱和程度约为50%;棕榈仁油主要含有月桂酸(C 12)，饱和程度达80...</p>
     * symptom : 煤尘或胆固醇结晶的黏痰,近迫性心肌梗塞,痉挛性偏瘫步态,脑血管痉挛,血浆胆固醇水平高,心脑血管意外,脑血管动静脉畸形,对侧肢体偏瘫,偏瘫
     * url : http://www.tngou.net/food/show/1599
     */

    private int count;
    private String description;
    private String disease;
    private int fcount;
    private String food;
    private int id;
    private String img;
    private String keywords;
    private String message;
    private String name;
    private int rcount;
    private boolean status;
    private String summary;
    private String symptom;
    private String url;

    public FoodDetail() {
    }

    public FoodDetail(int count, String description, String disease, int fcount, String food, int id, String img, String keywords, String message, String name, int rcount, boolean status, String summary, String symptom, String url) {
        this.count = count;
        this.description = description;
        this.disease = disease;
        this.fcount = fcount;
        this.food = food;
        this.id = id;
        this.img = img;
        this.keywords = keywords;
        this.message = message;
        this.name = name;
        this.rcount = rcount;
        this.status = status;
        this.summary = summary;
        this.symptom = symptom;
        this.url = url;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

