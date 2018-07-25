package com.miracle.pub.util;

import java.security.MessageDigest;

/**
 * SHA1加密类
 * @Author: ZhengHuanBin
 * @Date: 2018-07-25 16:51:15
 */
public class SHA1 {

    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * sha1加密
     * @param str   原始字符串
     * @return  加密后的字符串
     */
    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从摘要中提取原始字节，并将它们格式化为正确。
     * @param bytes 来自摘要的原始字节。
     * @return  格式化的字节。
     */
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

}
