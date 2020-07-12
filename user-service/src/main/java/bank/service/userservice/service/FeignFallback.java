package bank.service.userservice.service;

import bank.service.userservice.model.Stonk;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Feign fallback behaviour
 *
 * @author Ruslan Potapov
 */
@Component
public class FeignFallback implements ServiceFeignClient {
    @Override
    public List<Stonk> getAllEmployeesList() {
        return new ArrayList<>();
    }
}