package com.example.demo.repo;

import com.example.demo.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo  extends JpaRepository<AppUser, Long> , AppUserRepoCustom{

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);

    AppUser findByUserNameOrEmail(String name, String email);
}
