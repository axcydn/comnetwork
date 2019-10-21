/**
 * Created with IntelliJ IDEA.
 * User: gxm
 * Date: 2019/10/20
 * Time: 21:56
 * To change this template use File | Settings | File Templates.
 * Description: 基本的字节流读取，然后保持不断的读写
 **/
package com.network.TCPDemo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PictureRecv implements Runnable {
    private static ServerSocket serverSocket;
    private Socket accept;
    PictureRecv(Socket accept) {
        this.accept = accept;
    }

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(9999);
        String client = "client";
        int counter = 0;
        while (true) {
            Socket accept = serverSocket.accept();
            new Thread(new PictureRecv(accept), client + counter++).start();
        }
    }
    @Override
    public void run() {
        try {

            System.out.println("new connection from" + accept);
            DataInputStream bi = new DataInputStream(accept.getInputStream());//字节流读取
            File file = new File("TCPUpload/" + Thread.currentThread().getName() + "dstTCP.rar");
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            } else
                file.createNewFile();
            FileOutputStream fs = new FileOutputStream(file);
            byte[] buffer = new byte[1000];
            int totalLen = 0;
            while (bi.read(buffer) != -1) {//读取客户端输入
                /*
                 *   使用多线程进行文件传输，每个文件传输开启线程，结束则关闭连接
                 * */

                String data = new String(buffer);
                fs.write(buffer);
                totalLen += buffer.length;
                buffer = new byte[1000];
                System.out.println(Thread.currentThread().getName() +  "recv bytes = " + totalLen / 1000);
            }
            fs.close();
            //serverSocket.close();
            bi.close();
            Thread.currentThread();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
