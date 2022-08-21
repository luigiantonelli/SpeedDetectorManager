package it.uniroma1.finesmanagement.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import it.uniroma1.commons.entity.Fine;
import it.uniroma1.commons.entity.SpeedCamera;
import it.uniroma1.commons.queue.handler.DetectionHandler;
import it.uniroma1.commons.queue.handler.MessageHandler;
import it.uniroma1.commons.queue.object.Detection;
import it.uniroma1.commons.queue.object.DetectionExt;
import it.uniroma1.commons.repository.FineRepository;
import it.uniroma1.commons.repository.SpeedCameraRepository;
import it.uniroma1.commons.utility.ParamConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.net.ssl.SSLException;
import java.util.Optional;

@Component
public class ConsumerSpringFM {
	@Autowired
	@Qualifier("admin_queue")
	private Queue adminQueue;
	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	FineRepository fineRepository;

	@Autowired
	SpeedCameraRepository speedCameraRepository;

	MessageHandler messageHandler = new MessageHandler();
	private static final Logger logger = LoggerFactory.getLogger(ConsumerSpringFM.class);

	@JmsListener(destination = ParamConnection.SPEED_DETECTION_QUEUE)
	public void consumeMessage(String message) {
		logger.info("Message received from activemq queue---"+message);

		Detection detection = (Detection) messageHandler.handleMessage(message, Detection.class);
//		Fine fine =  new Fine();
//		//search SpeedCamera on DB
//		Optional<SpeedCamera> optionalSpeedCamera = speedCameraRepository.findById(detection.getSpeedCameraId());
//		if(optionalSpeedCamera.isEmpty()) {
//			logger.info("SpeedCamera is not present");
//		}
//
//		fine.setSpeedCamera(optionalSpeedCamera.get());
//		fine.setCarPlate(detection.getLicensePlate());
		//finire con la gestione della multa

//		fineRepository.save(fine);
		//Create detectionExt
		DetectionExt detectionExt = new DetectionExt();
		detectionExt.setLicensePlate(detection.getLicensePlate());
		detectionExt.setSpeedValue(detection.getSpeedValue());
		Gson gson = new Gson();

		String json = gson.toJson(detectionExt);
		try {
			jmsTemplate.convertAndSend(adminQueue, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
