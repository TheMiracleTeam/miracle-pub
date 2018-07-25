package com.miracle.pub.model;

import java.math.BigDecimal;

/**
 * 日常天气类
 * @Author: ZhengHuanBin
 * @Date: 2018-07-25 22:00
 */
public class DailyWeather {
    private String date;    //日期
    private String sunrise; //日出时间
    private String sunset;  //日落时间
    private String high;    //最高温度
    private String low;     //最低温度
    private BigDecimal aqi;     //空气质量指数(AQI)
    private String fx;      //风向
    private String fl;      //风力
    private String type;    //天气类型
    private String notice;  //提醒

    @Override
    public String toString() {
        return "DailyWeather{" +
                "date='" + date + '\'' +
                ", sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", aqi=" + aqi +
                ", fx='" + fx + '\'' +
                ", fl='" + fl + '\'' +
                ", type='" + type + '\'' +
                ", notice='" + notice + '\'' +
                '}';
    }

    public String toContentString() {
        return "\n"+date+"，\t"+type+"\n" +
                "日出："+sunrise+"，\t日落："+sunset+"\n" +
                "温度："+high+"，\t"+low+"\n" +
                "风向："+fx+"，\t风力："+fl+"\n" +
                "空气质量指数(AQI)："+aqi+"\n" +
                "温馨提醒："+notice+"\n\n" +
                "---------------";
    }

    public DailyWeather(String date, String sunrise, String sunset, String high, String low, BigDecimal aqi, String fx, String fl, String type, String notice) {
        this.date = date;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.high = high;
        this.low = low;
        this.aqi = aqi;
        this.fx = fx;
        this.fl = fl;
        this.type = type;
        this.notice = notice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public BigDecimal getAqi() {
        return aqi;
    }

    public void setAqi(BigDecimal aqi) {
        this.aqi = aqi;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
