package co.edu.ufps.innova.authentication.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Service
@RequiredArgsConstructor
public class PasswordService implements IPasswordService {

    private final BCryptPasswordEncoder bCrypt;

    /**
     * {@inheritDoc}
     */
    @Override
    public String generatePassword() {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder()
                .withinRange(48, 90).build();
        return pwdGenerator.generate(12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encryptPassword(String password) {
        return bCrypt.encode(password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validatePassword(String actualPassword, String userPassword) {
        return BCrypt.checkpw(actualPassword, userPassword);
    }

}
