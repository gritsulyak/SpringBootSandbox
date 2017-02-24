package fr.guddy.test.controllers;

import fr.guddy.test.model.Customer;
import fr.guddy.test.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository repository;

    @RequestMapping("/customers/{last_name}")
    public List<Customer> getCustomersByLastName(@PathVariable("last_name") final String lastName) {
        return repository.findByLastName(lastName);
    }

    @RequestMapping("/customers")
    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    @PostMapping("/customers")
    public ResponseEntity<String> addCustomer(@RequestBody final Customer customer) {
        repository.save(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //This is of course a very naive implementation! We are assuming unique names...
    @DeleteMapping("/customers/{last_name}")
    public ResponseEntity<String> deleteCustomerByLastName(@PathVariable("last_name") final String lastName) {
        final List<Customer> customers = repository.findByLastName(lastName);
        if (customers.size() == 1) {
            final Customer customer = customers.get(0);
            repository.delete(customer);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
