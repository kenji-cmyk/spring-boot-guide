package com.kna.DI;

public class Client  implements InterfaceInjection{

    private MessageService messageService;
//
//    public Client (MessageService messageServiceParam){
//        this.messageService = messageServiceParam;
//    }

    public void setterInjection (MessageService messageServiceParam){
        this.messageService = messageServiceParam;
    }

    public void processMessage (String message){
        messageService.send(message);
    }

    @Override
    public void setService(MessageService messageService){
        this.messageService = messageService;
    }
}
