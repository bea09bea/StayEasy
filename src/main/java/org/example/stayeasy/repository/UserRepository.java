package org.example.stayeasy.repository;

import org.example.stayeasy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//kommunicerar med databas
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
    boolean existsByPhoneAndIdNot(String phone, Long id);
    boolean existsByEmailAndIdNot(String email, Long id);
}
