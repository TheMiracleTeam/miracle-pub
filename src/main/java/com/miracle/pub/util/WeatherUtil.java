package com.miracle.pub.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 调用天气api工具类
 * @author: ZHB
 * @date: 2018-07-25 17:26
 */
public class WeatherUtil {

    public static String getWeather(String address) throws Exception{
        //参数url化
        String city = java.net.URLEncoder.encode(address, "utf-8");

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
        return result;
    }

    public static void main(String[] args) throws Exception {
            //参数url化
            String city = java.net.URLEncoder.encode("北京1", "utf-8");

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
    }
}
