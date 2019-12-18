package asb.components;


import asb.pojo.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Log4j2
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @JmsListener(destination = "${queue.boot}")
    public void receive(Person person) {
        log.info("received person='{}'", person);
        latch.countDown();
    }
}

