package com.example.demo;


import com.example.demo.controller.UserController;
import com.example.demo.model.AppUser;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.Role;
import com.example.demo.service.AppUserService;
import com.example.demo.service.ProductService;
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
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    AppUserService appUserService;
    @Autowired
    MockMvc mockMvc;

    List<AppUser> appUsersList = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        long id = 1;
        long id2 = 2;
        long id3 = 3;

        Role ROL = new Role(id3,"Users");

        AppUser User1 = new AppUser(id,"User1","Password1",ROL);
        AppUser User2 = new AppUser(id2,"User2","Password2",ROL);

        appUsersList.add(User1);
        appUsersList.add(User2);
    }

    @Test
    public void findAllTest() throws Exception {

        Mockito.when(appUserService.getUsers()).thenReturn(appUsersList);

        mockMvc.perform(get("/api/user/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].username", Matchers.is("User1")))
                .andExpect(jsonPath("$[1].username", Matchers.is("User2")));

    }
}
