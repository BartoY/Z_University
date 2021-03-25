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
    private JTextArea incoming;
    private JTextField outgoing;        //input box
    private BufferedReader reader;      //Cache the input character stream
    private PrintWriter writer;         //Output stream
    private Socket socket;
    private String clientName;
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
        JPanel jPanel = new JPanel();

        //Create a text field
        incoming = new JTextArea(15,30);
        incoming.setLineWrap(true);     //Sets whether to wrap a line if it becomes too long
        incoming.setWrapStyleWord(true);//Sets whether to move a long word to the next line if it becomes too long
        incoming.setEditable(false);

        //Create a scroll panel and place the text field in it
        JScrollPane jScrollPane = new JScrollPane(incoming);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //Create an input field
        outgoing = new JTextField(25);

        //Create a send button and create a listener
        JButton sendButton = new JButton("发送");
        sendButton.addActionListener(new sendButtonListener());

        //Place the above component in the JPanel container
        jPanel.add(jScrollPane);
        jPanel.add(outgoing);
        jPanel.add(sendButton);

        //Establish a connection with the server
        setUpNetworking();

        //Start a thread and execute the run method
        Thread thread = new Thread(new IncomingReader());
        thread.start();

        //Place the JPanel container in the JFrame panel
        jFrame.getContentPane().add(BorderLayout.CENTER,jPanel);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setBounds(100,100,380,330);
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
            incoming.append(clientName+"成功连接服务器..."+"\n");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * The listener
     */

    class sendButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //When the send button is clicked, an event is triggered to get the text in the input box and output it to the server
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
            String now = sdf.format(date);

            writer.println(now+"\n"+clientName+"："+outgoing.getText());
            //FileRecord.writeFile(clientName,writer);
            writer.flush();

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
                    incoming.append(message+"\n");

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



}
