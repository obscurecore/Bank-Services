package bank.service.userservice.service;

import bank.service.userservice.model.Stonk;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

/**
 * FeignClient - abstraction for calling REST, that native integrated with Ribbon and automatically convert into java objects
 * name - calling certain service if it registered in Eureka, url - if external
 * fallback - in case  if service or DB is unavailable
 */
@FeignClient(name = "stonks-service", url = "http://localhost:8081/", fallback = StatisticFallbackFactory.class)
public interface ServiceFeignClient {


    // Request. contain way that identical in gallery-service (same signature)
    @RequestLine("GET /show")
    List<Stonk> getAllEmployeesList();

}
