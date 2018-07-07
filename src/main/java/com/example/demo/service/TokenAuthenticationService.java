package com.example.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.model.UserGrantedAuthority;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.SignatureException;
import org.springframework.security
        .authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;


public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 5000; // 10 days
    static String randomString = "k`!jfj!fj#23af^h((&&)(UY_HFH@#$%&^*()...adfafdh";
    static String SECRET = Base64.getEncoder().encodeToString(randomString.getBytes());
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static void addAuthentication(HttpServletResponse res, Authentication authResult) {
        Date dt = new Date();
        Date dtStart = dt;
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        String JWT = null;
        List<GrantedAuthority> data = (List<GrantedAuthority>) authResult.getAuthorities();
        try {
            JWT = com.auth0.jwt.JWT
                    .create()
                    .withClaim("username", authResult.getName())
                    .withClaim("createAt", dtStart)
                    .withSubject(objectMapper.writeValueAsString(data))
                    .withExpiresAt(dt).sign(Algorithm.HMAC512(SECRET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        res.setStatus(HttpServletResponse.SC_OK);
        res.setHeader("Authorization", JWT);

    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String username = null;
            List<GrantedAuthority> authorityList = null;
            try{
                Algorithm algorithm = Algorithm.HMAC512(SECRET);
                JWTVerifier verifier = JWT.require(algorithm)
                        .build();
                DecodedJWT jwt = verifier.verify(token);
                username = jwt.getClaim("username").asString();
                String authorities = jwt.getSubject();
               authorityList = objectMapper.readValue(authorities, new TypeReference<List<UserGrantedAuthority>>(){});
            }catch (SignatureException e){
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return username != null ?
                    new UsernamePasswordAuthenticationToken(username, null, authorityList) :
                    null;
        }
        return null;
    }
}
