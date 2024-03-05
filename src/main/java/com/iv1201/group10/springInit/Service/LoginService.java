package com.iv1201.group10.springInit.Service;

import com.iv1201.group10.springInit.entity.Person;
import com.iv1201.group10.springInit.security.PersonPrincipal;
import com.iv1201.group10.springInit.entity.Role;
import com.iv1201.group10.springInit.repository.PersonRepository;
import com.iv1201.group10.springInit.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LoginService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Loads user details by username.
     *
     * @param username The username of the user.
     * @return UserDetails object representing the user.
     * @throws UsernameNotFoundException If the user with the specified username is not found.
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Role role = roleRepository.findRoleByRoleId(person.getRoleId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        return new PersonPrincipal(person, authorities);
    }
}