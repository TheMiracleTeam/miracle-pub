package com.miracle.pub.controller;

import com.miracle.pub.util.MessageUtil;
import com.miracle.pub.util.SHA1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class MiracleController {

    private String Token = "zhbtest";

    @RequestMapping("/")
    public ModelAndView index(ModelMap modelMap) {
        return new ModelAndView("index");
    }

    @RequestMapping("/json")
    @ResponseBody
    public String json() {
        return "HelloWorld1";
    }

    /**
     * 接收消息，被动回复
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/chat")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        if (isGet) {
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            System.out.println(signature);
            System.out.println(timestamp);
            System.out.println(nonce);
            System.out.println(echostr);
            access(request, response);
        } else {
            //接受微信服务器发送过来的XML形式的消息
            InputStream in=request.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String sReqData="";
            String itemStr="";//作为输出字符串的临时串，用于判断是否读取完毕
            while((itemStr=reader.readLine())!=null){
                sReqData+=itemStr;
            }
            in.close();
            reader.close();

            System.out.println("收到消息："+sReqData);
            //防止中文乱码
            response.setCharacterEncoding("UTF-8");
            MessageUtil.replyMessage(sReqData,response.getWriter());
        }
    }

    /**
     * 验证URL真实性
     *
     * @author morning
     * @date 2015年2月17日 上午10:53:07
     * @param request
     * @param response
     * @return String
     */
    private String access(HttpServletRequest request, HttpServletResponse response) {
        // 验证URL真实性
        System.out.println("进入验证access");
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");// 随机字符串
        List<String> params = new ArrayList<String>();
        params.add(Token);
        params.add(timestamp);
        params.add(nonce);
        // 1. 将token、timestamp、nonce三个参数进行字典序排序
        Collections.sort(params, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        String temp = SHA1.encode(params.get(0) + params.get(1) + params.get(2));
        if (temp.equals(signature)) {
            try {
                response.getWriter().write(echostr);
                System.out.println("成功返回 echostr：" + echostr);
                return echostr;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("失败 认证");
        return null;
    }
}
