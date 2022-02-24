package publisher;

import activemq.ActivemqStart;
import activemq.MQConsumer;

import javax.jms.JMSException;
import java.util.Timer;
import java.util.TimerTask;

public class PubRunner {

    public static void main(String[] args) throws JMSException {

        String url = "tcp://localhost:61616";

        ActivemqStart activemqStart = new ActivemqStart(url);
        activemqStart.run("hello world");
    }
}
