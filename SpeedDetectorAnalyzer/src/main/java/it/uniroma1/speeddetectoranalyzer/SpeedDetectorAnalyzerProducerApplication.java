package it.uniroma1.speeddetectoranalyzer; /**
 * 
 */



import it.uniroma1.commons.queue.producer.Producer;
import it.uniroma1.commons.utility.ParamConnection;

import javax.jms.JMSException;

/**
 * @author Jihed KAOUECH
 *
 */
public class SpeedDetectorAnalyzerProducerApplication {

	public static void main(String[] args) throws JMSException {
		sendMsg();
	}
	
	private static void sendMsg() {
		Producer producter = new Producer(ParamConnection.BROKER_URL, ParamConnection.DETECTION_QUEUE);
		producter.sendMessage("hello world");
	}

}
