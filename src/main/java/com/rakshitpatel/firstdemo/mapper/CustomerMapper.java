package com.rakshitpatel.firstdemo.mapper;

import com.rakshitpatel.firstdemo.dto.CustomerRequest;
import com.rakshitpatel.firstdemo.dto.CustomerResponse;
import com.rakshitpatel.firstdemo.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest req){
        return Customer.builder()
                .firstName(req.firstName())
                .lastName(req.lastName())
                .email(req.email())
                .password(req.password())
                .address(req.address())
                .pinCode(req.pinCode())
//                .accessToken(req.accessToken())
                .build();
    }

    public CustomerResponse toCustomerResponse(Customer customer){
        return new CustomerResponse(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail()
        );
    }

}
