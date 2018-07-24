package com.miracle.pub.util;

import com.miracle.pub.reply.Constant;
import com.miracle.pub.reply.ReplyTextMsg;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.PrintWriter;

/**
 * @Description:
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
        System.out.println("MsgType"+MsgType);
        if (MsgType.equals(Constant.MSGTYPE_TEXT)) {
            System.out.println("进来了");
            ReplyTextMsg textMsg = new ReplyTextMsg();
            textMsg.setFromUserName(Constant.DEVELOPERWXID);
            textMsg.setToUserName(XMLUtil.readNode(root, "FromUserName"));
            textMsg.setCreateTime();
            //将XML消息的参数都转化内容回复给微信
            XMLUtil.content = "";
            String nodeString = XMLUtil.readNodes(root);
            textMsg.setContent(nodeString);
            textMsg.setMsgType(Constant.MSGTYPE_TEXT);
            try {
                //将对象转化为XML
                String replyMsg = textMsg.Msg2Xml();
                out.println(replyMsg);
                System.out.println("");
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
