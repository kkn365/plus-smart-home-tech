package ru.yandex.practicum.commerce.cart.controller;

import jakarta.ws.rs.InternalServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.commerce.cart.exception.CartNotFoundException;
import ru.yandex.practicum.commerce.cart.exception.NoProductsInShoppingCartException;
import ru.yandex.practicum.commerce.cart.exception.NotAuthorizedUserException;
import ru.yandex.practicum.commerce.cart.exception.ProductNotAvailableException;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(NoProductsInShoppingCartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotAuthorizedUserException(NoProductsInShoppingCartException ex) {
        return errorResponse(HttpStatus.BAD_REQUEST, "Cart is empty", ex);
    }

    @ExceptionHandler(NotAuthorizedUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleNotAuthorizedUserException(NotAuthorizedUserException ex) {
        return errorResponse(HttpStatus.UNAUTHORIZED, "Unauthorized user", ex);
    }

    @ExceptionHandler(ProductNotAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleProductNotAvailableException(ProductNotAvailableException ex) {
        return errorResponse(HttpStatus.BAD_REQUEST, "Product unavailable", ex);
    }

    @ExceptionHandler(CartNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCartNotFoundException(CartNotFoundException ex) {
        return errorResponse(HttpStatus.NOT_FOUND, "Cart not found", ex);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalServerErrorException(InternalServerErrorException ex) {
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", ex);
    }

    private ErrorResponse errorResponse(HttpStatus status, String userMessage, Throwable ex) {
        return ErrorResponse.builder()
                .cause(ex.getCause())
                .stackTrace(List.of(ex.getStackTrace()))
                .httpStatus(status.name())
                .userMessage(userMessage)
                .message(ex.getMessage())
                .suppressed(List.of(ex.getSuppressed()))
                .localizedMessage(ex.getLocalizedMessage())
                .build();
    }
}