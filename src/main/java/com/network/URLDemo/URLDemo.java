/**
 * Created with IntelliJ IDEA.
 * User: gxm
 * Date: 2019/10/20
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 * Description:
 **/
package com.network.URLDemo;

import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
/*
*   1. 协议
*   2. 域名、计算机
*   3. 端口：默认 80
*   4. 请求资源
*   http://www.baidu.com:80/index.html?uname=shsxt&age=18#a
* */
public class URLDemo {
    public static void main(String[] args) throws MalformedURLException {//格式错误异常
        String URLString = "http://www.baidu.com:80/index.html?uname=shsxt&age=18#a";
        URL url = new URL(URLString);
        //获取上述步骤的四个值
        System.out.println("协议："+url.getProtocol());
        System.out.println("主机名："+url.getHost());
        System.out.println("端口："+url.getPort());
        System.out.println("请求资源1："+url.getFile());
        System.out.println("请求资源2：" + url.getFile());
        System.out.println("参数："+url.getQuery());
        System.out.println("锚点："+url.getRef());

    }
}
