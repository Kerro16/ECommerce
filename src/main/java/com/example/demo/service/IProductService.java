package com.example.demo.service;

import com.example.demo.data.ApiResponse;
import com.example.demo.data.ProductCard;
import com.example.demo.model.Product;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IProductService {

    ApiResponse<Long> saveProduct(Product product);

    ApiResponse<Product> getProduct(Long id);

    List<Product> getProducts();

    ApiResponse<List<ProductCard>> getProductCards();

    void addCategoryToProduct(String categoryName, String productName);

    ApiResponse<Long> editProduct(Long id, Product product);

    ApiResponse<Long> deleteProduct(Long id);
}
