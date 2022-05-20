package ru.mirea.onlineclothesstore.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import ru.mirea.onlineclothesstore.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product saveProduct(MultipartFile file, Product product);

    Product getProductById(Long id);

    Product updateProduct(MultipartFile file, Product product);

    void deleteProductById(Long id);

    Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
