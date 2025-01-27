package com.matariky.utils;

import java.io.IOException;

public class DrawServer {
    public String setServer(int port) {
        String data = "";
        try {
            // 1. Use socket to establish the Server
            java.net.ServerSocket ss = new java.net.ServerSocket(port);
            try {
                System.out.println("Label Reader Service established successfully~! " + port);
                ss.setSoTimeout(2000);
                // 2. When a client connects, keep waiting for data input and output
                java.net.Socket client = ss.accept();
                System.out.println("Client connected successfully!");
                // 3. Establish input and output streams
                java.io.InputStream ins = client.getInputStream();
                java.io.OutputStream ous = client.getOutputStream();

                byte[] b = new byte[1024]; // Define byte array
                int len = ins.read(b); // Since the information is transmitted in binary form, read the data in binary form

                if (len != -1) {
                    data = new String(b, 0, len);
                    System.out.println("Label data received from client: " + data);
                }
                // Define the output stream to be sent to the client
                String putText = "Label data received";
                ous.write(putText.getBytes()); // Write the output stream information in binary form

                // Close the Server
                ous.close();
                ins.close();
                client.close();
                ss.close();
            } catch (Exception e) {
                ss.close();
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}

