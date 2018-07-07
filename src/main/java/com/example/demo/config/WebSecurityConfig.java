package com.example.demo.config;

import javax.sql.DataSource;

import com.example.demo.filter.JWTAuthenticationFilter;
import com.example.demo.filter.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedHeader("Authorization, Authorization");
        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("Access-Control-Expose-Headers");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and();
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/user/**").hasAuthority("USER")
                .antMatchers("/admin/**").hasAuthority(("ADMIN"))
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().and()
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("SELECT username, password, enable FROM public.user WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT u.username as username, r.name as role FROM public.user u "
                        + "INNER JOIN public.users_roles ur ON u.id = ur.user_id "
                        + "INNER JOIN public.role r ON ur.role_id = r.id WHERE u.username = ?").passwordEncoder(new BCryptPasswordEncoder(12));
    }
}
