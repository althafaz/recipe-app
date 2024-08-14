package com.bandits.api.recipe_app_api.repository;

import com.bandits.api.recipe_app_api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByAuthority(String authority);
}
