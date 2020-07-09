package bank.service.userservice.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestService {
    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @HystrixCommand(fallbackMethod = "failed")
    public String data() {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("stonks-service", false);
        String serviceBaseUrl = instanceInfo.getHomePageUrl();
        String result = new RestTemplate().getForObject(serviceBaseUrl + "data", String.class);
        return result;
    }

    public String failed() {
        return "Service is not available now. Please try later";
    }

}
