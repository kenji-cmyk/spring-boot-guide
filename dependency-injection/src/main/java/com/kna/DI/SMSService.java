package com.kna.DI;

public class SMSService implements MessageService{

    @Override
    public void send(String message){
        System.out.println("Send from SMS : " + message);
    }


}