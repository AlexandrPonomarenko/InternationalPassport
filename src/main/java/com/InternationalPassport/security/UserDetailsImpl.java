package com.InternationalPassport.security;

import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Role;
import com.InternationalPassport.businessLayer.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDetailsImpl implements UserDetails {
    public static final Logger logger = LogManager.getLogger(UserDetailsImpl.class);

    private Customer customer;
    private List<Role> roles;
    private Map<String, String> tempRoles = new HashMap<>();


    public UserDetailsImpl(Customer customer) {
        this.customer = customer;
    }

    public UserDetailsImpl(Customer customer, List<Role> roles) {
        this.customer = customer;
        this.roles = roles;
        initRoles();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        if ("User".equals(customer.getRole().getRole())) {
            authorities.add(new SimpleGrantedAuthority(tempRoles.get(customer.getRole().getRole())));
        } else if ("Manager".equals(customer.getRole().getRole())) {
            authorities.add(new SimpleGrantedAuthority(tempRoles.get(customer.getRole().getRole())));
            authorities.add(new SimpleGrantedAuthority(tempRoles.get("User")));
        } else {
            authorities.add(new SimpleGrantedAuthority(tempRoles.get(customer.getRole().getRole())));
            authorities.add(new SimpleGrantedAuthority(tempRoles.get("Manager")));
            authorities.add(new SimpleGrantedAuthority(tempRoles.get("User")));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getLogin();
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

    public Customer getCust() {
        return customer;
    }

    private void initRoles() {
        tempRoles.put("CEO", "ROLE_CEO");
        tempRoles.put("Manager", "ROLE_Manager");
        tempRoles.put("User", "ROLE_User");
    }
}
