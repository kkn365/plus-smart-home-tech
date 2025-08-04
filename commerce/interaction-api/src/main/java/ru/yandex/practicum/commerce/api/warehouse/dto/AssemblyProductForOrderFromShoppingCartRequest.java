package ru.yandex.practicum.commerce.api.warehouse.dto;

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
public class AssemblyProductForOrderFromShoppingCartRequest {

    @NotNull(message = "The shopping cart ID must not be empty.")
    private UUID shoppingCartId;

    @NotNull(message = "The order ID must not be empty.")
    private UUID orderId;

}