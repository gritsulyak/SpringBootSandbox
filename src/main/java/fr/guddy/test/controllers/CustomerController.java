package fr.guddy.test.controllers;

import fr.guddy.test.model.Customer;
import fr.guddy.test.repositories.CustomerRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository repository;

    @ApiOperation(value = "getCustomersByLastName", nickname = "getCustomersByLastName")
    @RequestMapping("/customers/{last_name}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Customer.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public List<Customer> getCustomersByLastName(@PathVariable("last_name") final String lastName) {
        return repository.findByLastName(lastName);
    }

    @ApiOperation(value = "getCustomers", nickname = "getCustomers")
    @RequestMapping("/customers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Customer.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    @ApiOperation(value = "addCustomer", nickname = "addCustomer")
    @PostMapping("/customers")
    public ResponseEntity<String> addCustomer(@RequestBody final Customer customer) {
        repository.save(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //This is of course a very naive implementation! We are assuming unique names...
    @ApiOperation(value = "deleteCustomerByLastName", nickname = "deleteCustomerByLastName")
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
