package fr.guddy.test.repositories;

import fr.guddy.test.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByFirstName(final String firstName);

    List<Customer> findByLastName(final String lastName);

}
