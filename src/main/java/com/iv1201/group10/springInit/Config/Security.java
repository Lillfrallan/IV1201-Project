package com.iv1201.group10.springInit.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Security {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                (requests ->
                        requests.requestMatchers("/","/register").permitAll()
                        .anyRequest().authenticated()))
                .formLogin((form) ->
                        form.loginPage("/login").permitAll())
                .logout(LogoutConfigurer::permitAll);

        return httpSecurity.build();
    }
}
