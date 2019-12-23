package amq.components.processor;

import amq.config.JmsConfig;
import amq.pojo.Person;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;



@Component
public class PersonProcessor implements Processor {

    @Autowired
    private JmsConfig jmsConfig;


    @Override
    public void process(Exchange exchange) throws Exception {
        final String body = exchange.getIn().getBody(String.class);
        Person transformed = jmsConfig.objectMapper().readValue(body, Person.class);
        transformed.getHobbies().add("Camel process updated");
        exchange.getOut().setBody(jmsConfig.objectMapper().writeValueAsString(transformed));
        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.ACCEPTED);
    }
}
