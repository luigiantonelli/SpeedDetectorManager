package javastatistics;


/*import it.uniroma1.commons.queue.handler.DetectionHandler;
import it.uniroma1.commons.queue.handler.MessageHandler;
import it.uniroma1.commons.queue.object.Detection;*/
/*import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;*/

import com.google.gson.Gson;
import it.uniroma1.commons.queue.object.DetectionExt;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.FileWriter;
import java.io.IOException;


/**
 * @author Jihed KAOUECH
 */
public class Consumer {
    static String del = "\n%%%\n";
    private String queue;
    private String brokerUrl;

    private ActiveMQConnectionFactory connectionFactory;


    public Consumer(String brokerUrl, String queue) {
        this.queue = queue;
        this.brokerUrl = brokerUrl;
        // Create a ConnectionFactory
        connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
    }


    public void run() {

        try {
            System.out.println(Thread.currentThread().getName() + " | Get Connection from broker << " + brokerUrl + " >>");
            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();
            String clientID = connection.getClientID();
            System.out.println(Thread.currentThread().getName() + " | Connection Created with ID << " + clientID + " >>");

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
                    System.out.println(Thread.currentThread().getName() + " | Receive JMS | text = " + text);

                    Gson gson = new Gson();
                    DetectionExt d = gson.fromJson(text, DetectionExt.class);
                    FileWriter file = null;
                    try {
                        file = new FileWriter("file.txt", true);
                        file.write(d.toString() + "\n");
                        file.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    {
                        try {
                            file.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
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

