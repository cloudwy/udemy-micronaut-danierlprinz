package com.wy.udemy.broker.wallet.error;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CustomError(
        int status,
        String error,
        String message
){}
