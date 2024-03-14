package com.wy.udemy.broker.wallet;

import com.wy.udemy.broker.Symbol;

import java.math.BigDecimal;
import java.util.UUID;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record DepositFiatMoney(
        UUID acoountId,
        UUID walletId,
        Symbol symbol,
        BigDecimal amount
) {
}
