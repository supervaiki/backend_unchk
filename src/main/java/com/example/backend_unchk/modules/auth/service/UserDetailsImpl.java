package com.example.backend_unchk.modules.auth.service;

import com.example.backend_unchk.modules.auth.entity.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    private String motDePasse;

    private String nom;

    private String prenom;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(Utilisateur user) {
    List<GrantedAuthority> authorities = user.getRoles()
            .stream()
            // Utilisez directement le nom tel qu'il est en base de données
            .map(role -> new SimpleGrantedAuthority(role.getNom().toUpperCase()))
            .collect(Collectors.toList());

    return new UserDetailsImpl(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getNom(),
            user.getPrenom(),
            authorities
    );
}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return username;
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