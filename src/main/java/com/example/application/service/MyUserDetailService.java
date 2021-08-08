package com.example.application.service;

import com.example.application.model.AppUser;
import com.example.application.repos.AppUserRepos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private AppUserRepos appUserRepos;

    @Override
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        AppUser appUser = appUserRepos.findByUsername(arg0);
        if (appUser == null) {
            throw new UsernameNotFoundException("fdfdfd");
        }
        User user = new User(appUser.getUsername(), appUser.getPassword(),
                AuthorityUtils.createAuthorityList(appUser.getRole()));
        return user;
    }

}
