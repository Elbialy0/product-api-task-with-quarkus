package org.elbialy.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.elbialy.dto.ProductDto;
import org.elbialy.model.Product;
import org.elbialy.repository.ProductRepository;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> getAll(int page, int size) {
        PanacheQuery<Product> query = productRepository.findAll().page(page,size);
        return query.list();
    }

    @Transactional
    public String  add(Product product) {
        productRepository.persist(product);
        return "Product saved successfully";
    }

    public ProductDto getById(long id) {
        Product product = productRepository.findById(id);
        return new ProductDto(product.getName(),product.getCategory(),product.getQuantity());
    }

    public ProductDto update(long id, ProductDto productDto) {
        Product product = productRepository.findById(id);
        product.setName(productDto.name());
        product.setCategory(productDto.category());
        product.setQuantity(productDto.quantity());
        product.persist();
        return productDto;
    }
}
