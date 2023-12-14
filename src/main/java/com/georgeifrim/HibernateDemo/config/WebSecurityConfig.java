package com.georgeifrim.HibernateDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.httpBasic(Customizer.withDefaults())
//                    .authorizeHttpRequests(a -> a.requestMatchers(HttpMethod.GET,"/trainee/**").authenticated()
//                            .requestMatchers(HttpMethod.POST, "/trainee/**").hasAuthority("read")
//                            .requestMatchers(HttpMethod.DELETE, "/trainee/**").authenticated())
//                .build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


}
