package pl.wp.gameofthroneapplication.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.wp.gameofthroneapplication.service.CustomUserSecurityService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


private final CustomUserSecurityService customUserSecurityService;
private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    public SecurityConfig(CustomUserSecurityService customUserSecurityService) {
        this.customUserSecurityService = customUserSecurityService;
    }

//zasoby ktore sa dostepne dla wszystkich
    // zasoby ktore sa autentykowane

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.
                authorizeHttpRequests((request)->request
                        .requestMatchers("/","/register")
                        .permitAll().anyRequest()
                        .authenticated())
                .formLogin((form)->form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .defaultSuccessUrl("books")
                        .permitAll());
        return security.build();
    }

    @Bean
    public DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
        dao.setUserDetailsService(customUserSecurityService);
        dao.setPasswordEncoder(encoder);
        return dao;
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity)throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder=httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider());
        return authenticationManagerBuilder.build();
    }

}
