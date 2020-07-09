package bank.service.stonksservice;

import bank.service.stonksservice.model.Stonk;
import bank.service.stonksservice.repository.BucketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Flux;

@EnableEurekaClient
@SpringBootApplication
@EnableReactiveMongoRepositories
public class StonksServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StonksServiceApplication.class, args);
    }


    /**
     * Creates a Flux of four sample Persons objects, saves them to the DB.
     * Then, queries all the Persons from the DB and print them to the console.
     *
     * @param bucketRepository the bucket repository
     * @return the command line runner
     * @author Ruslan Potapov
     */
    @Bean
    CommandLineRunner run(BucketRepository bucketRepository) {
        return args -> {
            bucketRepository.deleteAll()
                    .thenMany(Flux.just(
                            new Stonk((long) 1, "Java", "OOP", 280, "http://infopulse-univer.com.ua/images/trenings/java.png"),
                            new Stonk((long) 2, "Java", "Stream API", 437, "https://www.hdwallpaperslife.com/wp-content/uploads/2018/09/JAVA14-480x270.png"),
                            new Stonk((long) 3, "Java", "Collections", 14, "https://i.ytimg.com/vi/oOOESCvGGcI/hqdefault.jpg"),
                            new Stonk((long) 4, ".NET", "Basic", 1213, "https://upload.wikimedia.org/wikipedia/commons/0/0e/Microsoft_.NET_logo.png")
                    )
                            .flatMap(bucketRepository::save))
                    .thenMany(bucketRepository.findAll())
                    .subscribe(System.out::println);
        };
    }


}
