package com.example.demo.controller;

import com.example.demo.data.ApiResponse;
import com.example.demo.model.AppUser;
import com.example.demo.model.CustomerData;
import com.example.demo.service.AppUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

    private final AppUserService appUserService;

    @GetMapping("/")
    public ResponseEntity<List<AppUser>> getUsers() {
        return ResponseEntity.ok().body(appUserService.getUsers());
    }


    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody CustomerData data) {
        return ResponseEntity.ok().body(appUserService.register(data));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody AppUser user) {
        return ResponseEntity.ok().body(appUserService.login(user));
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<ApiResponse> getUserRole(@PathVariable Long id) {
        return ResponseEntity.ok().body(appUserService.getRoleByUser(id));
    }

    @PostMapping("/admin/save")
    public ResponseEntity<AppUser> saveAdmin(@RequestBody AppUser user) {
        return ResponseEntity.ok().body(appUserService.saveAdmin(user));
    }
}
