package com.miracle.pub.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.miracle.pub.model.DailyWeather;
import com.miracle.pub.model.Weather;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * 调用天气api工具类
 * @author: ZHB
 * @date: 2018-07-25 17:26
 */
public class WeatherUtil {

    public static String getWeather(String address){
        address = address.trim();
        String result = null;
        try {
            //参数url化
            String city = java.net.URLEncoder.encode(address, "utf-8");

            //拼地址
            String apiUrl = String.format("https://www.sojson.com/open/api/weather/json.shtml?city=%s",city);
            //开始请求
            URL url= new URL(apiUrl);
            URLConnection open = url.openConnection();
            InputStream input = open.getInputStream();
            InputStreamReader reader=new InputStreamReader(input);
            //这里转换为String，带上包名，怕你们引错包
            result = org.apache.commons.io.IOUtils.toString(input,"utf-8");
            System.out.println("result:"+result);
            /*开始处理返回的天气字符串格式*/
            if (result != null) {
                return formatWeatherMsg(result);
            }
            return result;
        } catch (Exception e) {
            return "获取天气接口失败";
        }
    }

    /**
     * 对 调用天气api返回的字符串 格式化
     * @param msg
     * @return  格式化后的字符串
     */
    public static String formatWeatherMsg(String msg){
        Map map = MessageUtil.parseJSON2Map(msg);
        if (map.get("status") == null) {
            return "地区名称不存在，请检查一下哦";
        }
        int status = (int)map.get("status");
        Weather weather = null;
        if (status == 200) {
            Map dataMap = MessageUtil.parseJSON2Map(((JSONObject) map.get("data")).toString());
            weather = new Weather(status, (String) map.get("city"),
                    (String) dataMap.get("shidu"),
                    (String) dataMap.get("wendu"),
                    (BigDecimal) dataMap.get("pm25"),
                    (BigDecimal) dataMap.get("pm10"),
                    (String) dataMap.get("quality"),
                    (String) dataMap.get("ganmao"));
            System.out.println(weather.toString());
            Map todayMap = MessageUtil.parseJSON2Map(((JSONArray)dataMap.get("forecast")).get(0).toString());
            Map tomorrowMap = MessageUtil.parseJSON2Map(((JSONArray)dataMap.get("forecast")).get(1).toString());
            Map houtianMap = MessageUtil.parseJSON2Map(((JSONArray)dataMap.get("forecast")).get(2).toString());
            DailyWeather today = new DailyWeather(
                    (String )todayMap.get("date"), (String )todayMap.get("sunrise") ,
                    (String )todayMap.get("sunset"), (String )todayMap.get("high") ,
                    (String )todayMap.get("low"), (BigDecimal )todayMap.get("aqi"),
                    (String )todayMap.get("fx"), (String )todayMap.get("fl"),
                    (String )todayMap.get("type"), (String )todayMap.get("notice"));
            DailyWeather tomorrow = new DailyWeather(
                    (String )tomorrowMap.get("date"), (String )tomorrowMap.get("sunrise") ,
                    (String )tomorrowMap.get("sunset"), (String )tomorrowMap.get("high") ,
                    (String )tomorrowMap.get("low"), (BigDecimal )tomorrowMap.get("aqi"),
                    (String )tomorrowMap.get("fx"), (String )tomorrowMap.get("fl"),
                    (String )tomorrowMap.get("type"), (String )tomorrowMap.get("notice"));
            DailyWeather houtian = new DailyWeather(
                    (String )houtianMap.get("date"), (String )houtianMap.get("sunrise") ,
                    (String )houtianMap.get("sunset"), (String )houtianMap.get("high") ,
                    (String )houtianMap.get("low"), (BigDecimal )houtianMap.get("aqi"),
                    (String )houtianMap.get("fx"), (String )houtianMap.get("fl"),
                    (String )houtianMap.get("type"), (String )houtianMap.get("notice"));
            System.out.println(today.toString());
            System.out.println(tomorrow.toString());
            System.out.println(houtian.toString());
            Map<String,DailyWeather> dailyWeatherMap = new HashMap<String,DailyWeather>();
            dailyWeatherMap.put("today",today);
            dailyWeatherMap.put("tomorrow",tomorrow);
            dailyWeatherMap.put("houtian",houtian);
            weather.setDailyWeatherMap(dailyWeatherMap);
            System.out.println(weather.toContentString());
            return weather.toContentString();
        } else if (status == 304) {
            return "获取频繁，请3秒后再获取";
        } else {
            return "奇怪的状态码status："+status;
        }
    }

    /**
     * 测试用的
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //参数url化
        String city = java.net.URLEncoder.encode("北京", "utf-8");

        //拼地址
        String apiUrl = String.format("https://www.sojson.com/open/api/weather/json.shtml?city=%s",city);
        //开始请求
        URL url= new URL(apiUrl);
        URLConnection open = url.openConnection();
        InputStream input = open.getInputStream();
        //这里转换为String，带上包名，怕你们引错包
        String result = org.apache.commons.io.IOUtils.toString(input,"utf-8");
        //输出
        System.out.println(result);
        formatWeatherMsg(result);
    }
}
