package com.bosonit.backbusiness.security;

import com.bosonit.backbusiness.client.domain.Client;
import com.bosonit.backbusiness.client.infrastructure.repository.ClientRepository;
import com.bosonit.backbusiness.utils.exceptions.CustomUnprocessableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/business/login")
public class TokenController {

    @Autowired
    ClientRepository clientRepository;

    @PostMapping
    public ResponseEntity<String> login(
            @RequestHeader("email") String email,
            @RequestHeader("password") String password) {
        Client client = clientRepository.findByEmail(email);
        if (!client.getPassword().equals(password)) {
            throw new CustomUnprocessableException("Incorrect Password!");
        }
        if (client.getEmail().equals("adminbus@business.local")) {
            return new ResponseEntity<>(getJWTToken(client.getIdClient(), email, "ADMIN"), HttpStatus.OK);
        }
        return new ResponseEntity<>(getJWTToken(client.getIdClient(), email, "USER"), HttpStatus.OK);
    }

    @GetMapping("/{token}")
    public ResponseEntity<Void> checkToken(@PathVariable String token) {
        if (this.verifyToken(token))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    private String getJWTToken(UUID id, String email, String role) {
        String secretKey = "${secretKey}";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(role);
        String token = Jwts
                .builder()
                .setId(String.valueOf(id))
                .setSubject(email)
                .claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).toList())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();
        return "Bearer " + token;
    }

    private boolean verifyToken(String token) {
        final String SECRET = "${secretKey}";
        final String PREFIX = "Bearer ";
        try {
            String jwtToken = token.replace(PREFIX, "");
            Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
