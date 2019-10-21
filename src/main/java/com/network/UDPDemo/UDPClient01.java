/**
 * Created with IntelliJ IDEA.
 * User: gxm
 * Date: 2019/10/20
 * Time: 15:53
 * To change this template use File | Settings | File Templates.
 * Description:
 **/
package com.network.UDPDemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UDPClient01 {
    public static void main(String[] args) throws IOException {
        String addr = "localhost";
        int localPort = 35115;  //本地端口
        int remotePort = 35114; //服务器端口
        InetSocketAddress socketAddressLocal = new InetSocketAddress(addr,localPort);//本地端口
        InetSocketAddress socketAddressRemote = new InetSocketAddress(addr,remotePort);//服务器端口
        DatagramSocket datagramSocket = new DatagramSocket(socketAddressLocal);
        String data = "UDP连接测试";
        byte[] dataBytes = data.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(dataBytes,dataBytes.length,socketAddressRemote);
        datagramSocket.send(datagramPacket);
        //释放资源
        datagramSocket.close();

    }
}
