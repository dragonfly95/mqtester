package publisher;

import activemq.ActivemqStart;
import activemq.MQConsumer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;
import java.util.Timer;
import java.util.TimerTask;

public class PubRunner {

    public static void main(String[] args) throws JMSException {

        String url = "tcp://localhost:61616";
        String route = "exampleTopic";

        String virtualRoute = "Consumer." + route +".VirtualTopic." + route;

        int count = 0;

        Connection conn = null;
        Destination destination;
        MessageConsumer consumer = null;

        ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(url);
        conn = cf.createConnection();
        conn.start();

        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        destination = session.createTopic(route);
        destination = new ActiveMQTopic(virtualRoute);

        MessageProducer mq = null;
        mq = session.createProducer(destination);

        mq.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        TextMessage message = session.createTextMessage("data");
        mq.send(message);
        System.out.println("SendData = " + message);

        conn.close();
        session.close();
    }
}
