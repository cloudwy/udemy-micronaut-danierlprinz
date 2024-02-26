package com.wy.udemy.broker;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Symbol(String value) {
}

//public class Symbol {
//
//    String value;
//
//    public Symbol(String value) {
//        this.value = value;
//    }
//
//    public String getValue() {
//        return value;
//    }
//}
