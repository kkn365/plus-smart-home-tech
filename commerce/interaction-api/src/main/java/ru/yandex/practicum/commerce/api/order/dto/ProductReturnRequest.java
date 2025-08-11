package ru.yandex.practicum.commerce.api.order.dto;

import java.util.Map;
import java.util.UUID;

public record ProductReturnRequest(UUID orderId, Map<UUID, Long> products) {
}