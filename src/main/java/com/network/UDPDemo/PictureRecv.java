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
 *   操作引用类型
 *   1. 需要应用中设置MTU，防止中间分配造成包裹发送失败
 *   2. 使用DatagramSocket指定端口创建接收端
 *       接收数据还原成原类型
 *   3. 阻塞式 接收数据报 receive(DatagramPacket ) 发送数据包 send(DatagramPacket)
 *   4. 分析数据
 *       getData()
 *       getLength()
 *   5.  释放资源，关闭接口
 * */
public class PictureRecv {
    public static void main(String[] args) throws IOException {
        InetSocketAddress localAddr = new InetSocketAddress("localhost", 8888);
        DatagramSocket localSocket = new DatagramSocket(localAddr);//绑定地址
        byte[] dataRecvBuffer = new byte[1000];
        DatagramPacket revPacket;//接收端无需输入地址等信息
        String data = "";
        File file = new File("UDPUpload/dstUDP.rar");
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        } else
            file.createNewFile();
        DataOutputStream fw = new DataOutputStream(new FileOutputStream(file, true));
        int packets = 0;
        while (true) {
            revPacket = new DatagramPacket(dataRecvBuffer, dataRecvBuffer.length);//接收端无需输入地址等信息
            localSocket.receive(revPacket);
            data = new String(revPacket.getData());
            if (data.contains("packet")) {
                System.out.println(data);
                break;
            }

            fw.write(revPacket.getData());
            packets++;
            System.out.println("数据包数量 = " + packets);
            dataRecvBuffer = new byte[1000];
        }
        localSocket.close();
    }
}
