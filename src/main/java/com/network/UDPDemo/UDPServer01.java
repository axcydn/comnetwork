/**
 * Created with IntelliJ IDEA.
 * User: gxm
 * Date: 2019/10/20
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 * Description:
 **/
package com.network.UDPDemo;

import java.io.IOException;
import java.net.*;

/*
 *   1. 需要应用中设置MTU，防止中间分配造成包裹发送失败
 *   2. 使用DatagramSocket指定端口创建接收端
 *       数据封装成byte[]
 *   3. 阻塞式 接收数据报 receive(DatagramPacket ) 发送数据包 send(DatagramPacket)
 *   4. 分析数据
 *       getData()
 *       getLength()
 *   5.  释放资源，关闭接口
 * */
public class UDPServer01 {
    public static void main(String[] args) throws IOException {
        String addr = "localhost";//服务端也可不配置
        int port = 35114;
        byte[] receiveBuffer = new byte[200];
        InetSocketAddress inetSocketAddress = new InetSocketAddress(addr, port);
        DatagramSocket datagramSocket = new DatagramSocket(inetSocketAddress);
        DatagramPacket datagramPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        datagramSocket.receive(datagramPacket);//阻塞等待

        byte[] data = datagramPacket.getData();
        System.out.println("接收到数据："
                + " IP地址：" + datagramPacket.getAddress()
                + " 端口：" + datagramPacket.getPort()
                + " 内容：" + new String(data));
        datagramSocket.close();
    }
}
