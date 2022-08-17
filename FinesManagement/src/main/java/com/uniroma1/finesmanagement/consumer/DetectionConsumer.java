package com.uniroma1.finesmanagement.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

//@Component
//public class DetectionConsumer {
//
//    @JmsListener(destination = "jokes")
//    public void consume(Message message) {
//        System.out.println("message: " + message);
//    }
//
//}