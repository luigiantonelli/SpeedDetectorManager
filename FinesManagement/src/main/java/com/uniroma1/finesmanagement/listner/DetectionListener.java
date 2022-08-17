//package com.uniroma1.finesmanagement.listner;
//
//import com.uniroma1.finesmanagement.model.Detection;
//
//import javax.jms.*;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import java.util.Observable;
//import java.util.Observer;
//import java.util.Properties;
//
//public class DetectionListener extends Observable implements MessageListener {
//
//    private TopicConnection topicConnection;
//    private TopicSession topicSession = null;
//    private Destination destination = null;
//    private MessageProducer producer = null;
//
//
//	public DetectionListener(Observer[] observers) {
//		for (Observer observer : observers) {
//			super.addObserver(observer);
//		}
//		Context jndiContext = null;
//		ConnectionFactory topicConnectionFactory = null;
//
//		String destinationName = "Topics/Detections";
//
//		try {
//			Properties props = new Properties();
//
//			props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
//			props.setProperty(Context.PROVIDER_URL,"tcp://localhost:61616");
//			jndiContext = new InitialContext(props);
//
//			topicConnectionFactory = (ConnectionFactory)jndiContext.lookup("ConnectionFactory");
//			destination = (Destination)jndiContext.lookup(destinationName);
//			topicConnection = (TopicConnection)topicConnectionFactory.createConnection();
//			topicSession = (TopicSession)topicConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//
//			TopicSubscriber topicSubscriber = topicSession.createSubscriber((Topic)destination);
//
//			topicSubscriber.setMessageListener(this);
//		} catch (JMSException err) {
//			err.printStackTrace();
//		} catch (NamingException err) {
//			err.printStackTrace();
//                }
//	}
//
//	/**
//	 * Chiude la ricezione dei messaggi sulla topic quotazioni
//	 */
//	public void stop() {
//		try {
//			topicConnection.stop();
//		} catch (JMSException err) {
//			err.printStackTrace();
//		}
//	}
//
//	/**
//	 * Apre la ricezione dei messaggi sulla topic quotazioni
//	 */
//	public void start() {
//		try {
//			topicConnection.start();
//		} catch (JMSException err) {
//			err.printStackTrace();
//		}
//	}
//
//	public void onMessage(Message mex) {
//		try {
//			int speedValue = mex.getIntProperty("speedValue");
//			String licensePlate = mex.getStringProperty("licensePlate");
//
//			Detection detection = new Detection();
//
//			detection.setSpeedValue(speedValue);
//			detection.setLicensePlate(licensePlate);
//
//			super.setChanged();	// rende attivo il cambiamento di stato
//			super.notifyObservers(detection);
//		} catch (JMSException err) {
//			err.printStackTrace();
//		}
//	}
//
//}
