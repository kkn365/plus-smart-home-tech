package ru.yandex.practicum.commerce.api.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeProductQuantityRequest {

    @NotNull(message = "The product ID must not be empty.")
    private UUID productId;

    @Min(value = 0, message = "New quantity must greater than 0.")
    private int newQuantity;

}