package com.example.application.securityhelper;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN").and().withUser("user")
                .password("{noop}user").roles("USER");
    }

    private static final String LOGIN_PROCESSING_URL = "/login";
    private static final String LOGIN_FAILURE_URL = "/login?error";
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_SUCCESS_URL = "/login";

    @Override
    public void configure(WebSecurity web) throws Exception {
        // TODO Auto-generated method stub
        web.ignoring().antMatchers("/VAADIN/**", "/favicon.ico", "/robots.txt", "/manifest.webmanifest", "/sw.js",
                "/offline.html", "/icons/**", "/images/**", "/styles/**", "/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        new SecurityUtils();
        // TODO Auto-generated method stub
        http.csrf().disable().requestCache().requestCache(new CustomRequestCache()).and().authorizeRequests()
                .requestMatchers(request -> SecurityUtils.isFrameworkInternalRequest(request)).permitAll()
                .antMatchers("/user/**").hasRole("USER").antMatchers("/admin/**").hasRole("ADMIN")

                .and().formLogin().loginPage(LOGIN_URL).permitAll().loginProcessingUrl(LOGIN_PROCESSING_URL)
                .failureUrl(LOGIN_FAILURE_URL).and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);
    }

}
