import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Log4j2
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = { "jms", "jms.components"})
public class SpringJmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJmsApplication.class, args);
    }


}