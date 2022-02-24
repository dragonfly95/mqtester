import javax.jms.JMSException;
import java.util.Timer;
import java.util.TimerTask;

public class MainTest {

    public static void main(String[] args) {

        String url = "tcp://localhost:61616";

        ActivemqStart activemqStart = new ActivemqStart(url);
        MQConsumer mqc = new MQConsumer(url);

        mqc.run();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                try {
                    activemqStart.run();
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
