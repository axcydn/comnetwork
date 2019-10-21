/**
 * Created with IntelliJ IDEA.
 * User: gxm
 * Date: 2019/10/20
 * Time: 21:10
 * To change this template use File | Settings | File Templates.
 * Description:
 **/
package com.network.TCPDemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/*
*   1. 创建Socket连接服务器，通过地址寻找服务器
*   2. 调用Socket的getInputStream和getOutputStream方式获取和服务端相连的IO流
*   3. 发送或接收数据
*   4. 释放资源
* */
public class TCPClient01 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8888);//服务器地址和端口
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        while (true){
            byte[] buffer = new byte[1000];
            System.out.printf("请输入：");
            String data = new java.util.Scanner(System.in).nextLine();
            outputStream.write(data.getBytes());
            int read = inputStream.read(buffer);//阻塞等待
            System.out.println(new String(buffer));
            if (read == -1 || new String(buffer).equals("close"))
                {
                    socket.shutdownOutput();//发送结束，关闭掉输出及输入流
                    socket.shutdownInput();
                    break;
                }
        }
        outputStream.close();
        socket.close();
    }
}
