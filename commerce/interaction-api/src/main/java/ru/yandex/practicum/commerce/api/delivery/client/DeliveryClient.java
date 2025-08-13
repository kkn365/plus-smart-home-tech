package ru.yandex.practicum.commerce.api.delivery.client;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.commerce.api.delivery.dto.DeliveryDto;
import ru.yandex.practicum.commerce.api.order.dto.OrderDto;

import java.math.BigDecimal;
import java.util.UUID;

@FeignClient(name = "delivery", path = "/api/v1/delivery")
public interface DeliveryClient {

    @PutMapping
    DeliveryDto addDelivery(@Valid @RequestBody DeliveryDto newDelivery);

    @PostMapping("/successful")
    void successfulDelivery(@Valid @RequestBody UUID deliveryId);

    @PostMapping("/picked")
    void pickedDelivery(@Valid @RequestBody UUID deliveryId);

    @PostMapping("/failed")
    void failedDelivery(@Valid @RequestBody UUID deliveryId);

    @PostMapping("/cost")
    BigDecimal getDeliveryCost(@Valid @RequestBody OrderDto order);

}
