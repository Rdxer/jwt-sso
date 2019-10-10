package com.rdxer.jwtsso.security;

import com.rdxer.jwtsso.model.Account;
import com.rdxer.jwtsso.model.Role;
import com.rdxer.jwtsso.server.AccountServer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author lxf
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    AccountServer accountServer;

    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountServer.findByName(username);

        if (account == null){
            throw new UsernameNotFoundException(username);
        }

        User.UserBuilder userBuilder = User.builder();

        var roles =  new ArrayList<String>();

        for (Role role : account.getRoles()) {
            roles.add(role.getName());
        }

        userBuilder
                .username(account.getUsername())
                .password(passwordEncoder.encode(account.getPassword()))
                .roles(roles.toArray(new String[]{}));

        return userBuilder.build();
    }
}
