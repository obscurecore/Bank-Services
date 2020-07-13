package bank.service.stonksservice.controller;


import bank.service.stonksservice.exception.StonkNotFoundException;
import bank.service.stonksservice.model.Stonk;
import bank.service.stonksservice.payload.ErrorResponse;
import bank.service.stonksservice.repository.StonkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;
import java.util.logging.Logger;

/**
 * The type Stonk controller.
 * @author Ruslan Potapov
 */
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class StonkController {

    private final Environment env;
    private final StonkRepository stonkRepository;
    Logger logger = Logger.getLogger(StonkController.class.getName());

    @RequestMapping("/")
    public String home() {
        String home = "Stonks-Service running at port: " + env.getProperty("local.server.port");
        logger.info(home);
        return home;
    }

    @GetMapping(path = "/show")
    public Flux<Stonk> getAllEmployeesList() {
        logger.info("Get data from database (Feign Client on User-Service side)");
        return stonkRepository.findAll();
    }

    @GetMapping("/data")
    public Flux<Stonk> data() {
        logger.info("Get data from database (RestTemplate on User-Service side)");
        return stonkRepository.findAll();
    }

    @GetMapping("/getAll")
    public Flux<Stonk> getAllBuckets() {
        return stonkRepository.findAll();
    }


    @PostMapping("/create")
    public Mono<Stonk> createStonk(@Valid @RequestBody Stonk stonk) {
        return stonkRepository.save(stonk);
        /*System.err.println("SAVE");
        return Mono.just(new ResponseEntity<Void>(HttpStatus.OK));*/
    }

    @GetMapping("/get/{id}")
    public Mono<ResponseEntity<Stonk>> getStonkById(@PathVariable(value = "id") String stonkId) {
        return stonkRepository.findById(stonkId)
                .map(ResponseEntity::ok)  // then the map operator is called on this Stock to wrap it in a ResponseEntity object with status code 200 OK
                .defaultIfEmpty(ResponseEntity.notFound().build());   // finally there is a call to defaultIfEmpty to build an empty ResponseEntity with status 404 NOT FOUND if the Stock was not found.
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Stonk>> updateStonk(@PathVariable(value = "id") String stonkId,
                                                    @Valid @RequestBody Stonk stonk) {
        return stonkRepository.findById(stonkId)
                .flatMap(existingStonk -> {
                    existingStonk.setDescription(stonk.getDescription());
                    existingStonk.setImageLink(stonk.getImageLink());    // then calls flatMap with this movie to update its entries using its setters and the values from the Bucket passed as argument.
                    return stonkRepository.save(existingStonk);
                })
                .map(updateStonk -> new ResponseEntity<>(updateStonk, HttpStatus.OK))  // Then it saves them to the database and wraps this updated Bucket in a ResponseEntity with status code 200 OK in case of success or 404 NOT FOUND in case of failure.
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteStonk(@PathVariable(value = "id") String stonkId) {

        return stonkRepository.findById(stonkId)  // First, search the Bucket you want to delete.
                .flatMap(existingStonk ->
                        stonkRepository.delete(existingStonk)  // Next, delete and return 200 OK to show your delete was successful
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));  // or return 404 NOT FOUND to say the Bucket was not found
    }

    @DeleteMapping("/deleteAllStonks")
    public Mono<Void> deleteAllStonks() {
        return stonkRepository.deleteAll();
    }

    // Stonks are Sent to the client as Server Sent Events
    @GetMapping(value = "/stream/stonks", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Stonk> streamAllStonks() {
        return stonkRepository.findAll();
    }

    // Get default value every 1 second
    @GetMapping(value = "/stream/stonks/default", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Stonk> emitBuckets() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(val -> new Stonk(""+val, "Python", "default theme", 0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS2f2NovvIAZjv9jGeSmzXnWnkiIXZX2VR7i2e-v_V756pWxFSS"));
    }

    // Get all Bucket from the database (every N second you will receive 1 record from the DB)
    @GetMapping(value = "/stream/stonks/delay", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Stonk> streamAllStonksDelay() {
        logger.info("Get data from database (WebClient on User-Service side)");
        return stonkRepository.findAll().delayElements(Duration.ofSeconds(2));
    }

    // Exception Handling Examples (These can be put into a @ControllerAdvice to handle exceptions globally)
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("A Stonk with the same id already exists"));
    }

    @ExceptionHandler(StonkNotFoundException.class)
    public ResponseEntity handleStockNotFoundException(StonkNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

}
