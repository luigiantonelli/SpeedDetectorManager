package it.uniroma1.finesmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
public class FinesManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinesManagementApplication.class, args);

    }
}
