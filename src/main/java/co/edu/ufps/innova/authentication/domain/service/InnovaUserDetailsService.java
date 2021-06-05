package co.edu.ufps.innova.authentication.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.user.domain.dto.UserType;
import co.edu.ufps.innova.user.domain.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
@RequiredArgsConstructor
public class InnovaUserDetailsService implements UserDetailsService {

    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<co.edu.ufps.innova.user.domain.dto.User> user = service.findByEmail(username);
        if (user.isPresent()) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            switch (service.getUserType(user.get().getId())) {
                case "CONSULTANT":
                    authorities.add(new SimpleGrantedAuthority(UserType.CONSULTANT.name()));
                    break;
                case "CONTACT":
                    authorities.add(new SimpleGrantedAuthority(UserType.CONTACT.name()));
                    break;
                case "CLIENT":
                    authorities.add(new SimpleGrantedAuthority(UserType.CLIENT.name()));
                    break;
                case "ADMIN":
                default:
                    authorities.add(new SimpleGrantedAuthority(UserType.ADMIN.name()));
                    break;
            }
            return new User(
                    user.get().getEmail(),
                    service.getPassword(user.get().getId()),
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
