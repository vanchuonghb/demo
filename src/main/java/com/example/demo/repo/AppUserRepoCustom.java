package com.example.demo.repo;

import com.example.demo.domain.AppUser;

public interface AppUserRepoCustom {
    AppUser findUserAccount(String userName);
}
