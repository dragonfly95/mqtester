import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ActivemqStart {

    String url = "";
    String route = "";
    public int count = 0;

    public Connection conn = null;
    public Destination destination;
    public MessageConsumer consumer = null;

    public ActivemqStart() {}
    public ActivemqStart(String url) {
        this.url = url;
    }

    public void run() throws JMSException {

        ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(url);
        conn = cf.createConnection();
        conn.start();

        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createTopic(route);

        MessageProducer mq = null;
        mq = session.createProducer(destination);

        mq.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        Send(session, mq);

        count++;
        System.out.println("mq = " + count);
    }

    private void Send(Session session, MessageProducer mq) throws JMSException {

        for (int i = 0; i < 1; i++) {
            String text = "Data";
            TextMessage message = session.createTextMessage(text);
            mq.send(message);
            System.out.println("SendData = " + message);
        }
    }


}
