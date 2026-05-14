package com.kna.DI;

public class EmailService implements MessageService{

    @Override
    public void send(String message){
        System.out.println("Send from Email : " + message);
    }
}
