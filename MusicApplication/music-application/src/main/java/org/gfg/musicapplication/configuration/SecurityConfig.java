package org.gfg.musicapplication.configuration;

import org.gfg.musicapplication.model.UserType;
import org.gfg.musicapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().
                disable().
                httpBasic().
                and().
                authorizeRequests().
                antMatchers("/signup/**").permitAll().
                antMatchers("/v1/songs/addition/**").hasAuthority(UserType.ADMIN.name()).
                and().
                formLogin();
    }


}

