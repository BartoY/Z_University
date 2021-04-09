package com.sugonYH.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClientHandler implements Runnable {
    public static List<ClientHandler> clientOutPutStream = new ArrayList();
    private BufferedReader reader;
    private Socket socket;

    public ClientHandler(Socket clientSocket){
        try {
            //Establish an input stream with the socket
            this.socket = clientSocket;

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(inputStreamReader);
        } catch (IOException e){
            e.printStackTrace();
        }/*finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("reader关闭失败");
                    e.printStackTrace();
                }
            }
        }*/
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
        }finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("socket关闭失败");
                    e.printStackTrace();
                }
            }
        }
    }

    public void tellEveryone(String message) throws IOException{
        for (ClientHandler c : clientOutPutStream) {
            if (c != this) {
                PrintWriter printWriter = new PrintWriter(c.socket.getOutputStream());
                printWriter.println(message);
                printWriter.flush();
            }
        }

        /*
        while(it.hasNext()){

            try{
                PrintWriter writer = (PrintWriter)it.next();
                writer.println(message);
                writer.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }*/
    }
}
