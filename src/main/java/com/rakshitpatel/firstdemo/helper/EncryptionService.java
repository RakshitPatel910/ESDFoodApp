package com.rakshitpatel.firstdemo.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncryptionService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean validate(String password, String encryptedPassword) {

        System.out.println( passwordEncoder.matches(password, encryptedPassword) );


        return passwordEncoder.matches(password, encryptedPassword);
    }

//    public String

}
