package amq.components.route;

import amq.components.processor.PersonProcessor;
import lombok.Data;
import org.apache.camel.LoggingLevel;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class PersonRouteBuilder extends SpringRouteBuilder {

    @Value("${camel.queue.incoming.name}")
    private String queueIn;

    @Value("${camel.queue.outcoming.name}")
    private String queueOut;

    @Autowired
    private PersonProcessor personProcessor;


    @Override
    public void configure() throws Exception {
        log.info("Configuring route");

        from(queueIn)
                .log(LoggingLevel.INFO, log, "New message received")
                .routeId("MyRoute")
                .process(personProcessor)
                .to(queueOut)
                .log(LoggingLevel.INFO, log, "Message sent to the other queue")
                .end();

    }
}