package ru.yandex.practicum.commerce.cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.commerce.api.cart.dto.ChangeProductQuantityRequest;
import ru.yandex.practicum.commerce.api.cart.dto.ShoppingCartDto;
import ru.yandex.practicum.commerce.cart.service.ShoppingCartService;

import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/shopping-cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public ShoppingCartDto getShoppingCart(String username) {
        log.info("A shopping cart request has been received for the user {}", username);
        return shoppingCartService.getShoppingCart(username);
    }

    @PutMapping
    public ShoppingCartDto addToCart(@RequestBody Map<UUID, Integer> products, @RequestParam String username) {
        log.info("For the user {}, a request to add products", username);
        return shoppingCartService.addProducts(username, products);
    }

    @DeleteMapping
    public void deleteCart(String username) {
        log.info("User {} shopping-cart deletion request", username);
        shoppingCartService.deleteCart(username);
    }

    @PostMapping("/remove")
    public ShoppingCartDto removeFromCart(Map<UUID, Integer> products, String username) {
        log.info("Request to delete items from the user {} shopping cart", username);
        return shoppingCartService.removeProducts(username, products);
    }

    @PostMapping("/change-quantity")
    public ShoppingCartDto changeProductQuantity(@RequestBody ChangeProductQuantityRequest request, @RequestParam String username) {
        log.info("Request to change the quantity of a product {} per {} user {}",
                request.getProductId(), request.getNewQuantity(), username);
        return shoppingCartService.changeProductQuantity(username, request);
    }

}