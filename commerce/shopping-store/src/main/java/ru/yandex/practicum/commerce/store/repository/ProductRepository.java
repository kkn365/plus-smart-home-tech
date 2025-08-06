package ru.yandex.practicum.commerce.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.practicum.commerce.api.store.dto.enums.ProductCategory;
import ru.yandex.practicum.commerce.store.entity.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findByProductCategory(ProductCategory productCategory, Pageable pageable);
}