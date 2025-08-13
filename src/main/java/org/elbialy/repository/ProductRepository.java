package org.elbialy.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.elbialy.model.Product;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {
    public  PanacheQuery<org.elbialy.model.Product> findByCategory(String filter) {
        return find("category", filter);
    }
}
