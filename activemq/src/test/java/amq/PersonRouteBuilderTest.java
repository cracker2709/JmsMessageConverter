package amq;


import amq.config.JmsConfig;
import amq.pojo.Person;
import amq.utils.PersonTestBuilder;
import lombok.extern.log4j.Log4j2;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = PersonRouteBuilderTest.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = { "amq", "amq.components", "amq.config"})
@DirtiesContext
@Log4j2
public class PersonRouteBuilderTest  {
    @Value("${queue.incoming.name}")
    private String queueIn;

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private JmsConfig jmsConfig;

    @EndpointInject(uri = "mock:out-queue.q")
    private MockEndpoint mockCamel;

    @Test
    public void test() throws InterruptedException {
        Person person = PersonTestBuilder.buildPerson();

        mockCamel.expectedMessageCount(1);
        producerTemplate.sendBody(mockCamel, person);

        List<Exchange> out = mockCamel.getReceivedExchanges();
        Assert.assertEquals(1, out.size());
        log.info("Received message : {}", out.get(0).getMessage().getBody());

        mockCamel.assertIsSatisfied();
    }

}