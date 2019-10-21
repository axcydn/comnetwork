/**
 * Created with IntelliJ IDEA.
 * User: gxm
 * Date: 2019/10/20
 * Time: 21:56
 * To change this template use File | Settings | File Templates.
 * Description: 1. 基本的字节流写入，
 **/
package com.network.TCPDemo;

import java.io.*;
import java.net.Socket;

public class PictureSend {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",9999);//服务器地址及端口
        DataInputStream ds = new DataInputStream(new FileInputStream(new File("srcFile/src.rar")));//文本读取
        DataOutputStream socketDataOut = new DataOutputStream(socket.getOutputStream());//socket 输出流
        byte[] bufferFile = new byte[1000];
        int totalLen = 0;
        while (ds.read(bufferFile) != -1){//直到读取到文件尾部
            String data = new String(bufferFile);
            socketDataOut.write(bufferFile);
            totalLen += bufferFile.length;
            bufferFile = new byte[1000];
           // System.out.println(data);
        }
        System.out.println("send bytes = " + totalLen);
        socket.shutdownOutput();
        ds.close();
        socket.close();
        socketDataOut.close();
    }
}
