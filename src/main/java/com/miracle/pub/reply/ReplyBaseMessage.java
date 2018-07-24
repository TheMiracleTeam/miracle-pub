package com.miracle.pub.reply;

/**
 * @Description:返回消息基类
 * @Author: ZhengHuanBin
 * @Date: 2018-07-25 0:49
 */
public class ReplyBaseMessage {
    protected String ToUserName;
    protected String FromUserName;
    protected String CreateTime;
    protected String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime() {
        CreateTime = String.valueOf(System.currentTimeMillis()).substring(0,10);
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
