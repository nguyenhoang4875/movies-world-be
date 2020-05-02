package com.movies.service.impl;

import com.movies.entity.Role;
import com.movies.repository.RoleRepository;
import com.movies.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findOneByName(String name) {
        return roleRepository.findByName(name);
    }
}
