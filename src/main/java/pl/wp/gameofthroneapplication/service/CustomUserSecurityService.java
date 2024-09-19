package pl.wp.gameofthroneapplication.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.wp.gameofthroneapplication.databaseutils.CustomUserDao;
import pl.wp.gameofthroneapplication.model.CustomUser;

@Service
public class CustomUserSecurityService implements UserDetailsService {

    private final CustomUserDao customUserDao = new CustomUserDao();

    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        CustomUser customUser = customUserDao.findUserByEmail(mail);
        return User.withUsername(customUser.getEmail())
                .password(customUser.getPassword())
                .roles(customUser.getRole())
                .build();
    }
}

