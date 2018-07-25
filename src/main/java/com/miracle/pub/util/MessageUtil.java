package com.miracle.pub.util;

import com.miracle.pub.reply.Constant;
import com.miracle.pub.reply.ReplyTextMsg;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.PrintWriter;

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
            if (Content.equals("天气-")) {
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
}
