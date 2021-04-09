package com.sugonYH.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import static com.sugonYH.Chat.ClientHandler.clientOutPutStream;

public class SimpleChatServer {


    public static void main(String[] args) {
        new SimpleChatServer().go();
    }


    public void go(){
        try{
            //The server application creates ServerSocket for a specific port number
            ServerSocket serverSocket = new ServerSocket(5000);
            while (true){
                // The server creates a socket to communicate with the client
                Socket clientSocket = serverSocket.accept();

                // Create an output stream with the socket
                //PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
                //clientOutPutStream.add(printWriter);

                // Start a thread
                ClientHandler handler = new ClientHandler(clientSocket);
                clientOutPutStream.add(handler);
                Thread thread = new Thread(handler);
                thread.start();
                System.out.println("服务器得到一个连接");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}
