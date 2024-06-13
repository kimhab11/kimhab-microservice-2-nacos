package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.UserRepository;
import org.example.config.JwtTokenProvider;
import org.example.entity.RoleEntity;
import org.example.entity.UserEntity;
import org.example.model.UserLoginRequest;
import org.example.model.UserLoginResponse;
import org.example.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("user/auth")
@Slf4j
public class UserController {

    @Value("${test.data}")
    private String test;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("signup")
    public ResponseEntity<?> signUp(@RequestBody UserRequest req) {
        // find username in db
        var userEntityOptional = userRepository.findByUsername(req.getUsername());

        if (!userEntityOptional.isPresent()) {
            // encrypt pass
            req.setPassword(passwordEncoder.encode(req.getPassword()));

            UserEntity userEntity = new UserEntity();
            userEntity.setPassword(req.getPassword());
            userEntity.setEmail(req.getEmail());
            userEntity.setFirstName(req.getFirstName());
            userEntity.setLastName(req.getLastName());

            var role = new  RoleEntity();
            role.setId(1L);
            userEntity.setRole(role);
            userEntity.setUsername(req.getUsername());
         //   userEntity.s
            // save entity
            userRepository.save(userEntity);
        } else throw new RuntimeException("user exist");

        return ResponseEntity.ok(HttpStatus.CREATED.getReasonPhrase());

    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginRequest loginRequest){

        System.out.println(test);
        log.info("loginRequest: {}", loginRequest);
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException e){
            log.warn(e.getMessage());
        }

        var token = jwtTokenProvider.generateToken(authentication);
        log.info("token: {}", token);

        var jwtRes = new UserLoginResponse(token);
        return ResponseEntity.ok(jwtRes);
    }

    @GetMapping("detail")
    public ResponseEntity<?> getUserDetail(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();
                log.info("currentUserName: {}", currentUserName);
                return ResponseEntity.ok(authentication.getPrincipal());
            }
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

}
