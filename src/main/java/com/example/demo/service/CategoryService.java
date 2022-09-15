package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        log.info("Guardando una nueva categoria {} en la base de datos", category.getName());
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getCategory(String name) {
        log.info("Buscando categoria {} en la base de datos", name);
        return Optional.ofNullable(categoryRepository.findByName(name));
    }

    @Override
    public List<Category> getCategories() {
        log.info("Buscando todas las categorias");
        return categoryRepository.findAll();
    }
}
