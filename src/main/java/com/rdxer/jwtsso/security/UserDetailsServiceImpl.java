package com.rdxer.jwtsso.security;

import com.rdxer.jwtsso.model.Account;
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
//    @PreAuthorize("#username != null and #username != \"\"")
//    @PreFilter("#username != \"\"")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountServer.findByName(username);

        if (account == null){
            throw new UsernameNotFoundException(username);
        }

        User.UserBuilder userBuilder = User.builder();

        userBuilder
                .username(account.getUsername())
                .password(passwordEncoder.encode(account.getPassword()))
                .roles("USER");

        return userBuilder.build();
    }
}
