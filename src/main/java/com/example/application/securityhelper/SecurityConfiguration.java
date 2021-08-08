package com.example.application.securityhelper;

import com.example.application.service.MyUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailService myUserDetailService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailService);
        authenticationProvider.setPasswordEncoder(encoder);
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        auth.authenticationProvider(authenticationProvider());
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
