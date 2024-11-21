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

        customer.setPassword(encryptionService.encryptPassword(customer.getPassword()));

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

    public String updateCustomer( String authToken, CustomerRequest req) {
        Customer customer = getCustomer(req.email());

//        String token = req.accessToken();

        if(!jwtHelper.validateToken(authToken, customer.getEmail())){
            throw new RuntimeException("Invalid access token");
        }

        customer.setFirstName(req.firstName());
        customer.setLastName(req.lastName());
        customer.setAddress(req.address());
        customer.setPinCode(req.pinCode());

        customerRepo.save(customer);

        return "Customer updated successfully!";
    }

    public String deleteCustomer( String authToken, CustomerRequest req ) {
        Customer customer = getCustomer(req.email());

//        String token = req.accessToken();

        if(!jwtHelper.validateToken(authToken, customer.getEmail())){
            throw new RuntimeException("Invalid access token");
        }

        customerRepo.delete(customer);

        return "Customer deleted successfully!";
    }

    public String login(LoginRequest req){
        Customer customer = getCustomer(req.email());

        if(!encryptionService.validate(req.password(), customer.getPassword())){
            System.out.println( req.password() + " " + customer.getPassword() );
            return "Incorrect password";
        }

        return jwtHelper.generateToken(req.email());
    }

}
