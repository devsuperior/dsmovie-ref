package com.devsuperior.dsmovie.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmovie.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
}
