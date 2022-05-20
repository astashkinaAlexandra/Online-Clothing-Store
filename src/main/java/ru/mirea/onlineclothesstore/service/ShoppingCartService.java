package ru.mirea.onlineclothesstore.service;

import ru.mirea.onlineclothesstore.model.Product;

import java.util.List;

public interface ShoppingCartService {
    List<Product> getAllProducts();

    void addProduct(Long id, Product product);

    void deleteProduct(Long id);
}