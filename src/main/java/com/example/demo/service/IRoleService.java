package com.example.demo.service;

import com.example.demo.model.Role;

import java.util.List;

public interface IRoleService {
    Role saveRole(Role role);

    Role getRole(String name);

    List<Role> getRoles();
}
