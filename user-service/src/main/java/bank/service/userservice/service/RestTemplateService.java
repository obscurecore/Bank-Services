package bank.service.userservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sun.tools.sjavac.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * The type Rest template service.
 *
 * @author Ruslan Potapov
 */
@Service
@RequiredArgsConstructor
public class RestTemplateService {
    private final RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "failed")
    public String data() {
        String response = restTemplate.getForObject("http://stonks-service/data", String.class);
        Log.log(Log.Level.INFO, response);
        return response;
    }

    public String failed() {
        String error = "Service is not available now. Please try later";
        Log.log(Log.Level.INFO, error);
        return error;
    }

}
