import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@Log4j2
@EnableJms
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = { "asb", "asb.components", "asb.config"})
public class SpringJmsApplicationASB {

    public static void main(String[] args) {
        SpringApplication.run(SpringJmsApplicationASB.class, args);
    }


}