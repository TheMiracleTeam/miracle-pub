package com.miracle.pub.controller;

import com.miracle.pub.util.MessageUtil;
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
