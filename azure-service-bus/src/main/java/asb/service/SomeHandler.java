package asb.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

@Service
@Log4j2
public class SomeHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        log.error("Error in listener", t);
    }
}