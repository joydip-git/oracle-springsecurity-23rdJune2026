package com.springboot.pmsrestservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.pmsrestservice.entities.AppUser;

public interface AppUsersRepository extends JpaRepository<AppUser, Long> {

	Optional<AppUser> findByUsername(String username);
}
