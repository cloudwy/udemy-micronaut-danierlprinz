package com.wy.udemy;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Introspected
public class Greeting {
    final String myText = "Hello World";
    final BigDecimal id = BigDecimal.valueOf(123456789);
    final Instant timeUTC = Instant.now();
}
