package amq.web;

import amq.components.Receiver;
import amq.components.Sender;
import amq.components.route.PersonRouteBuilder;
import amq.pojo.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Log4j2
@RequestMapping("/jms")
public class JmsController {


    @Value("${queue.name}")
    private String converterQueue;

    @Autowired
    private Sender sender;

    @Autowired
    private PersonRouteBuilder personRouteBuilder;

    @Autowired
    private Receiver receiver;


    @GetMapping("/sendPersonAndConsume")
    @ResponseBody
    public Person sendPerson() {
        Person person = new Person();
        person.setName("Classic AMQ");
        person.setCash(300);
        Stream<String> hobbies = Stream.of("foo", "bar", "plop");
        person.setHobbies(hobbies.collect(Collectors.toList()));
        sender.sendPerson(converterQueue, person);
        return person;
    }


}
