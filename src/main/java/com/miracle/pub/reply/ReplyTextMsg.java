package com.miracle.pub.reply;

import com.thoughtworks.xstream.XStream;

/**
 * 返回文本消息类
 * @Author: ZhengHuanBin
 * @Date: 2018-07-25 0:50
 */
public class ReplyTextMsg extends ReplyBaseMessage {

    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
    /**
     * 将对象转换为XML
     * @return
     */
    public String Msg2Xml(){
        XStream xstream=new XStream();
        xstream.alias("xml", this.getClass());
        return xstream.toXML(this);
    }
}
