package com.theneodriver.chatapp.repository;

import com.theneodriver.chatapp.model.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User>  findByNameContainingIgnoreCase(String name, Pageable pageable);
    Optional<User> findByName(String name);
    void deleteByName(String name);
}
