package com.matariky.utils;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Server 代码模拟
 *
 * @author chenyajun
 */
public class DrawServer {
    public String setServer(int port) {
        String data = "";
        try {
            // 1、使用socket套接字建立 Server
            java.net.ServerSocket ss = new java.net.ServerSocket(port);
            try {
                System.out.println(" Label   Reader  Service 端建立成功~! " + port);
                ss.setSoTimeout(2000);
                // 2、当客户机接入后 ,一直待机等待 Data 输入输出
                java.net.Socket client = ss.accept();
                System.out.println("Client 连接成功！");
                // 3、建立输入输出流
                java.io.InputStream ins = client.getInputStream();
                java.io.OutputStream ous = client.getOutputStream();

                byte[] b = new byte[1024]; // 定义字节数组
                int len = ins.read(b); // 由于 Information 的传输是以二进制的形式 ,所以要以二进制的形式进行 Data 的 Read

                if (len != -1) {
                    data = new String(b, 0, len);
                    System.out.println("Client 传来 Label  Data ：" + data);
                }
                // 定义发送给Client 的输出流
                String putText = " Label  Data 已接收";
                ous.write(putText.getBytes()); // 将输出流 Information 以二进制的形式进行写入

                // Close Server
                ous.close();
                ins.close();
                client.close();
                ss.close();
            } catch (Exception e) {
                ss.close();
                e.printStackTrace();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        DrawServer ds = new DrawServer();
        ds.setServer(9998);
    }
}
