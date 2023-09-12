package com.example.chadi.Controller;

import com.example.chadi.Auth.AuthenticationRequest;
import com.example.chadi.Auth.AuthenticationResponse;
import com.example.chadi.Config.JwtUtils;

import com.example.chadi.Repository.UserRep;
import lombok.RequiredArgsConstructor;
import lombok.var;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRep Rep;


    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                                            request.getEmail(),
                                                  request.getPassword()
                                                                         )
                                                                            );
        var user = Rep.findByEmail(request.getEmail())
                                .orElseThrow(NullPointerException::new);


        var jwtToken = jwtUtils.generateToken(user);

        return AuthenticationResponse.builder()
                                        .token(jwtToken)
                                             .build();
    }
    //    public AuthenticationResponse register(RegisterRequest request) {
//   var user = User.builder()
//           .firstname(request.getFirstname())
//           .lastname(request.getLastname())
//           .email(request.getEmail())
//           .password(passwordEncoder.encode(request.getPassword()))
//           .role(Role.USER)
//           .build();
//   Rep.save(user);
//
//    }
}
