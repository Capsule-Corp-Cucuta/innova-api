package co.edu.ufps.innova.security.domain.service;

import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    public String generatePassword() {
        return "HolaMundo";
    }

    public String generatePassword(String password) {
        return "HolaMundo";
    }

    public boolean validatePassword(String password) {
        return true;
    }

}
