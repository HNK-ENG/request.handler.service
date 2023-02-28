package com.example.request.handler.service.repository;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;


@Getter
@Setter
@ToString
@Entity
@Table(name = "user_login")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name="pk_sequence", sequenceName="entity_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO,generator="pk_sequence")
    private Long id;

    private String userName;

    private String password;

    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

