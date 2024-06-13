package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.example.UserRepository;
import org.example.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if (!userEntity.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        UserEntity user = userEntity.get();
        log.info("UserEntity: {}", user);

//        var simpleGrantedAuthorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole().getName().toString()));
//        log.info("simpleGrantedAuthorities: {}", simpleGrantedAuthorities);
        var user1 = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthority(user));
        return user1;
    }

    public Set<SimpleGrantedAuthority> getAuthority(UserEntity user){
        var userRoles = user.getRole();
        var roleString = userRoles.getName();
        log.info("userRoles: {}", roleString);
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+roleString));
        log.info("simpleGrantedAuthorities: {}", authorities);
        return authorities;
    }

}