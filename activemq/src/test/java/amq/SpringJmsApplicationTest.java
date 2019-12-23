package amq;

import amq.components.Receiver;
import amq.components.Sender;
import amq.pojo.Person;
import amq.utils.PersonTestBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringJmsApplicationTest.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = { "amq", "amq.components", "amq.config"})
@DirtiesContext
public class SpringJmsApplicationTest {

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @Value("${queue.name}")
    private String queue;



    @Test
    public void testReceive() throws Exception {
        Person person = PersonTestBuilder.buildPerson();

        sender.sendPerson(queue, person);

        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }



}
