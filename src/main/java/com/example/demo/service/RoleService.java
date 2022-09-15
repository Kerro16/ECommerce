package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService implements IRoleService{

    private final IRoleRepository roleRepository;


    @Override
    public Role saveRole(Role role) {
        log.info("Guardando nuevo rol {} en la base de datos",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public Role getRole(String name) {
        log.info("Buscando rol {} en la base de datos",name);
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> getRoles() {
        log.info("Buscando todos los roles de la base de datos");
        return roleRepository.findAll();
    }
}
