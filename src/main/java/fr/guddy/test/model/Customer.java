package fr.guddy.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

    @Id
    public String id;

    @ApiModelProperty(notes = "The first name of the customer")
    public String firstName;
    @ApiModelProperty(notes = "The last name of the customer")
    public String lastName;

    public Customer() {
    }

    public Customer(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
