package com.hb.cda.examrest.model;

import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "user_table")
public class User implements UserDetails {
    
    @Id
    @UuidGenerator
    private String id;
    private String lastname;
    private String firstanme;
    private String email;
    private String password;
    private String role;

    public User() {
    }

    public User(String id, String lastname, String firstanme, String email, String password, String role) {
        this.id = id;
        this.lastname = lastname;
        this.firstanme = firstanme;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getFirstanme() {
        return firstanme;
    }
    public void setFirstanme(String firstanme) {
        this.firstanme = firstanme;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
    return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
    }



}
