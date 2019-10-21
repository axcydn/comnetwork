/**
 * Created with IntelliJ IDEA.
 * User: gxm
 * Date: 2019/10/20
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 * Description:
 **/
package com.network.UDPDemo;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/*
     操作引用类型
 *   1. 需要应用中设置MTU，防止中间分配造成包裹发送失败
 *   2. 使用DatagramSocket指定端口创建接收端
 *       数据封装成byte[]
 *   3. 阻塞式 接收数据报 receive(DatagramPacket ) 发送数据包 send(DatagramPacket)
 *   4. 分析数据
 *       getData()
 *       getLength()
 *   5.  释放资源，关闭接口
 * */
public class PictureSend {
    public static void main(String[] args) throws IOException, InterruptedException {
        //发送端可以不配置IP地址及端口
        String dstAddr = "localhost";
        int remotePort = 8888;
        InetSocketAddress remoteSocket = new InetSocketAddress(dstAddr, remotePort);
        DatagramSocket localSocket = new DatagramSocket();//此处不配置端口和IP信息
        DatagramPacket sendPacket;
        String filePath = "srcFile/";//文件路径
        String filename = "src.rar";//文件名称
        DataInputStream br = new DataInputStream(new FileInputStream(new File(filePath+filename)));
        byte[] tempByte = new byte[1000];
        int len = 0;
        int packets = 0;
        while ((len = br.read(tempByte)) != -1) {
            sendPacket = new DatagramPacket(tempByte, len, remoteSocket);
            localSocket.send(sendPacket);
           // System.out.println(new String(tempByte));
            Thread.sleep(1);        //强制延时，防止数据包发送过快，导致丢包率过高
            tempByte = new byte[1000];
            packets++;
/*            if (packets == 10)
                br.read(tempByte);//跳过一个包，查看文件反应*/
        }
        String packetNum = "packets = " + packets;
        sendPacket = new DatagramPacket(packetNum.getBytes(), packetNum.getBytes().length, remoteSocket);
        localSocket.send(sendPacket);
        localSocket.close();
        System.out.println("terminal");
    }
}
