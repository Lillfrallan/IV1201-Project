package com.iv1201.group10.springInit.Service;

import com.iv1201.group10.springInit.entity.Person;
import com.iv1201.group10.springInit.entity.Role;
import com.iv1201.group10.springInit.repository.PersonRepository;
import com.iv1201.group10.springInit.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("LOGGING IN!!!");
        Person user = personRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Role role = roleRepository.findRoleByRoleId(user.getRoleId());
        System.out.println(role.getName());
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(role.getName())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
