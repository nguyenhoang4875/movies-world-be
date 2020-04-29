package com.movies.service;

import com.movies.entity.Role;

public interface RoleService {
    Role findOneByName(String name);
}
