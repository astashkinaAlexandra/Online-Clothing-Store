package ru.mirea.onlineclothesstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.mirea.onlineclothesstore.service.impl.ProductServiceImpl;

@Controller
public class MainController {

    private final ProductServiceImpl productService;

    public MainController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = {"/", "/home"})
    public String home(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }

    @GetMapping("/item/{id}")
    public String item(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "item_page";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
