package com.ludmylla.cineapi.repository;

import com.ludmylla.cineapi.model.Role;
import com.ludmylla.cineapi.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName name);
}
