package client1;

import activemq.ActivemqStart;
import activemq.MQConsumer;

import javax.jms.JMSException;
import java.util.Timer;
import java.util.TimerTask;

public class Runner1 {
    public static void main(String[] args) {

        String url = "tcp://localhost:61616";

        MQConsumer mqc = new MQConsumer(url);

        mqc.run();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
            try {
                if (mqc.Num == 1) {
                    mqc.Consume(mqc.conn, mqc.session, mqc.consumer);
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }

            }
        };

        timer.scheduleAtFixedRate(timerTask, 1000, 5000);

    }
}
