package com.springboot.pmsrestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.pmsrestservice.entities.AppUsers;

public interface AppUsersRepository extends JpaRepository<AppUsers, Long> {

	AppUsers findByUsername(String username);
}
