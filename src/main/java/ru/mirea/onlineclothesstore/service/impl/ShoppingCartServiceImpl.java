package ru.mirea.onlineclothesstore.service.impl;

import org.springframework.stereotype.Service;
import ru.mirea.onlineclothesstore.model.Cart;
import ru.mirea.onlineclothesstore.model.Product;
import ru.mirea.onlineclothesstore.repository.CartRepository;
import ru.mirea.onlineclothesstore.service.ShoppingCartService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    CartRepository cartRepository;

    public ShoppingCartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    private final Map<Long, Product> products = new HashMap<>();
    private final Cart cart = new Cart();

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void addProduct(Long id, Product product) {
        products.put(id, product);

        List<Product> productList = new ArrayList<>(products.values());
        cart.setProducts(productList);
        cartRepository.save(cart);
    }

    @Override
    public void deleteProduct(Long id) {
        products.remove(id);

        List<Product> productList = new ArrayList<>(products.values());
        cart.setProducts(productList);
        cartRepository.save(cart);
    }
}
