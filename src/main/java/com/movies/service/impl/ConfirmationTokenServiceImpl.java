package com.movies.service.impl;

import com.movies.entity.ConfirmationToken;
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
}
