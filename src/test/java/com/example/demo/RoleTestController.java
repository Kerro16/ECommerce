package com.example.demo;

import com.example.demo.controller.RoleController;
import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RoleController.class)
public class RoleTestController {

    @MockBean
    RoleService roleService;

    @Autowired
    MockMvc mockMvc;

    List<Role> categoryList = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        long id = 1;
        long id2 = 2;

        Role ROL1 = new Role(id,"Administrator");
        Role ROL2 = new Role(id2, "User");

        categoryList.add(ROL1);
        categoryList.add(ROL2);
    }

    @Test
    public void findAll() throws Exception{

        Mockito.when(roleService.getRoles()).thenReturn(categoryList);

        mockMvc.perform(get("/api/role/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Administrator")))
                .andExpect(jsonPath("$[1].name", Matchers.is("User")));
    }

}
