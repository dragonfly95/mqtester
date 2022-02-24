import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MQConsumer implements MessageListener, ExceptionListener {

    private String url = "tcp://localhost:61616?wireFormat.maxInteractivityDuration=3000";

    public Session session;
    public Connection conn = null;
    public Destination destination;
    public MessageConsumer consumer = null;

    private String route = "";
    private long receiveTimeout;

    int count = 0;
    int Num = 0;

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

        }
    }
}
