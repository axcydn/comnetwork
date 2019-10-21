/**
 * Created with IntelliJ IDEA.
 * User: gxm
 * Date: 2019/10/20
 * Time: 21:09
 * To change this template use File | Settings | File Templates.
 * Description:
 **/
package com.network.TCPDemo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 *   1. 创建ServerSocket  需要指定端口号
 *   2. 调用accept方法接收一个客户端的请求，得到一个socket
 *   3. 调用输入输出流
 *   4. 释放资源
 * */
public class TCPServer01{
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8888);//服务器端口
        InputStream inputStream = null;
        DataInputStream dataInputStream = null;
        Socket accept = serverSocket.accept();
        OutputStream outputStream = accept.getOutputStream();
        System.out.println("得到 " + accept.toString() + "的连接");
        while (true) {
            inputStream = accept.getInputStream();
            dataInputStream = new DataInputStream(inputStream);
            byte[] buffer = new byte[1000];
            dataInputStream.read(buffer);//一直读取
            System.out.println(new String(buffer));
            System.out.printf("请输入");
            String text = new java.util.Scanner(System.in).nextLine();
            outputStream.write(text.getBytes());
            if (new String(buffer).equals("close"))
                {
                    break;
                }
        }
        serverSocket.close();
        inputStream.close();
        dataInputStream.close();
    }

}
