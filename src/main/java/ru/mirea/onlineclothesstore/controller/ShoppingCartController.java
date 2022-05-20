package ru.mirea.onlineclothesstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.onlineclothesstore.model.Product;
import ru.mirea.onlineclothesstore.service.impl.ProductServiceImpl;
import ru.mirea.onlineclothesstore.service.impl.ShoppingCartServiceImpl;

import java.util.List;

@Controller
public class ShoppingCartController {

    private final ShoppingCartServiceImpl cartService;

    private final ProductServiceImpl productService;

    public ShoppingCartController(ShoppingCartServiceImpl cartService, ProductServiceImpl productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cartProducts", cartService.getAllProducts());
        model.addAttribute("total", cartSum());
        return "cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addProduct(@PathVariable("id") Long productId, Model model) {
        Product product = productService.getProductById(productId);
        cartService.addProduct(productId, product);
        model.addAttribute("cartProducts", product);
        return "redirect:/cart";
    }

    @GetMapping("/cart/delete/{id}")
    public String deleteProductInCart(@PathVariable("id") Long productId) {
        cartService.deleteProduct(productId);
        return "redirect:/cart";
    }

    private Double cartSum() {
        List<Product> productList = cartService.getAllProducts();
        Double sum = 0.0;
        for (Product product : productList) {
            sum += product.getPrice();
        }
        return sum;
    }
}
