package com.example.demo.service;

import com.example.demo.domain.AppUser;
import com.example.demo.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceDemoImpl implements UserServiceDemo{

    @Autowired
    private AppUserRepo appUserRepo;

    @Override
    public List<AppUser> loadAllData() {
        return appUserRepo.findAll();
    }
}
