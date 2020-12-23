package com.example.demo.repo;

import com.example.demo.domain.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepo extends JpaRepository<AppRole, Long> , AppRoleRepoCustom{

}
