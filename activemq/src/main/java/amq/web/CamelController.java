package amq.web;

import amq.components.Sender;
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
@RequestMapping("/camel")
public class CamelController {
    @Value("${queue.incoming.name}")
    private String queueIn;

    @Autowired
    private Sender sender;

    @GetMapping("/sendPersonInQueue")
    @ResponseBody
    public Person sendPersonInQueue() {
        Person person = new Person();
        person.setName("CAMEL AMQ Route");
        person.setCash(1100);
        Stream<String> hobbies = Stream.of("doo", "test");
        person.setHobbies(hobbies.collect(Collectors.toList()));
        person.setBirthDate("10/10/1990");
        sender.sendPerson(queueIn, person);
        return person;
    }


}
