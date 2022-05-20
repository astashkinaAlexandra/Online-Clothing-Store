package ru.mirea.onlineclothesstore.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mirea.onlineclothesstore.model.Product;
import ru.mirea.onlineclothesstore.service.impl.ProductServiceImpl;

import java.util.List;

@Controller
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        return findPaginated(1, "name", "asc", model);
    }

    @GetMapping("/products/new")
    public String createProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "create_product";
    }

    @PostMapping("/products")
    public String saveProduct(@RequestParam("file") MultipartFile file,
                              @ModelAttribute("product") Product product) {
        productService.saveProduct(file, product);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "edit_product";
    }

    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id,
                                @RequestParam("file") MultipartFile file,
                                @ModelAttribute("product") Product product,
                                Model model) {

        Product existingProduct = productService.getProductById(id);
        existingProduct.setId(id);
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setImage(product.getImage());
        existingProduct.setPrice(product.getPrice());

        productService.updateProduct(file, existingProduct);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Product> page = productService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Product> productList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("productList", productList);
        return "products";
    }
}