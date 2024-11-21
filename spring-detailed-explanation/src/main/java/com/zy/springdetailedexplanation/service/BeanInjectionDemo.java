package com.zy.springdetailedexplanation.service;

import org.springframework.stereotype.Service;

@Service
public class BeanInjectionDemo {

    private MessageService messageService;

    public BeanInjectionDemo() {
    }

    public BeanInjectionDemo(MessageService messageService) {
        this.messageService = messageService;
    }

    public String sayHello() {
        return messageService.sayHello();
    }


}
