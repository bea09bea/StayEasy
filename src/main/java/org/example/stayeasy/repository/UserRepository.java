package org.example.stayeasy.repository;

import org.example.stayeasy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
