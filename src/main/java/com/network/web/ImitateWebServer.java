/**
 * Created with IntelliJ IDEA.
 * User: gxm
 * Date: 2019/10/21
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 * Description:
 **/
package com.network.web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/*
 *   ① 创建服务器
 *   ② 获取到accept请求的客户端对象
 *   ③ 使用网络输入流中的read读取客户端的请求信息
 *   ④ 使用字符缓存输入流将请求信息的第一行读取处理
 *   ⑤ 利用字符串处理，仅取请求信息的第一个号中间部分，并去除/
 *       web/jdk-12.0.2_doc-all/docs/index.html
 *   ⑥ 创建本地字节输入流，构造方法中绑定要读取的html信息
 *   ⑦ 使用socket中的方法，获取网络字节输出流
 *   ⑧ 写入http协议响应，固定写发
 *       os.write("HTTP/1.1 200 OK\r\n".getBytes());
 *       os.write("Content-Type:text/html\r\n".getBytes());
 *       //写入空行，否则浏览器不解析
 *       os.write("\r\n".getBytes());
 *       上述读写过程将html文件回写到客户端
 *           浏览器访问 http://127.0.0.1:8888/web/jdk-12.0.2_doc-all/docs/index.html
 *        并不会显示图片，原因：
 *               浏览器解析服务器回写的html页面，如果页面中有图片，
 *               浏览器将单独开启线程读取服务器的图片，故需要将服务器一直处于监听状态
 *               客户端请求一次，服务器则回复一次
 *       解决方法：
 *           服务器中开启线程不断的处理新的请求即可。
 *   ⑨ 回收资源，socket ,流IO
 * */
public class ImitateWebServer implements Runnable {
    private Socket socket;
    ImitateWebServer(Socket socket){
        this.socket = socket;
    }
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
/*        Socket socket =serverSocket.accept();
        InputStream is = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1)
        {
            System.out.println(new String(buffer));
        }*/
        String clientName = "client";
        int counter = 0;
        while (true) {
            Socket socket = serverSocket.accept();//阻塞等待客户端访问
            new Thread(new ImitateWebServer(socket), clientName + counter ++).start();
        }
    }

    @Override
    public void run() {
        BufferedReader bfr;
        OutputStream os;
        FileInputStream fis;
        try {
            bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));//获取字符换从输入流
            //GET /web/jdk-12.0.2_doc-all/docs/index.html HTTP/1.1
            String[] temp = bfr.readLine().split(" ");
            //  web/jdk-12.0.2_doc-all/docs/index.html
            String line1 = temp[1].substring(1);
            System.out.println(new Date() + Thread.currentThread().getName() + ": " + line1);
            //本地输入流读取文件
            fis = new FileInputStream(new File(line1));
            //回写到请求客户端
            os = socket.getOutputStream();

            //写入http请求
            os.write("HTTP/1.1 200 OK\r\n".getBytes());
            os.write("Content-Type:text/html\r\n".getBytes());
            //写入空行，否则浏览器不解析
            os.write("\r\n".getBytes());
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            //资源回收
            fis.close();
            socket.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
