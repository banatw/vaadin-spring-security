package com.example.application;

import com.example.application.model.AppUser;
import com.example.application.repos.AppUserRepos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.vaadin.artur.helpers.LaunchUtil;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class Application extends SpringBootServletInitializer {
    @Autowired
    private AppUserRepos appUserRepos;

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return x -> {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            AppUser appUser = new AppUser();
            appUser.setUsername("admin");
            appUser.setPassword(encoder.encode("admin"));
            appUser.setRole("ROLE_ADMIN");
            appUserRepos.save(appUser);

            appUser = new AppUser();
            appUser.setUsername("user");
            appUser.setPassword(encoder.encode("user"));
            appUser.setRole("ROLE_USER");
            appUserRepos.save(appUser);

        };
    }

}
