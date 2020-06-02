package com.movies.service;

import com.movies.entity.dao.ConfirmationToken;

public interface ConfirmationTokenService {
    ConfirmationToken save(ConfirmationToken confirmationToken);

    ConfirmationToken findByToken(String token);
}
