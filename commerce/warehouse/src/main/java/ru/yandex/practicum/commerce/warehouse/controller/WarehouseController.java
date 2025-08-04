package ru.yandex.practicum.commerce.warehouse.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.commerce.api.cart.dto.ShoppingCartDto;
import ru.yandex.practicum.commerce.api.warehouse.dto.AddProductToWarehouseRequest;
import ru.yandex.practicum.commerce.api.warehouse.dto.AddressDto;
import ru.yandex.practicum.commerce.api.warehouse.dto.BookedProductsDto;
import ru.yandex.practicum.commerce.api.warehouse.dto.NewProductInWarehouseRequest;
import ru.yandex.practicum.commerce.warehouse.service.WarehouseService;

@Slf4j
@RestController
@RequestMapping("/api/v1/warehouse")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PutMapping
    public void addProduct(@RequestBody @Valid NewProductInWarehouseRequest request) {
        log.info("Request to add a product {}", request);
        warehouseService.addProduct(request);
    }

    @PostMapping("/check")
    public BookedProductsDto checkProductCount(ShoppingCartDto shoppingCartDto) {
        log.info("Request to check the shopping cart {}", shoppingCartDto);
        return warehouseService.checkProductCount(shoppingCartDto);
    }

    @PostMapping("/add")
    public void addProductQuantity(@RequestBody @Valid AddProductToWarehouseRequest request) {
        log.info("Request to add a product to the warehouse {}", request);
        warehouseService.addProductQuantity(request);
    }

    @GetMapping("/address")
    public AddressDto getWarehouseAddress() {
        log.info("Requesting a warehouse address");
        return warehouseService.getWarehouseAddress();
    }

}