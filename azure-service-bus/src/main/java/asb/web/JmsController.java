package asb.web;

import asb.components.Receiver;
import asb.components.Sender;
import asb.pojo.Person;
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
    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @Value("${queue.boot}")
    private String gapQueue;


    @GetMapping("/sendMsgToAzureAndConsume")
    @ResponseBody
    public Person handleProductsFileUpload() {
        Person person = new Person();
        person.setName("Classic Azure Service BUS");
        person.setCash(300);
        Stream<String> hobbies = Stream.of("foo", "bar", "plop");
        person.setHobbies(hobbies.collect(Collectors.toList()));
        sender.sendPerson(gapQueue, person);
        return person;
    }

}
