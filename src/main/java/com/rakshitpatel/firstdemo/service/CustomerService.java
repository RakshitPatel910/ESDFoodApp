package com.rakshitpatel.firstdemo.service;

import com.rakshitpatel.firstdemo.dto.CustomerRequest;
import com.rakshitpatel.firstdemo.dto.CustomerResponse;
import com.rakshitpatel.firstdemo.dto.LoginRequest;
import com.rakshitpatel.firstdemo.entity.Customer;
import com.rakshitpatel.firstdemo.helper.EncryptionService;
import com.rakshitpatel.firstdemo.helper.JWTHelper;
import com.rakshitpatel.firstdemo.mapper.CustomerMapper;
import com.rakshitpatel.firstdemo.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String createCustomer(CustomerRequest req) {
        Customer customer = customerMapper.toCustomer(req);
        customer.setPassword(customer.getPassword());

//        System.out.println(customer.getFirstName() + customer.getLastName());

        customerRepo.save(customer);

        return "Customer created!";
    }

    public Customer getCustomer( String email ){

//        System.out.println(email);

        return customerRepo.findByEmail(email)
                .orElseThrow( () -> new RuntimeException("Customer not found with email: " + email) );
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = getCustomer(email);

        return customerMapper.toCustomerResponse(customer);
    }

    public String login(LoginRequest req){
        Customer customer = getCustomer(req.email());

        if(!encryptionService.validate(req.password(), encryptionService.encryptPassword(customer.getPassword()))){
            System.out.println( req.password() + " " + customer.getPassword() );
            return "Incorrect password";
        }

        return jwtHelper.generateToken(req.email());
    }

}