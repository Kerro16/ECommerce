package com.example.demo;

import com.example.demo.controller.CategoryController;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
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
@WebMvcTest(CategoryController.class)
public class CategoryTestController {

    @MockBean
    CategoryService categoryService;

    @Autowired
    MockMvc mockMvc;

    List<Category> categoryList = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        long id = 1;
        long id2 = 2;

        Category CAT1 = new Category(id,"Sports");
        Category CAT2 = new Category(id2, "Other");

        categoryList.add(CAT1);
        categoryList.add(CAT2);
    }

    @Test
    public void findAll() throws Exception{

        Mockito.when(categoryService.getCategories()).thenReturn(categoryList);

        mockMvc.perform(get("/api/category/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Sports")))
                .andExpect(jsonPath("$[1].name", Matchers.is("Other")));
    }

}
