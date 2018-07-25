package com.miracle.pub.util;

import com.alibaba.fastjson.JSONObject;
import com.miracle.pub.reply.Constant;
import com.miracle.pub.reply.ReplyTextMsg;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.PrintWriter;
import java.util.*;

/**
 * 消息工具类
 * @Author: ZhengHuanBin
 * @Date: 2018-07-25 0:52
 */
public class MessageUtil {
    /**
     * replyMessage方法：以XML形式返回消息给微信
     * @param message
     * @param out
     */
    public static void replyMessage(String message, PrintWriter out) {
        Document document = XMLUtil.readString2XML(message);
        Element root = document.getRootElement();
        String MsgType = XMLUtil.readNode(root, "MsgType");
        String Content = XMLUtil.readNode(root, "Content");
        System.out.println("MsgType"+MsgType);
        System.out.println("Content"+Content);
        if (MsgType.equals(Constant.MSGTYPE_TEXT)) {
            System.out.println("进来了");
            ReplyTextMsg textMsg = new ReplyTextMsg();
            textMsg.setFromUserName(Constant.DEVELOPERWXID);
            textMsg.setToUserName(XMLUtil.readNode(root, "FromUserName"));
            textMsg.setCreateTime();
            //将XML消息的参数都转化内容回复给微信
            XMLUtil.content = "";
            String nodeString = XMLUtil.readNodes(root);
            if (Content.contains("天气-")) {
                String address = Content.substring(3,Content.length());
                try {
                    String result = WeatherUtil.getWeather(address);
                    textMsg.setContent(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                textMsg.setContent(nodeString);
            }
            textMsg.setMsgType(Constant.MSGTYPE_TEXT);
            try {
                //将对象转化为XML
                String replyMsg = textMsg.Msg2Xml();
                System.out.println(replyMsg);
                out.println(replyMsg);
                System.out.println("");
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Map<String, Object> parseJSON2Map(String jsonStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject json = JSONObject.parseObject(jsonStr);
        for(Object k : json.keySet()){
            Object v = json.get(k);
            map.put(k.toString(), v);
        }
        return map;
    }

    public static void main(String[] args) {
        String str = "{\"0\":\"zhangsan\",\"1\":\"lisi\",\"2\":\"wangwu\",\"3\":{\"date\":\"24日星期二\",\"sunrise\":\"05:04\",\"high\":\"高温 28.0℃\",\"low\":\"低温 24.0℃\",\"sunset\":\"19:36\",\"aqi\":21.0,\"fx\":\"北风\",\"fl\":\"3-4级\",\"type\":\"大雨\",\"notice\":\"出门最好穿雨衣，勿挡视线\"}}";
        Map<String, Object> map = parseJSON2Map(str);
        System.out.println(map.toString());
    }
}
