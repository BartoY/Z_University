package com.sugonYH.Chat;

public class ChatApp {

    public static void main(String[] args) {
        ChatClient sChatA = new ChatClient("客户端A");
        ChatClient sChatB = new ChatClient("客户端B");

        sChatA.go();
        sChatB.go();
    }
}
