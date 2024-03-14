package com.wy.udemy.broker.wallet.error;

import com.wy.udemy.broker.api.RestApiResponse;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CustomError(
        int status,
        String error,
        String message
) implements RestApiResponse {}
