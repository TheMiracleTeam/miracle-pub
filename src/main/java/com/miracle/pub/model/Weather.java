package com.miracle.pub.model;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 总体天气实体类
 * @Author: ZhengHuanBin
 * @Date: 2018-07-25 21:45
 */
public class Weather {
    private int status; //请求状态码  null:参数无效，200:成功，304:调用频繁
    private String city;    //城市
    private String shidu;   //当前湿度
    private String wendu;   //当前温度
    private BigDecimal pm25;    //pm2.5
    private BigDecimal pm10;    //pm10
    private String quality; //空气质量
    private String ganmao;  //预防感冒
    private Map<String,DailyWeather> dailyWeatherMap;   //日常天气Map

    @Override
    public String toString() {
        return "Weather{" +
                "status=" + status +
                ", city='" + city + '\'' +
                ", shidu='" + shidu + '\'' +
                ", wendu='" + wendu + '\'' +
                ", pm25='" + pm25 + '\'' +
                ", pm10='" + pm10 + '\'' +
                ", quality='" + quality + '\'' +
                ", ganmao='" + ganmao + '\'' +
                ", dailyWeatherMap=" + dailyWeatherMap +
                '}';
    }

    public String toContentString() {
        return "城市："+city+"\n" +
                "温度："+wendu+"，\t湿度："+shidu+"\n" +
                "pm2.5："+pm25+"，\tpm10："+pm10+"\n" +
                "空气质量："+quality+"\n" +
                "预防感冒："+ganmao+"\n\n" +
                "---------------" +
                dailyWeatherMap.get("today").toContentString() +
                dailyWeatherMap.get("tomorrow").toContentString() +
                dailyWeatherMap.get("houtian").toContentString();
    }

    public Weather(int status, String city, String shidu, String wendu, BigDecimal pm25, BigDecimal pm10, String quality, String ganmao) {
        this.status = status;
        this.city = city;
        this.shidu = shidu;
        this.wendu = wendu;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.quality = quality;
        this.ganmao = ganmao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public BigDecimal getPm25() {
        return pm25;
    }

    public void setPm25(BigDecimal pm25) {
        this.pm25 = pm25;
    }

    public BigDecimal getPm10() {
        return pm10;
    }

    public void setPm10(BigDecimal pm10) {
        this.pm10 = pm10;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public Map<String, DailyWeather> getDailyWeatherMap() {
        return dailyWeatherMap;
    }

    public void setDailyWeatherMap(Map<String, DailyWeather> dailyWeatherMap) {
        this.dailyWeatherMap = dailyWeatherMap;
    }
}