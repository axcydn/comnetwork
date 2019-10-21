/**
 * Created with IntelliJ IDEA.
 * User: gxm
 * Date: 2019/10/20
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 * Description:
 **/
package com.network.URLDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/*
 *   爬虫原理：
 *   1. 获取URL
 *   2. 下载资源
 *   3. 分析
 *   4. 并非所有网站均可有权限访问
 *       解决方法一： 模拟浏览器  使用openConnection
 * */
public class SpiderTest01 {
    public static void main(String[] args) throws IOException {
        //String urlString = "https://www.jd.com";
        String urlString = "https://www.dianping.com";
        URL url = new URL(urlString);// 直接调用 url.openConnection()
        //下载资源 url直接打开不可用时
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");//设置请求方法  请求：get post
        httpURLConnection.setRequestProperty(//网站的network
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
        Scanner scanner = new Scanner(bufferedReader);
        while (scanner.hasNextLine())
            System.out.println(scanner.nextLine());
        scanner.close();
        bufferedReader.close();
    }
}
