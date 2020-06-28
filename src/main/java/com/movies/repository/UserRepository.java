package com.movies.repository;

import com.movies.entity.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
    Optional<User> findById(Integer id);
    List<User> findByRolesName(String role);
}
