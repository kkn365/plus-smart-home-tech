package ru.yandex.practicum.commerce.api.warehouse.dto;

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
public class AddProductToWarehouseRequest {

    @NotNull(message = "The ID must not be empty.")
    private UUID productId;

    @Min(value = 1, message = "The quantity must be greater than 0.")
    private Long quantity;

}