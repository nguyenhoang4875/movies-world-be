package com.movies.repository;

import com.movies.entity.ConfirmationToken;
import com.movies.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Integer> {
    ConfirmationToken findByToken(String token);
}
