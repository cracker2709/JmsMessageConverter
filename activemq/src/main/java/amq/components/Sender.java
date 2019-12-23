package amq.components;

import amq.pojo.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;


@Component
@Log4j2
public class Sender {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private MappingJackson2MessageConverter jacksonJmsMessageConverter;

    public void sendPerson(String destination, Person person) {
        log.info("sending message='{}' to destination='{}'", person,
                destination);
        jmsTemplate.convertAndSend(destination, person);
    }
}