package com.movies.service;

import com.movies.entity.dao.Role;

public interface RoleService {
    Role findOneByName(String name);
}
