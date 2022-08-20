package it.uniroma1.speeddetectoranalyzer.producer;//package com.uniroma1.speeddetectoranalyzer.producer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DetectionProducer {
//
//    @Autowired
//    JmsTemplate jmsTemplate;
//
//    @Scheduled(cron = "* * * * * *")
//    public void produce() {
//        System.out.println("Sending message");
//        jmsTemplate.send("jokes", c -> c.createTextMessage("Hello"));
//    }
//}
