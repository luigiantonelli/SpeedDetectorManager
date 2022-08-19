package com.uniroma1.speeddetectoranalyzer;



import com.uniroma1.speeddetectoranalyzer.consumer.Consumer;
import utility.ParamConnection;

import javax.jms.JMSException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpeedDetectorAnalyzerConsumerApplication {

	public static void main(String[] args) throws JMSException {
		consmeMsg(1);
	}

	private static void consmeMsg() {
		Consumer consumer = new Consumer(ParamConnection.BROKER_URL, ParamConnection.DETECTION_QUEUE);
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
