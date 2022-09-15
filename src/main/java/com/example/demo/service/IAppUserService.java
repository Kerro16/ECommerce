package com.example.demo.service;

import com.example.demo.data.ApiResponse;
import com.example.demo.model.AppUser;
import com.example.demo.model.CustomerData;

import java.util.List;

public interface IAppUserService {
    AppUser saveUser(AppUser user);

    AppUser saveAdmin(AppUser user);

    AppUser getUser(String username);

    ApiResponse<String> getRoleByUser(Long id);

    List<AppUser> getUsers();

    ApiResponse<Long> register(CustomerData data);

    ApiResponse<Long> login(AppUser user);
}
