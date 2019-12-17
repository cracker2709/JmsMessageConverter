package jms;

import jms.components.Receiver;
import jms.components.Sender;
import jms.pojo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringJmsApplicationTest.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = { "jms", "jms.components"})
@DirtiesContext
public class SpringJmsApplicationTest {

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @Test
    public void testReceive() throws Exception {
        Person person = new Person();
        person.setName("a guy");
        person.setAge(30);
        Stream<String> hobbies = Stream.of("foo", "bar", "plop");
        person.setHobbies(hobbies.collect(Collectors.toList()));

        sender.send("converter.q", person);

        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }
}
