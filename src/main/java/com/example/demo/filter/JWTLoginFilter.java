package com.example.demo.filter;

import com.example.demo.model.UserLoginDTO;
import com.example.demo.service.TokenAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
        super(url);
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if(request.getMethod().equals("POST")){
            UserLoginDTO user = new ObjectMapper().readValue(request.getInputStream(), UserLoginDTO.class);
            return getAuthenticationManager()
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    user.getUsername(),
                                    user.getPassword(),
                                    Collections.emptyList()
                            ));
        }
        return getAuthenticationManager()
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                null,
                                null,
                                Collections.emptyList()
                        ));

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        TokenAuthenticationService.addAuthentication(response,  authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse res, AuthenticationException failed) throws IOException, ServletException {
        //super.unsuccessfulAuthentication(request, response, failed);
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter printWriter = res.getWriter();
        printWriter.write("{\"authorization\":"+null+"}");
        printWriter.flush();
        printWriter.close();
    }
}
