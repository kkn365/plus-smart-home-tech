package ru.yandex.practicum.commerce.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.commerce.api.store.dto.ProductDto;
import ru.yandex.practicum.commerce.api.store.dto.enums.ProductCategory;
import ru.yandex.practicum.commerce.api.store.dto.enums.QuantityState;
import ru.yandex.practicum.commerce.store.service.ShoppingStoreService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/shopping-store")
@RequiredArgsConstructor
public class ShoppingStoreController {

    private final ShoppingStoreService shoppingStoreService;

    @GetMapping
    public Page<ProductDto> getProducts(@RequestParam ProductCategory category, @PageableDefault Pageable pageable) {
        log.info("Getting products by category {} and pageable {}", category, pageable);
        return shoppingStoreService.getProductsByCategory(category, pageable);
    }

    @PutMapping
    public ProductDto createNewProduct(@Valid @RequestBody ProductDto productDto) {
        log.info("Creating product by ProductDto: {}", productDto);
        return shoppingStoreService.createNewProduct(productDto);
    }

    @PostMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto)  {
        log.info("Update product: {}", productDto);
        return shoppingStoreService.updateProduct(productDto);
    }

    @PostMapping("/removeProductFromStore")
    public Boolean removeProductFromStore(@RequestBody String productId) {
        log.info("Remove product from store: {}", productId);
        return shoppingStoreService.removeProductFromStore(UUID.fromString(productId.replace("\"", "")));
    }

    @PostMapping("/quantityState")
    public Boolean setProductQuantityState(@RequestParam UUID productId, @RequestParam QuantityState quantityState){
        log.info("Update product {} quantity: {}", productId, quantityState);
        return shoppingStoreService.setProductQuantityState(productId, quantityState);
    }

    @GetMapping("/{productId}")
    public ProductDto getProduct(@PathVariable("productId") UUID productId)  {
        log.info("Getting product by id: {}", productId);
        return shoppingStoreService.getProduct(productId);
    }
}