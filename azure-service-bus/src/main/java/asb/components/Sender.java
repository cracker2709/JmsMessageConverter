package asb.components;

import asb.pojo.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
@Log4j2
public class Sender {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendPerson(String destination, Person person) {
        log.info("sending message='{}' to destination='{}'", person,
                destination);
        jmsTemplate.convertAndSend(destination, person);
    }
}