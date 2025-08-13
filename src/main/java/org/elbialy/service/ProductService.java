package org.elbialy.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.elbialy.model.Product;
import org.elbialy.repository.ProductRepository;

import java.awt.print.Pageable;
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
}
