//package com.uniroma1.finesmanagement.receiver;
//
//import entity.Detection;
//import com.uniroma1.finesmanagement.model.Email;
//import com.uniroma1.finesmanagement.repository.DetectionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DetectionReceiver {
//
//    @Autowired
//    private DetectionRepository detectionRepository;
//
//    @JmsListener(destination = "DetectionQueue", containerFactory = "myFactory")
//    public void receiveMessage(Detection detection) {
//        System.out.println("Received <" + detection + ">");
//        detectionRepository.save(detection);
//    }
//
//}
