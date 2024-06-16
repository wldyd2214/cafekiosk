package sample.cafekiosk.spring.async;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AsyncTest {

    @Autowired
    MailClient mailClient;

    @Test
    void asyncTest() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            mailClient.send(String.valueOf(i));
        }
        Thread.sleep(10000);
    }
}
