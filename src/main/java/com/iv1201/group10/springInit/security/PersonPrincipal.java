package com.iv1201.group10.springInit.security;

import com.iv1201.group10.springInit.entity.Person;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@Setter
@Getter
public class PersonPrincipal implements UserDetails {
    private final Person person;
    private final Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructs a new PersonPrincipal with the specified person and authorities.
     *
     * @param person      The person object representing the user.
     * @param authorities The authorities granted to the user.
     */
    public PersonPrincipal(Person person, Collection<? extends GrantedAuthority> authorities) {
        this.person = person;
        this.authorities = authorities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPassword() {
        return person.getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return person.getUsername();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}