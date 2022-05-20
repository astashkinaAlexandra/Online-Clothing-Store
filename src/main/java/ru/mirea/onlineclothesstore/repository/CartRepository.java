package ru.mirea.onlineclothesstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.onlineclothesstore.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
