package asb.web;

import asb.components.Receiver;
import asb.components.Sender;
import asb.pojo.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/sendMsg")
    @ResponseBody
    public Person handleProductsFileUpload() {
        Person person = new Person();
        person.setName("a guy");
        person.setAge(30);
        Stream<String> hobbies = Stream.of("foo", "bar", "plop");
        person.setHobbies(hobbies.collect(Collectors.toList()));

        sender.send("q.GAP", person);
        return person;
    }

}
