package com.bandits.api.recipe_app_api.repository;

import com.bandits.api.recipe_app_api.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);
}
