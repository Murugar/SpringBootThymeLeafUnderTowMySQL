package com.iqmsoft;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepo extends JpaRepository<User, Integer> {
    List<User> findByNameContainingIgnoreCase(String name);
    
    @Query("select u from User u where lower(concat(u.name, u.email)) like concat('%', lower(?1), '%')")
    Page<User> findByNameOrEmail(String search, Pageable pageable);
}
