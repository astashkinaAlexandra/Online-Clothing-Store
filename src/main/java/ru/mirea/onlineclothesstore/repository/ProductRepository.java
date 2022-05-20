package ru.mirea.onlineclothesstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.onlineclothesstore.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}