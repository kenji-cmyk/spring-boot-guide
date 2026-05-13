package com.kna.rabbitmq;


import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage (String message){
        System.out.println("Recive  d : " + message);

    }

    public CountDownLatch getLatch (){
        return latch;
    }

}
