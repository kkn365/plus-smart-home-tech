package ru.yandex.practicum.commerce.api.cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.yandex.practicum.commerce.api.cart.dto.ShoppingCartDto;

import java.util.UUID;

@FeignClient(name = "shopping-cart-service", path = "/api/v1/shopping-cart")
public interface ShoppingCartClient {

    @GetMapping("/{id}")
    ShoppingCartDto getShoppingCartById(@PathVariable UUID id);

}