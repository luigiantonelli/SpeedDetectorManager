package it.uniroma1.speedcamera;

import com.google.gson.Gson;
import it.uniroma1.commons.queueObject.Detection;
import it.uniroma1.commons.utility.ParamConnection;
import it.uniroma1.speedcamera.producer.Producer;
import javax.jms.JMSException;
import java.util.Random;

public class SpeedCameraApplication {
	private static final String[] cities = new String[]{"Milan", "Rome", "Milan", "Naples", "Turin", "Palermo", "Genoa", "Bologna", "Florence"};

	public static void main(String[] args) throws JMSException {
		long i = 0;
		while(true) {
			try {
				Thread.sleep(5000);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			Detection detection = new Detection();
			detection.setId(i);
			detection.setSpeedCameraId(Long.parseLong(args[0]));
			detection.setCity(generateRandomCity());
			detection.setSpeedValue(generateRandomSpeed());
			detection.setLicensePlate(generateRandomLicensePlate());

			Gson gson = new Gson();

			String json = gson.toJson(detection);
			sendMsg(json);
			i++;
		}

	}
	
	private static void sendMsg(String message) {
		Producer producter = new Producer(ParamConnection.BROKER_URL, ParamConnection.DETECTION_QUEUE);
		producter.sendMessage(message);
	}

	private static String generateRandomLicensePlate() {
		int alpha1 = 'A' + (int)(Math.random() * ('Z' - 'A'));
		int alpha2 = 'A' + (int)(Math.random() * ('Z' - 'A'));
		int alpha3 = 'A' + (int)(Math.random() * ('Z' - 'A'));
		int digit1 = (int)(Math.random() * 10);
		int digit2 = (int)(Math.random() * 10);
		int digit3 = (int)(Math.random() * 10);
		int digit4 = (int)(Math.random() * 10);

		return "" + (char)(alpha1) + ((char)(alpha2)) +
				((char)(alpha3)) + digit1 + digit2 + digit3 + digit4;
	}

	private static String generateRandomCity() {
		Random rand = new Random();
		return cities[rand.nextInt(cities.length)];
	}

	private static int generateRandomSpeed() {
		Random random = new Random();
		return random.nextInt(200 - 80) + 80;
	}

}
