package bank.service.userservice.controller;


import bank.service.userservice.model.Stonk;
import bank.service.userservice.service.ServiceFeignClient;
import bank.service.userservice.service.RestTemplateService;
import bank.service.userservice.service.WebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * Communicate with stonks-service and will be gotten data from DB through stonks-service.
 * So did't directly communicate with DB
 *
 * @author Ruslan Potapov
 */
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final Environment env;
    private final RestTemplateService service;
    private WebClientService webClientService;

    /**
     * This is useful for debugging
     * When having multiple instance of gallery service running at different ports.
     * We load balance among them, and display which instance received the request.
     *
     * @return the string
     */
    @RequestMapping("/")
    public String home() {
        return "Hello from User-Service running at port: " + env.getProperty("local.server.port");
    }

    /**
     * Using Feign Client
     *
     * @param model the model
     * @return the data
     */
    @RequestMapping(path = "/getAllDataFromGalleryService")
    public List<Stonk> getData(Model model) {
        return ServiceFeignClient.FeignHolder.create().getAllEmployeesList();
    }

    /**
     * Using RestTemplate
     *
     * @return the string
     */
    @GetMapping("/data")
    public String data() {
        return service.data();

    }

    /**
     * Using WebClient
     *
     * @return the data by web client
     */
    @GetMapping(value = "/getDataByWebClient",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Stonk> getDataByWebClient() {
        return webClientService.getDataByWebClient();
    }




}
