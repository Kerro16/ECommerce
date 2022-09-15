package com.example.demo.controller;

import com.example.demo.data.ApiResponse;
import com.example.demo.data.ProductCard;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(productService.saveProduct(product));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getProductCards() {
        return ResponseEntity.ok().body(productService.getProductCards());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editProduct(@PathVariable Long id,@RequestBody Product product){
        return ResponseEntity.ok().body(productService.editProduct(id,product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok().body(productService.deleteProduct(id));
    }
}
