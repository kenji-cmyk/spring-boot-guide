package com.kna.DI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DependencyInjectionApplication {


    public static void main(String[] args) {

//        Constructor Injection
//        MessageService messageService = new EmailService();
//        Client client = new Client(messageService);
//        client.processMessage("Hello Wolrd");


//        Setter Injection
//        Client client = new Client();
//        MessageService messageService = new SMSService();
//        client.setterInjection(messageService);
//        client.processMessage("Hello World");

        MessageService messageService = new SMSService();
        Client client = new Client();
        client.setService(messageService);
        client.processMessage("Hello em ten gi ");

    }

}
