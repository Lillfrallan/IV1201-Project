package com.iv1201.group10.springInit.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Configuration class for Spring Security settings.
 */
@Configuration
@EnableWebSecurity
public class Security {

    /**
     * Configurations for the security filter chain.
     *
     * @param httpSecurity The HttpSecurity object to configure security.
     * @return The SecurityFilterChain object.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                (requests ->
                        requests.requestMatchers("/register").permitAll()
                                .requestMatchers("/recruiter", "/updateStatus", "/status").hasRole("recruiter")
                                .requestMatchers("/competence", "/availability").hasRole("applicant")
                                .anyRequest().authenticated()))
                .formLogin((form) ->
                        form.loginPage("/login").permitAll()
                        .successHandler(authenticationSuccessHandler()))
                .logout(LogoutConfigurer::permitAll);

        return httpSecurity.build();
    }

    /**
     * Provides a password encoder bean for encrypting passwords.
     *
     * @return A BCryptPasswordEncoder object.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides an authentication success handler bean for redirecting user to appropriate URL path.
     *
     * @return An instance of URLHandler.
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new URLHandler();
    }
}