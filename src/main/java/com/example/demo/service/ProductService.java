package com.example.demo.service;

import com.example.demo.data.ApiResponse;
import com.example.demo.data.ProductCard;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.ICategoryRepository;
import com.example.demo.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    @Override
    public ApiResponse<Long> saveProduct(Product product) {
        log.info("Guardando un nuevo producto {} en la base de datos", product.getName());
        Optional<Category> category = Optional.ofNullable(categoryRepository.findByName(product.getCategory().getName()));
        if (category.isEmpty()) return new ApiResponse<Long>(false, null, "Categoria no existe");
        Product productBD = new Product(null, product.getSku(), product.getName(), product.getPrice(),
                product.getWeight(), product.getDescription(), product.getImage(), category.get(),
                product.getCreate_date(), product.getStock());
        return new ApiResponse<Long>(true, productRepository.save(productBD).getId(), null);
    }

    @Override
    public ApiResponse<Product> getProduct(Long id) {
        log.info("Buscando producto {} en la base de datos", id);
        //Buscar el producto en la base de datos
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty())
            return new ApiResponse<Product>(false, null, "Producto no encontrado en la base de datos");
        return new ApiResponse<Product>(true, product.get(), null);
    }

    @Override
    public List<Product> getProducts() {
        log.info("Buscando todos los productos de la base de datos");
        return productRepository.findAll();
    }

    @Override
    public ApiResponse<List<ProductCard>> getProductCards() {
        log.info("Buscando todos los cards products de la base de datos");

        List<ProductCard> productCards = new ArrayList<ProductCard>();
        List<Product> products = getProducts();

        for (Product product : products) {
            productCards.add(
                    new ProductCard(product.getId(), product.getName(), product.getImage(), product.getPrice(),
                            product.getCategory().getName()));
        }


        return new ApiResponse<List<ProductCard>>(true, productCards, null);
    }

    @Override
    public void addCategoryToProduct(String categoryName, String productName) {
        Category category = categoryRepository.findByName(categoryName);
        Product product = productRepository.findByName(productName);
        product.setCategory(category);
    }

    @Override
    public ApiResponse<Long> editProduct(Long id, Product productData) {
        Optional<Product> updateProduct = productRepository.findById(id);
        if (updateProduct.isEmpty())
            return new ApiResponse<Long>(false, null, "Producto no existe en la base de datos");

        Optional<Category> category = Optional.ofNullable(categoryRepository.findByName(productData.getCategory().getName()));
        if (category.isEmpty())
            return new ApiResponse<Long>(false, null, "Categoria no encontrada en la base de datos");

        updateProduct.get().setSku(productData.getSku());
        updateProduct.get().setName(productData.getName());
        updateProduct.get().setPrice(productData.getPrice());
        updateProduct.get().setWeight(productData.getWeight());
        updateProduct.get().setDescription(productData.getDescription());
        updateProduct.get().setImage(productData.getImage());
        updateProduct.get().setCategory(category.get());
        updateProduct.get().setStock(productData.getStock());

        productRepository.save(updateProduct.get());
        return new ApiResponse<Long>(true, updateProduct.get().getId(), null);
    }

    @Override
    public ApiResponse<Long> deleteProduct(Long id) {
        Optional<Product> productToDelete = productRepository.findById(id);
        if (productToDelete.isEmpty())
            return new ApiResponse<Long>(false, null, "Producto no encontrado en la base de datos");

        productRepository.delete(productToDelete.get());

        return new ApiResponse<Long>(true, null, null);
    }


}
