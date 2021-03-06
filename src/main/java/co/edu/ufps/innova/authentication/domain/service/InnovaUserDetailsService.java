package co.edu.ufps.innova.authentication.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.user.domain.dto.UserType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import co.edu.ufps.innova.user.domain.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Service
@RequiredArgsConstructor
public class InnovaUserDetailsService implements UserDetailsService {

    private final IUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<co.edu.ufps.innova.user.domain.dto.User> user = repository.findByEmail(username);
        if (user.isPresent()) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            switch (repository.getUserType(user.get().getId())) {
                case "ADMINISTRADOR":
                    authorities.add(new SimpleGrantedAuthority(UserType.ADMINISTRADOR.name()));
                    break;
                case "ASESOR":
                    authorities.add(new SimpleGrantedAuthority(UserType.ASESOR.name()));
                    break;
                case "CONTACTO":
                    authorities.add(new SimpleGrantedAuthority(UserType.CONTACTO.name()));
                    break;
                case "CLIENTE":
                    authorities.add(new SimpleGrantedAuthority(UserType.CLIENTE.name()));
                    break;
            }
            return new User(
                    user.get().getEmail(),
                    repository.getPassword(user.get().getId()),
                    user.get().isActive(),
                    user.get().isActive(),
                    user.get().isActive(),
                    user.get().isActive(),
                    authorities
            );
        }
        return null;
    }

}
