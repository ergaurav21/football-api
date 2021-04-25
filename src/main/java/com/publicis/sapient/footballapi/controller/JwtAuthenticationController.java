package com.publicis.sapient.footballapi.controller;


import com.publicis.sapient.footballapi.model.JwtRequest;
import com.publicis.sapient.footballapi.model.JwtResponse;
import com.publicis.sapient.footballapi.service.JwtTokenService;
import com.publicis.sapient.footballapi.service.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private final JwtTokenService jwtTokenService;
    private final JwtUserDetailService userDetailsService;

    @Autowired
    public JwtAuthenticationController (final JwtTokenService jwtTokenService, final JwtUserDetailService userDetailsService ){
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody final JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenService.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(final String username, final String password) throws Exception {
       if(!"admin".equals(username) || !"admin".equals(password)){
           throw new IllegalArgumentException("INVALID_CREDENTIALS");
       }
    }
}