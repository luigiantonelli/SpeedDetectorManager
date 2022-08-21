package it.uniroma1.finesmanagement;



import it.uniroma1.commons.utility.ParamConnection;
import it.uniroma1.finesmanagement.consumer.Consumer;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.jms.JMSException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@AutoConfigurationPackage
@ComponentScan(basePackages = "it.uniroma1.commons")
@EnableJpaRepositories(basePackages = "it.uniroma1.commons")
public class FinesManagementConsumerApplication {

	public static void main(String[] args) throws JMSException {
		consmeMsg(1);
	}

	private static void consmeMsg() {
		Consumer consumer = new Consumer(ParamConnection.BROKER_URL, ParamConnection.SPEED_DETECTION_QUEUE);
		consumer.run();
	}

	private static void consmeMsg(int nbr) {
		ExecutorService executor = Executors.newFixedThreadPool(nbr);
		for (int i = 0; i < nbr; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					consmeMsg();
				}
			});
		}
	}
	
}
