import java.util.Timer;
import java.util.TimerTask;

public class MainTest {

    public static void main(String[] args) {

        ActivemqStart activemqStart = new ActivemqStart();
        MQConsumer mqc = new MQConsumer();

        mqc.run();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                activemqStart.run();
                if (mqc.Num == 1) {
                    mqc.Consume(mqc.conn, mqc.session, mqc.consumer);
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask, 1000, 5000);
    }
}
