package com.sugonYH.Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatClient {
    //private JTextArea incoming;
    private JTextArea outgoing;        //input box
    private BufferedReader reader;      //Cache the input character stream
    private PrintWriter writer;         //Output stream
    private Socket socket;
    private String clientName;

    //private TextField outgoing = new TextField(25);

    JPanel jPanel = new JPanel();
    JPanel jPanel2 = new JPanel();
    JScrollPane jScrollPane = new JScrollPane(jPanel);

    /*
    public static void main(String[] args) {
        new SimpleChatClientA().go();
    }
*/

    public ChatClient(String clientName){
        this.clientName = clientName;

    }

    public void go(){
        JFrame jFrame = new JFrame(clientName);


        jPanel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jFrame.add(jScrollPane,BorderLayout.CENTER);
        jPanel.setLayout(new GridLayout(40,1));


        //Create an input field
        outgoing = new JTextArea(6,48);
        Font font = new Font("Dialog", Font.PLAIN, 18);
        outgoing.setFont(font);

        //Create a send button and create a listener
        JButton sendButton = new JButton("发送");
        sendButton.setPreferredSize(new Dimension(80,40));
        sendButton.addActionListener(new sendButtonListener());

        //Place the above component in the JPanel container

        jPanel2.add(outgoing);
        jPanel2.setBounds(0,450,680,200);
        jPanel2.add(sendButton);
        jFrame.add(jPanel2);

        //Establish a connection with the server
        setUpNetworking();

        //Start a thread and execute the run method
        Thread thread = new Thread(new IncomingReader());
        thread.start();

        //Place the JPanel container in the JFrame panel
        jFrame.getContentPane().add(BorderLayout.CENTER,jPanel);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setBounds(200,100,700,700);
        jFrame.setVisible(true);

    }

    /**
     * Establish a socket connection with the server, and establish input and output streams on the basis of the socket
     */
    private void setUpNetworking(){
        try {
            //Establish a socket connection with the server
            socket = new Socket("127.0.0.1",5000);

            //Create a character input buffer stream with the socket
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader((inputStreamReader));

            //Establish a character output stream with the socket
            writer = new PrintWriter(socket.getOutputStream());
            //incoming.append(clientName+"成功连接服务器..."+"\n");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * The listener
     */

    private class sendButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //When the send button is clicked, an event is triggered to get the text in the input box and output it to the server
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
            String now = sdf.format(date);

            String text = outgoing.getText();

            String message = now+"\n"+clientName+"："+outgoing.getText();
            FileRecord.writeFile(clientName,message);
            writer.println(now+"\n"+clientName+"："+outgoing.getText().trim());
            //FileRecord.writeFile(clientName,writer);
            writer.flush();

            JLabel tempLabel = new JLabel(   text + " : 我 "+" "  +"\n");
            tempLabel.setHorizontalAlignment(JLabel.RIGHT);
            jPanel.add(tempLabel);
            tempLabel.revalidate();


            //Clear the input box text
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }




    /**
     * The task of this thread is to continuously read information from the server and load it into a text field
     */
    class IncomingReader implements Runnable{
        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine())!=null){
                    System.out.println("read"+message);
                    FileRecord.writeFile(clientName,message);
                    //incoming.append(message+"\n");
                    JLabel tempLabel = new JLabel(message);
                    jPanel.add(tempLabel);
                    tempLabel.revalidate();

                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        System.out.println("din关闭失败");
                        e.printStackTrace();
                    }
                }

            }
        }
    }



}
