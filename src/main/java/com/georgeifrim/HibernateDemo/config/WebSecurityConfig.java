package com.georgeifrim.HibernateDemo.config;

import com.georgeifrim.HibernateDemo.entities.User;
import com.georgeifrim.HibernateDemo.exceptions.users.UserWithUsernameNotExist;
import com.georgeifrim.HibernateDemo.repositories.UserRepo;
import com.georgeifrim.HibernateDemo.services.JpaUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    private final JpaUserDetailsService userDetails;
    private UserRepo userRepo;
    private final Map<String, Integer> loginAttempts = new HashMap<>();
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.httpBasic(Customizer.withDefaults())
                    .authorizeHttpRequests(a -> a.requestMatchers(HttpMethod.GET).authenticated()
                            .requestMatchers(HttpMethod.POST).authenticated()
                            .requestMatchers(HttpMethod.DELETE).authenticated()
                            .anyRequest().permitAll())
                .formLogin(AbstractHttpConfigurer::disable)
                .exceptionHandling(exc -> exc.authenticationEntryPoint(
                        (request, response, authException) -> {
                            String username = request.getHeader("Authorization");
                            username = username.replace("Basic ", "");
                            username = new String(Base64.getDecoder().decode(username)).split(":")[0];
                            int currentAttempts = loginAttempts.getOrDefault(username, 0);
                            loginAttempts.put(username, ++currentAttempts);

                            if(loginAttempts.get(username) >= 3){
                                blockUser(username);
                                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                                response.getWriter().write("Too many failed login attempts, wait 1 minute !");
                                response.getWriter().flush();
                            }
                        }
                ))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetails);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(daoAuthenticationProvider())
                .build();
    }

    private void blockUser(String username){
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserWithUsernameNotExist(username));
        user.setActive(false);
        user.setBlockedUntil(Instant.now().plus(2, ChronoUnit.MINUTES));
        userRepo.save(user);
    }

    @Scheduled(fixedRate = 60000)
    public void unblockUsers(){
        List<User> blockedUsers = userRepo.findByIsActiveTrueAndBlockedUntilBefore(Instant.now());

        for(User user : blockedUsers){
            user.setActive(true);
            user.setBlockedUntil(null);
            userRepo.save(user);
        }
    }


}
