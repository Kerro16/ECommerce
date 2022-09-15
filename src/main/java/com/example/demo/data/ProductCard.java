package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCard {

    private Long id;
    private String name;
    private String imageUrl;
    private Double price;
    private String categoryName;

}
