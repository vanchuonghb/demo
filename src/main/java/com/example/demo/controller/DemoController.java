package com.example.demo.controller;

import com.example.demo.config.JwtAuthenticationEntryPoint;
import com.example.demo.config.JwtTokenProvider;
import com.example.demo.domain.AppUser;
import com.example.demo.dto.ResponseData;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.JwtAuthenticationResponse;
import com.example.demo.payload.LoginRequest;
import com.example.demo.payload.SignUpRequest;
import com.example.demo.repo.AppRoleRepo;
import com.example.demo.repo.AppUserRepo;
import com.example.demo.service.UserServiceDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @Autowired
    private UserServiceDemo userServiceDemo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    AppUserRepo appUserRepo;
    //
    @Autowired
    AppRoleRepo appRoleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("service is running...");
    }

    @GetMapping("/user-info")
    public ResponseEntity<ResponseData> getUserInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseData.success("Thanh cong", userServiceDemo.loadAllData()));
    }

    @GetMapping("/userAccountInfo")
    public ResponseEntity<ResponseData> success() {
        logger.info("login successful");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseData.success("Thanh cong", userServiceDemo.loadAllData()));
    }

    @GetMapping("/403")
    public ResponseEntity<ResponseData> notFound() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseData.fail("Access Denied !"));
    }

    @GetMapping("/logout")
    public ResponseEntity<ResponseData> signOut() {
        persistentTokenRepository.removeUserTokens("admin");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseData.success("Sign out successful !"));
    }

    @GetMapping("/logoutSuccessful")
    public ResponseEntity<ResponseData> logoutSuccessful() {
        logger.info("Sign out successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseData.success("Sign out successfully !"));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }


    @RequestMapping(value = "/sign-up", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (appUserRepo.existsByUserName(signUpRequest.getUsername())) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ResponseData.fail("Username is already taken!"));
        }

        if (appUserRepo.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ResponseData.fail("Email Address already in use!"));
        }

        AppUser user = new AppUser(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setEncrytedPassword(passwordEncoder.encode(user.getEncrytedPassword()));

//        AppRole userRole = appRoleRepo.findBy RoleName.ROLE_USER)
//                .orElseThrow(() -> new AppException("User Role not set."));
//
//
        AppUser result = appUserRepo.save(user);
//
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUserName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

}
