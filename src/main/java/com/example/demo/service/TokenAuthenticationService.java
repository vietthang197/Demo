package com.example.demo.service;

import com.example.demo.model.UserProfileDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.SignatureException;
import org.springframework.security
        .authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;

import static java.util.Collections.emptyList;

import java.util.Base64;
import java.util.List;

public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 86_400_000; // 10 days
    static String randomString = "k`!jfj!fj#23af^h((&&)(UY_HFH@#$%&^*()...adfafdh";
    static String SECRET = Base64.getEncoder().encodeToString(randomString.getBytes());
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse res, Authentication authResult) {
        String JWT = Jwts.builder()
                .setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        res.setStatus(HttpServletResponse.SC_OK);
        res.setContentType("application/json");
        try {
            PrintWriter writer = res.getWriter();
            ObjectMapper objectMapper = new ObjectMapper();
            List<GrantedAuthority> data = (List<GrantedAuthority>) authResult.getAuthorities();
            writer.write(objectMapper.writeValueAsString(new UserProfileDTO(JWT, authResult.getName(), data)));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = null;
            try{
                 user = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();
            }catch (SignatureException e){

            }

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;
        }
        return null;
    }
}
