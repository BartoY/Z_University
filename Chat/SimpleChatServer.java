package com.sugonYH.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class SimpleChatServer {
    ArrayList clientOutPutStream = new ArrayList();

    public class ClientHandler implements Runnable
    {
        BufferedReader reader;
        Socket socket;

        public ClientHandler(Socket clientSocket){
            try {
                //Establish an input stream with the socket
                this.socket = clientSocket;

                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(inputStreamReader);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        public void run(){
            String message = null;
            try{
                //Continuously read data from the socket
                while((message = reader.readLine()) != null){
                    System.out.println("read" + message);
                    //Send data to each client
                    tellEveryone(message);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

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
                PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
                clientOutPutStream.add(printWriter);
                // Start a thread
                Thread thread = new Thread(new ClientHandler(clientSocket));
                thread.start();
                System.out.println("服务器得到一个连接");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void tellEveryone(String message){
        Iterator it = clientOutPutStream.iterator();
        while(it.hasNext()){
            try{
                PrintWriter writer = (PrintWriter)it.next();
                writer.println(message);
                writer.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
