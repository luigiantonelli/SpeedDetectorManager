package it.uniroma1.speeddetectoranalyzer.consumer;

import it.uniroma1.commons.queue.enums.RoadType;
import it.uniroma1.commons.queue.handler.MessageHandler;
import it.uniroma1.commons.queue.object.Detection;
import it.uniroma1.commons.utility.ParamConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 
 * @author Jihed KAOUECH
 *
 */
public class Consumer {

	private String queue;
	private String brokerUrl;

	private ActiveMQConnectionFactory connectionFactory;

	/**
	 * Consumer constructor.
	 * 
	 * @param brokerUrl : JMS broker URL
	 * @param queue : JMS queue
	 */
	public Consumer(String brokerUrl, String queue) {
		this.queue = queue;
		this.brokerUrl = brokerUrl;
		// Create a ConnectionFactory
		connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
	}
	
	/**
	 * Read JMS Message
	 */
	public void run() {

		try {
			System.out.println(Thread.currentThread().getName() + " | Get Connection from broker << " + brokerUrl + " >>") ;
			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();
			String clientID = connection.getClientID();
			System.out.println(Thread.currentThread().getName() + " | Connection Created with ID << " + clientID + " >>") ;

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue(queue);
			// Create a MessageConsumer from the Session to the Topic or Queue
			MessageConsumer consumer = session.createConsumer(destination);

			while (true) {
				// Wait for a message
				Message message = consumer.receive();
				
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					String text = textMessage.getText();
					System.out.println(Thread.currentThread().getName() + " | Receive JMS | text = " + text );
					MessageHandler messageHandler = new MessageHandler();
					//gestione della rilevazione ed inoltra alla coda opportuna
					Detection detection = (Detection) messageHandler.handleMessage(text, Detection.class);
					if(detection.getRoadType() == RoadType.HIGHWAY && detection.getSpeedValue() > RoadType.HIGHWAY.getThreshold() ||
						detection.getRoadType() == RoadType.THROUGHWAY && detection.getSpeedValue() > RoadType.THROUGHWAY.getThreshold() ||
							detection.getRoadType() == RoadType.CITY_ROAD && detection.getSpeedValue() > RoadType.CITY_ROAD.getThreshold()) {
						messageHandler.sendObject(detection, ParamConnection.SPEED_DETECTION_QUEUE);
						System.out.println("Sent message to SpeedDetection");
					}

					//mando a statistics
					messageHandler.sendObject(detection, ParamConnection.STATISTIC_QUEUE);
					System.out.println("Sent message to Statistic");
				} else {
					System.out.println(Thread.currentThread().getName() + " | message: " + message);
				}
			}
		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}
	}
}
