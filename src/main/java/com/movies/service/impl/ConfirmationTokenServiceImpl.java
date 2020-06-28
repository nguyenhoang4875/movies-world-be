package com.movies.service.impl;

import com.movies.entity.dao.ConfirmationToken;
import com.movies.repository.ConfirmationTokenRepository;
import com.movies.service.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public ConfirmationToken save(ConfirmationToken confirmationToken) {
        return confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public ConfirmationToken findByToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    @Override
    public void deleteToke(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.delete(confirmationToken);
    }
}
