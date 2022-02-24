import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MQConsumer implements MessageListener, ExceptionListener {

    private String url = "";

    public Session session;
    public Connection conn = null;
    public Destination destination;
    public MessageConsumer consumer = null;

    private String route = "";
    private long receiveTimeout;

    int count = 0;
    int Num = 0;

    public MQConsumer() {}
    public MQConsumer(String url) {
        this.url = url;
    }

    public void run() {

        ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(url);
        try {
            conn = cf.createConnection();
            conn.setExceptionListener(this);
            conn.start();

            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic(route);
            consumer = session.createConsumer(destination);

            if (session == null) {
                System.out.println("cf = sesion is null ");
            } else {
                if (receiveTimeout == 0) {
                    Num = 1;
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onException(JMSException e) {

    }

    @Override
    public void onMessage(Message message) {
        if (message == null) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("textMessage = " + textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    protected void Consume(Connection conn, Session session, MessageConsumer consumer) throws JMSException {
        Message message = consumer.receive(5000);
        count++;
        if (message != null) {
            onMessage(message);
        }
    }

    public void Close(Connection conn, Session session, MessageConsumer consumer) throws JMSException {
        conn.close();
        session.close();
        consumer.close();
    }
}
