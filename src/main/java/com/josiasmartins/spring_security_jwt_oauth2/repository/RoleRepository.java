package com.josiasmartins.spring_security_jwt_oauth2.repository;

import com.josiasmartins.spring_security_jwt_oauth2.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
