package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.models.JWTRequest;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.models.JWTResponse;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.JWTService;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.JpaUserDetailsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JpaUserDetailsService jwtUserDetailService;
    private final JWTService jwtService;

    @PostMapping("/login") // localhost:8080/authenticate
    public ResponseEntity<?> postToken(@RequestBody JWTRequest request) {
        this.authenticate(request);

        final var userDetails = this.jwtUserDetailService.loadUserByUsername(request.getUsername());

        final var token = this.jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }

    private void authenticate(JWTRequest request) {
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException | DisabledException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
