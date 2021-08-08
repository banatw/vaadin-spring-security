package com.example.application.repos;

import com.example.application.model.AppUser;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepos extends CrudRepository<AppUser, Integer> {
    AppUser findByUsername(String uname);
}
