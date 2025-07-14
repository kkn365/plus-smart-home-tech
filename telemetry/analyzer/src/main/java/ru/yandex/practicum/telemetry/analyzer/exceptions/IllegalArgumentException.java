package ru.yandex.practicum.telemetry.analyzer.exceptions;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;

public class IllegalArgumentException extends StatusRuntimeException {
    public IllegalArgumentException(String message) {
        super(Status.INVALID_ARGUMENT.withDescription(message));
    }
}