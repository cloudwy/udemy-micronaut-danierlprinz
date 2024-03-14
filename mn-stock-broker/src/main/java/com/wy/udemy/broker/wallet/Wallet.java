package com.wy.udemy.broker.wallet;

import com.wy.udemy.broker.Symbol;

import java.math.BigDecimal;
import java.util.UUID;

public record Wallet(UUID accountId, UUID walletId, Symbol symbol, BigDecimal available, BigDecimal locked){}
