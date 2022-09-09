package com.bosonit.backbusiness.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    String ADMIN = "ADMIN";
    String USER = "USER";

    String ALLTOKEN = "/business/login/**";
    String ALLCLIENT = "/business/client/**";
    String ALLTRIP = "/business/trip/**";
    String ALLTICKET = "/business/ticket/**";
    String ALLMAIL = "/business/mail/**";

    // Ignore security for H2 of DB.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    // Authorization.
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().addFilterAfter(new JWTAuthentication(), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Authorize Requests of Tokens
        http.authorizeRequests().antMatchers(POST, ALLTOKEN).permitAll();
        http.authorizeRequests().antMatchers(GET, ALLTOKEN).permitAll();

        // Authorize Requests of Clients
        http.authorizeRequests().antMatchers(POST, ALLCLIENT).permitAll();
        http.authorizeRequests().antMatchers(GET, ALLCLIENT).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(GET, "/business/client/{\\d}").hasAnyAuthority(USER);
        http.authorizeRequests().antMatchers(PUT, ALLCLIENT).hasAnyAuthority(ADMIN, USER);
        http.authorizeRequests().antMatchers(DELETE, ALLCLIENT).hasAnyAuthority(ADMIN);

        // Authorize Requests of Trips
        http.authorizeRequests().antMatchers(POST, ALLTRIP).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(GET, "/business/trip/{\\d}").permitAll();
        http.authorizeRequests().antMatchers(GET, "/business/trip/details").permitAll();
        http.authorizeRequests().antMatchers(GET, "/business/trip/detailsLocalDate").permitAll();
        http.authorizeRequests().antMatchers(GET, ALLTRIP).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(PUT, ALLTRIP).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(DELETE, ALLTRIP).hasAnyAuthority(ADMIN);

        // Authorize Requests of Tickets
        http.authorizeRequests().antMatchers(POST, ALLTICKET).hasAnyAuthority(USER, ADMIN);
        http.authorizeRequests().antMatchers(GET, "/business/ticket/{\\d}").hasAnyAuthority(USER);
        http.authorizeRequests().antMatchers(GET, ALLTICKET).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(DELETE, ALLTICKET).hasAnyAuthority(USER, ADMIN);

        // Authorize Requests of Mails
        http.authorizeRequests().antMatchers(GET, ALLMAIL).hasAnyAuthority(ADMIN);

        http.authorizeRequests().anyRequest().authenticated();
    }
}
