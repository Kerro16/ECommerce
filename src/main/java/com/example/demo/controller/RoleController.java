package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@CrossOrigin(origins="*",methods= {RequestMethod.GET,RequestMethod.POST})
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/")
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok().body(roleService.getRoles());
    }

    @PostMapping("/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return ResponseEntity.ok().body(roleService.saveRole(role));
    }

}
