package it.uniroma1.speeddetectoranalyzer;

import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJms
public class SpeedDetectorAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpeedDetectorAnalyzerApplication.class, args);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public BrokerService broker() throws Exception {
        final BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:61616");
        broker.addConnector("vm://localhost");
        broker.setPersistent(false);
        return broker;
    }
}
