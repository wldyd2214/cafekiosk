package sample.cafekiosk.spring.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MailClient {

    @Async
    public void send(String input) {
        try {
            Thread.sleep(3000);
            System.out.println("MailClient Send... => " + input);
            System.out.println("Thread Name... => " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
