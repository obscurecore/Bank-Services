package bank.service.stonksservice.repository;

import bank.service.stonksservice.model.Stonk;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Bucket reactive repository.
 *
 * @author Ruslan Potapov
 */
@Repository
public interface StonkRepository extends ReactiveMongoRepository<Stonk, String> {
}
